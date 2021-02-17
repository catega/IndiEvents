package com.example.indievents.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.indievents.db.MiBD;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.EventStudio;
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventStudioDAO implements PojoDAO{
    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        EventStudio e = (EventStudio) obj;
        contentValues.put("idEvent" , e.getIdEvent());
        contentValues.put("idStudio", e.getIdStudio());

        return MiBD.getDB().insert("eventsStudios", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        EventStudio e = (EventStudio) obj;
        contentValues.put("idEvent" , e.getIdEvent());
        contentValues.put("idStudio", e.getIdStudio());

        String condicion = "idEvent=" + String.valueOf(e.getIdEvent());

        int resultado = MiBD.getDB().update("eventsStudios", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        EventStudio e = (EventStudio) obj;
        String condicion = "idEvent=" + String.valueOf(e.getIdEvent());

        //Se borra el event indicado en el campo de texto
        MiBD.getDB().delete("eventsStudios", condicion, null);
    }

    @Override
    public Object search(Object obj) throws ParseException {
        EventStudio e = (EventStudio) obj;

        String condicion = "idEvent=" + String.valueOf(e.getIdEvent()) + " AND idStudio=" + String.valueOf(e.getIdStudio());

        String[] columnas = {
                "idEvent","idStudio"
        };

        Cursor cursor = MiBD.getDB().query("eventsStudios", columnas, condicion, null, null, null, null);
        EventStudio nuevoEventStudio = null;
        if (cursor.moveToFirst()) {
            nuevoEventStudio = new EventStudio();
            nuevoEventStudio.setIdEvent(cursor.getInt(0));
            nuevoEventStudio.setIdStudio(cursor.getInt(1));
        }
        return nuevoEventStudio;
    }

    @Override
    public ArrayList getAll() throws ParseException {
        ArrayList<EventStudio> listaEventsStudios = new ArrayList<EventStudio>();
        String[] columnas = {
                "idEvent","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("eventsStudios", columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                EventStudio nuevoEventStudio = new EventStudio();
                nuevoEventStudio.setIdEvent(cursor.getInt(0));
                nuevoEventStudio.setIdStudio(cursor.getInt(1));

                listaEventsStudios.add(nuevoEventStudio);
            } while(cursor.moveToNext());
        }
        return listaEventsStudios;
    }

    public ArrayList getEventsStudio_S(Studio studio) {
        ArrayList<EventStudio> listaEventStudio = new ArrayList<EventStudio>();
        String condicion = "idStudio=" + String.valueOf(studio.getId());
        String[] columnas = {
                "idEvent","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("eventsStudios", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                EventStudio nuevoEventStudio = new EventStudio();
                nuevoEventStudio.setIdEvent(cursor.getInt(0));
                nuevoEventStudio.setIdStudio(cursor.getInt(1));

                listaEventStudio.add(nuevoEventStudio);

            } while (cursor.moveToNext());
        }
        return listaEventStudio;
    }

    public ArrayList getEventsStudio_E(Event event) {
        ArrayList<EventStudio> listaEventStudio = new ArrayList<EventStudio>();
        String condicion = "idEvent=" + String.valueOf(event.getId());
        String[] columnas = {
                "idEvent","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("eventsStudios", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                EventStudio nuevoEventStudio = new EventStudio();
                nuevoEventStudio.setIdEvent(cursor.getInt(0));
                nuevoEventStudio.setIdStudio(cursor.getInt(1));

                listaEventStudio.add(nuevoEventStudio);

            } while (cursor.moveToNext());
        }
        return listaEventStudio;
    }

    public ArrayList getEvents(Studio studio) throws ParseException {
        ArrayList<EventStudio> listaEventStudio = getEventsStudio_S(studio);
        ArrayList<Event> listaEvents = new ArrayList<Event>();

        for (EventStudio es : listaEventStudio){
            Event e = new Event();
            e.setId(es.getIdEvent());
            e = (Event) MiBD.getInstance(null).getEventDAO().searchAlt(e);
            listaEvents.add(e);
        }

        return listaEvents;
    }

    public ArrayList getStudios(Event event) throws ParseException {
        ArrayList<EventStudio> listaEventStudio = getEventsStudio_E(event);
        ArrayList<Studio> listaStudios = new ArrayList<Studio>();

        for (EventStudio es : listaEventStudio){
            Studio s = new Studio();
            s.setId(es.getIdStudio());
            s = (Studio) MiBD.getInstance(null).getStudioDAO().search(s);
            listaStudios.add(s);
        }

        return listaStudios;
    }
}
