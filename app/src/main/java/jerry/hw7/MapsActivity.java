package jerry.hw7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String OriJson;
    LiveCams cams;
    Location CurLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        DownloadTask downloadTask = new DownloadTask();
        try {
            OriJson = downloadTask.execute(new URL("https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=17&type=2")).get();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        cams = Util.GetLiveCamsFrom(OriJson);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                //request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {
            //got permission
        }
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                CurLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        service.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
        //CurLocation = LocationServices.getFusedLocationProviderClient(this).getLastLocation().getResult();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng seattle = new LatLng(47.62, -122.35);
        //my mock location
        mMap.addMarker(new MarkerOptions().position(seattle)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seattle, 12));
        //add markers
        addCams();
        //click a marker
        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter(getApplicationContext()));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1)
            Log.v("requestPermission", "permission.ACCESS_FINE_LOCATION");
    }

    void addCams() {
        List<Feature> features = cams.getFeatures();
        for (Feature feature : features) {
            AddACam(feature);
        }
    }

    void AddACam(Feature feature) {
        List<Double> pointCoordinate = feature.getPointCoordinate();
        Camera camera = feature.getCameras().get(0);
        String description = camera.getDescription();
        String type = camera.getType();
        String imageUrl = type.equals("sdot") ? "http://www.seattle.gov/trafficcams/images/" : "http://images.wsdot.wa.gov/nw/";
        imageUrl += camera.getImageUrl();
        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(pointCoordinate.get(0), pointCoordinate.get(1))));
        infoObj infoObj = new infoObj();
        infoObj.title = description;
        infoObj.url = imageUrl;
        marker.setTag(infoObj);
    }
}
