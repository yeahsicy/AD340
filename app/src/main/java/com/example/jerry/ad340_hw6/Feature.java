
package com.example.jerry.ad340_hw6;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feature {

    @SerializedName("PointCoordinate")
    @Expose
    private List<Double> pointCoordinate = null;
    @SerializedName("Cameras")
    @Expose
    private List<Camera> cameras = null;

    public List<Double> getPointCoordinate() {
        return pointCoordinate;
    }

    public void setPointCoordinate(List<Double> pointCoordinate) {
        this.pointCoordinate = pointCoordinate;
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

}
