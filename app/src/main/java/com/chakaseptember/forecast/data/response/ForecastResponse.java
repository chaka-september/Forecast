package com.chakaseptember.forecast.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Generated with http://www.jsonschema2pojo.org/
 *
 */

public class ForecastResponse {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.chakaseptember.forecast.data.response.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.chakaseptember.forecast.data.response.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.chakaseptember.forecast.data.response.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
