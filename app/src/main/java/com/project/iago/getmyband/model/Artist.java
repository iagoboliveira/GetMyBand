package com.project.iago.getmyband.model;

import java.util.List;

/**
 * Created by Iago Note on 15/11/2017.
 */

public class Artist {

    private Integer artist_id;
    private String artist_email;
    private String artist_name;
    private int artist_age;
    private int artist_phone;
    private List<Banda> artist_bandas;

    public Artist(Integer artist_id, String artist_email, String artist_name, int artist_age, int artist_phone, List<Banda> artist_bandas) {
        this.artist_id = artist_id;
        this.artist_email = artist_email;
        this.artist_name = artist_name;
        this.artist_age = artist_age;
        this.artist_phone = artist_phone;
        this.artist_bandas = artist_bandas;
    }

    public Artist() {

    }

    public Integer getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(Integer artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_email() {
        return artist_email;
    }

    public void setArtist_email(String artist_email) {
        this.artist_email = artist_email;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public int getArtist_age() {
        return artist_age;
    }

    public void setArtist_age(int artist_age) {
        this.artist_age = artist_age;
    }

    public int getArtist_phone() {
        return artist_phone;
    }

    public void setArtist_phone(int artist_phone) {
        this.artist_phone = artist_phone;
    }

    public List<Banda> getArtist_bandas() {
        return artist_bandas;
    }

    public void setArtist_bandas(List<Banda> artist_bandas) {
        this.artist_bandas = artist_bandas;
    }
}
