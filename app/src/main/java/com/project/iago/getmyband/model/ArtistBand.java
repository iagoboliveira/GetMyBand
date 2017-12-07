package com.project.iago.getmyband.model;

import java.util.List;

/**
 * Created by Iago Note on 15/11/2017.
 */

public class ArtistBand {

    private Integer artist_band_id;
    private String artist_band_name;
    private String artist_band_email;

    public ArtistBand(Integer artist_band_id, String artist_band_name, String artist_band_email) {
        this.artist_band_id = artist_band_id;
        this.artist_band_name = artist_band_name;
        this.artist_band_email = artist_band_email;
    }

    public ArtistBand() {

    }

    public Integer getArtist_band_id() {
        return artist_band_id;
    }

    public void setArtist_band_id(Integer artist_band_id) {
        this.artist_band_id = artist_band_id;
    }

    public String getArtist_band_name() {
        return artist_band_name;
    }

    public void setArtist_band_name(String artist_band_name) {
        this.artist_band_name = artist_band_name;
    }

    public String getArtist_band_email() {
        return artist_band_email;
    }

    public void setArtist_band_email(String artist_band_email) {
        this.artist_band_email = artist_band_email;
    }
}
