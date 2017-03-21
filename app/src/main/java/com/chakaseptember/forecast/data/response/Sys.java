
package com.chakaseptember.forecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Generated with http://www.jsonschema2pojo.org/
 *
 */
public class Sys {

    @SerializedName("pod")
    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
