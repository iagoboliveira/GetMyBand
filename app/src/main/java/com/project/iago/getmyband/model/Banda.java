package com.project.iago.getmyband.model;

import java.io.Serializable;

public class Banda implements Serializable{

       public String nome;
       public String genero;

       public Banda(String nome, String genero) {
           this.nome = nome;
           this.genero = genero;
       }

       @Override
       public String toString() {
           return nome + " : "+ genero;
       }
   }