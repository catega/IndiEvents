package com.example.indievents.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    int id;
    String nom, username, password, email;
    boolean isDev, isOrganitzador;
    Studio studio;
    ArrayList<Event> eventosEnSolitari;
    ArrayList<Event> eventosCreats;

    public User(){
    }

    public User(int id, String nom, String username, String password, String email, boolean isDev) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isDev = isDev;
        this.eventosEnSolitari = new ArrayList<>();
        this.eventosCreats = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDev() {
        return isDev;
    }

    public void setDev(boolean dev) {
        isDev = dev;
    }

    public boolean isOrganitzador() {
        return isOrganitzador;
    }

    public void setOrganitzador(boolean organitzador) {
        isOrganitzador = organitzador;
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

    public ArrayList<Event> getEventosEnSolitari() {
        return eventosEnSolitari;
    }

    public void setEventosEnSolitari(ArrayList<Event> eventosEnSolitari) {
        this.eventosEnSolitari = eventosEnSolitari;
    }

    public ArrayList<Event> getEventosCreats() {
        return eventosCreats;
    }

    public void setEventosCreats(ArrayList<Event> eventosCreats) {
        this.eventosCreats = eventosCreats;
    }

    public void addEventoParticipat(Event e){
        if (isDev())
            this.eventosEnSolitari.add(e);
    }

    public void addEventoCreat(Event e){
        if (isOrganitzador())
            this.eventosCreats.add(e);
    }

    @Override
    public String toString() {
        return "USER: " + this.getUsername();
    }
}
