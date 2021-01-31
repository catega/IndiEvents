package com.example.indievents.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {
    String nom, descripcio, direccio, web;
    Date fechaInici, fechaFinal;
    float preu;
    ArrayList<Studio> studiosParticipants = new ArrayList<>();
    ArrayList<User> developersParticipants = new ArrayList<>();

    public Event(){
    }

    public Event(String nom, String descripcio, String direccio, String web, Date fechaInici, Date fechaFinal, float preu) {
        this.nom = nom;
        this.descripcio = descripcio;
        this.direccio = direccio;
        this.fechaInici = fechaInici;
        this.fechaFinal = fechaFinal;
        this.preu = preu;
        this.web = web;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public Date getFechaInici() {
        return fechaInici;
    }

    public void setFechaInici(Date fechaInici) {
        this.fechaInici = fechaInici;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public ArrayList<Studio> getStudiosParticipants() {
        return studiosParticipants;
    }

    public void setStudiosParticipants(ArrayList<Studio> studiosParticipants) {
        this.studiosParticipants = studiosParticipants;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public ArrayList<User> getDevelopersParticipants() {
        return developersParticipants;
    }

    public void setDevelopersParticipants(ArrayList<User> developersParticipants) {
        this.developersParticipants = developersParticipants;
    }

    public void addStudio(Studio s){
        this.getStudiosParticipants().add(s);
    }

    public boolean addDev(User u){
        if (u.isDev()){
            this.getDevelopersParticipants().add(u);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "EVENT: " + this.getNom();
    }
}
