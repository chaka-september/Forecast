
package com.chakaseptember.forecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Generated with http://www.jsonschema2pojo.org/
 *
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}
