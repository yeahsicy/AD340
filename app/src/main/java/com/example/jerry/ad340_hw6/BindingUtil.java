package com.example.jerry.ad340_hw6;

import java.util.ArrayList;

public class BindingUtil {
    private static ArrayList<Camera> getCamerasFrom(LiveCams cams) {
        ArrayList<Camera> cameras = new ArrayList<>();
        for (Feature feature : cams.getFeatures())
            cameras.addAll(feature.getCameras());
        return cameras;
    }

    private static OutputItem mapOutputItemFrom(Camera camera) {
        OutputItem outputItem = new OutputItem();
        outputItem.name = camera.getDescription();
        String path = camera.getType().equals("sdot") ?
                "http://www.seattle.gov/trafficcams/images/" : "http://images.wsdot.wa.gov/nw/";
        path += camera.getImageUrl();
        outputItem.Uri = path;
        return outputItem;
    }

    static ArrayList<OutputItem> getOutputItems(LiveCams cams) {
        ArrayList<OutputItem> outputItems = new ArrayList<>();
        ArrayList<Camera> cameras = getCamerasFrom(cams);
        for (Camera camera : cameras)
            outputItems.add(mapOutputItemFrom(camera));
        return outputItems;
    }
}
