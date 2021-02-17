package com.example.indievents.pojo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {
    String nom, descripcio, web;
    Date fechaInici, fechaFinal;
    int id, idAdmin;
    ArrayList<Studio> studiosParticipants;
    ArrayList<User> developersParticipants;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Event(){
    }

    public Event(String nom, String descripcio, String web, String fechaInici, String fechaFinal, int idAdmin) throws ParseException {
        this.nom = nom;
        this.descripcio = descripcio;
        this.fechaInici = dateFormat.parse(fechaInici);
        this.fechaFinal = dateFormat.parse(fechaFinal);
        this.web = web;
        this.studiosParticipants = new ArrayList<>();
        this.developersParticipants = new ArrayList<>();
        this.idAdmin = idAdmin;
    }

    public Event(int id, String nom, String descripcio, String web, String fechaInici, String fechaFinal, int idAdmin) throws ParseException {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.fechaInici = dateFormat.parse(fechaInici);
        this.fechaFinal = dateFormat.parse(fechaFinal);
        this.web = web;
        this.studiosParticipants = new ArrayList<>();
        this.developersParticipants = new ArrayList<>();
        this.idAdmin = idAdmin;
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

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFechaIniciString(){
        return dateFormat.format(this.fechaInici);
    }

    public String getFechaFinalString(){
        return dateFormat.format(this.fechaFinal);
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public String toString() {
        return "EVENT: " + this.getNom();
    }
}
