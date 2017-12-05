package com.project.iago.getmyband.model.json;

/**
 * Created by Iago Note on 05/12/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LastFM_JSON {
    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
