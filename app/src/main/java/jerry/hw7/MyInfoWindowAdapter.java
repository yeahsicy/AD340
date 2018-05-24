package jerry.hw7;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;

    public MyInfoWindowAdapter(Context c) {
        context = c;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        View view = View.inflate(context, R.layout.custom_info, null);
        infoObj markerTag = (infoObj) marker.getTag();

        if (markerTag == null) return view;

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(markerTag.title);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(context).load(markerTag.url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                /*if (!isFirstResource) {
                    marker.showInfoWindow();
                }*/
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (marker.isInfoWindowShown()) {
                            marker.showInfoWindow();
                        }
                    }
                }, 100);
                return false;
            }
        }).into(imageView);
        return view;
    }
}
