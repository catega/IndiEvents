package com.example.indievents.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Studio implements Serializable {
    int id;
    String nom, email, web;
    User studioAdmin;
    ArrayList<User> developers;
    ArrayList<Event> eventosActius;
    ArrayList<Game> jocs;

    public Studio(){
        super();
    }

    public Studio(int id, String nom, String email, String web, User studioAdmin){
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.web = web;
        this.studioAdmin = studioAdmin;
        this.developers = new ArrayList<User>();
        this.eventosActius = new ArrayList<Event>();
        this.jocs = new ArrayList<Game>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public User getStudioAdmin() {
        return studioAdmin;
    }

    public void setStudioAdmin(User studioAdmin) {
        this.studioAdmin = studioAdmin;
    }

    public ArrayList<User> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<User> developers) {
        this.developers = developers;
    }

    public ArrayList<Event> getEventosActius() {
        return eventosActius;
    }

    public void setEventosActius(ArrayList<Event> eventosActius) {
        this.eventosActius = eventosActius;
    }

    public ArrayList<Game> getJocs() {
        return jocs;
    }

    public void setJocs(ArrayList<Game> jocs) {
        this.jocs = jocs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addDev(User u){
        this.getDevelopers().add(u);
    }

    public void addEvent(Event e){
        this.getEventosActius().add(e);
    }

    public void addGame(Game g){
        this.getJocs().add(g);
    }

    @Override
    public String toString() {
        return "STUDIO: " + this.getNom();
    }
}
