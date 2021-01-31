package com.example.indievents.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    int id;
    String titul, descripcio;
    String generes;
    Studio studio;

    public Game(){
    }

    public Game(int id, String titul, String descripcio, String generes, Studio studio) {
        this.id = id;
        this.titul = titul;
        this.descripcio = descripcio;
        this.generes = generes;
        this.studio = studio;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getGeneres() {
        return generes;
    }

    public void setGeneres(String generes) {
        this.generes = generes;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GAME: " + this.getTitul();
    }
}
