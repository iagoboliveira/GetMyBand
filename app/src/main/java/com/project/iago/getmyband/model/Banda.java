package com.project.iago.getmyband.model;


public class Banda {

    public Banda(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;
    }

    private String nome;
private String genero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}