package com.example.indievents.pojo;

import java.io.Serializable;

public class EventUser implements Serializable {
    int idEvent, idUser;

    public EventUser() {
    }

    public EventUser(int idEvent, int idUser) {
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "EventUser{" +
                "idEvent=" + idEvent +
                ", idUser=" + idUser +
                '}';
    }
}
