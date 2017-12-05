package com.project.iago.getmyband.model;

import java.util.List;

/**
 * Created by Iago Note on 15/11/2017.
 */

public class Musico {

    private long ID;
    private String nome;
    private int idade;
    private int telefone;
    private List<Banda> bandas;


    public Musico(String nome, int idade, int telefone, List<Banda> bandas) {
        this.setNome(nome);
        this.setIdade(idade);
        this.setTelefone(telefone);
        this.setBandas(bandas);
    }

    public Musico() {

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }
}
