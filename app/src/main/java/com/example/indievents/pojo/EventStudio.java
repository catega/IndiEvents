package com.example.indievents.pojo;

import java.io.Serializable;

public class EventStudio implements Serializable {
    int idEvent, idStudio;

    public EventStudio() {
    }

    public EventStudio(int idEvent, int idStudio) {
        this.idEvent = idEvent;
        this.idStudio = idStudio;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(int idStudio) {
        this.idStudio = idStudio;
    }

    @Override
    public String toString() {
        return "EventStudio{" +
                "idEvent=" + idEvent +
                ", idStudio=" + idStudio +
                '}';
    }
}
