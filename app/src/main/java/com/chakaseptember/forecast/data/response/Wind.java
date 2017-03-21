
package com.chakaseptember.forecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Generated with http://www.jsonschema2pojo.org/
 *
 */
public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private double deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

}
