
package com.chakaseptember.forecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Generated with http://www.jsonschema2pojo.org/
 *
 */
public class Snow {

    @SerializedName("3h")
    @Expose
    private double _3h;

    public double get3h() {
        return _3h;
    }

    public void set3h(double _3h) {
        this._3h = _3h;
    }

}
