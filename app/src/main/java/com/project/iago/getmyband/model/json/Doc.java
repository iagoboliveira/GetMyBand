package com.project.iago.getmyband.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("band")
    @Expose
    private String band;
    @SerializedName("fmRadios")
    @Expose
    private List<String> fmRadios = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public List<String> getFmRadios() {
        return fmRadios;
    }

    public void setFmRadios(List<String> fmRadios) {
        this.fmRadios = fmRadios;
    }

}