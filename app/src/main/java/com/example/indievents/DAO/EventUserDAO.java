package com.example.indievents.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.indievents.db.MiBD;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.EventStudio;
import com.example.indievents.pojo.EventUser;
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventUserDAO implements PojoDAO{
    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        EventUser e = (EventUser) obj;
        contentValues.put("idEvent" , e.getIdEvent());
        contentValues.put("idUser", e.getIdUser());

        return MiBD.getDB().insert("eventsUsers", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        EventUser e = (EventUser) obj;
        contentValues.put("idEvent" , e.getIdEvent());
        contentValues.put("idStudio", e.getIdUser());

        String condicion = "idEvent=" + String.valueOf(e.getIdEvent());

        int resultado = MiBD.getDB().update("eventsUsers", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        EventStudio e = (EventStudio) obj;
        String condicion = "idEvent=" + String.valueOf(e.getIdEvent());

        //Se borra el event indicado en el campo de texto
        MiBD.getDB().delete("eventsUsers", condicion, null);
    }

    @Override
    public Object search(Object obj) throws ParseException {
        EventStudio e = (EventStudio) obj;

        String condicion = "idEvent=" + String.valueOf(e.getIdEvent());

        String[] columnas = {
                "idEvent","idUser"
        };

        Cursor cursor = MiBD.getDB().query("eventsUsers", columnas, condicion, null, null, null, null);
        EventUser nuevoEventUser = null;
        if (cursor.moveToFirst()) {
            nuevoEventUser = new EventUser();
            nuevoEventUser.setIdEvent(cursor.getInt(0));
            nuevoEventUser.setIdUser(cursor.getInt(1));
        }
        return nuevoEventUser;
    }

    @Override
    public ArrayList getAll() throws ParseException {
        ArrayList<EventUser> listaEventUser = new ArrayList<EventUser>();
        String[] columnas = {
                "idEvent","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("eventsUsers", columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                EventUser nuevoEventUser = new EventUser();
                nuevoEventUser.setIdEvent(cursor.getInt(0));
                nuevoEventUser.setIdUser(cursor.getInt(1));

                listaEventUser.add(nuevoEventUser);
            } while(cursor.moveToNext());
        }
        return listaEventUser;
    }

    public ArrayList getEventsUser_U(User user) {
        ArrayList<EventUser> listaEventUser = new ArrayList<EventUser>();
        String condicion = "idUser=" + String.valueOf(user.getId());
        String[] columnas = {
                "idEvent","idUser"
        };
        Cursor cursor = MiBD.getDB().query("eventsUsers", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                EventUser nuevoEventUser = new EventUser();
                nuevoEventUser.setIdEvent(cursor.getInt(0));
                nuevoEventUser.setIdUser(cursor.getInt(1));

                listaEventUser.add(nuevoEventUser);

            } while (cursor.moveToNext());
        }
        return listaEventUser;
    }

    public ArrayList getEventsUser_E(Event event) {
        ArrayList<EventUser> listaEventUser = new ArrayList<EventUser>();
        String condicion = "idEvent=" + String.valueOf(event.getId());
        String[] columnas = {
                "idEvent","idUser"
        };
        Cursor cursor = MiBD.getDB().query("eventsUsers", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                EventUser nuevoEventUser = new EventUser();
                nuevoEventUser.setIdEvent(cursor.getInt(0));
                nuevoEventUser.setIdUser(cursor.getInt(1));

                listaEventUser.add(nuevoEventUser);

            } while (cursor.moveToNext());
        }
        return listaEventUser;
    }

    public ArrayList getEvents(User user) throws ParseException {
        ArrayList<EventUser> listaEventUser = getEventsUser_U(user);
        ArrayList<Event> listaEvents = new ArrayList<Event>();

        for (EventUser es : listaEventUser){
            Event e = new Event();
            e.setId(es.getIdEvent());
            e = (Event) MiBD.getInstance(null).getEventDAO().search(e);
            listaEvents.add(e);
        }

        return listaEvents;
    }

    public ArrayList getUsers(Event event){
        ArrayList<EventUser> listaEventUser = getEventsUser_E(event);
        ArrayList<User> listaUsers = new ArrayList<User>();

        for (EventUser es : listaEventUser){
            User s = new User();
            s.setId(es.getIdUser());
            s = (User) MiBD.getInstance(null).getUserDAO().search(s);
            listaUsers.add(s);
        }

        return listaUsers;
    }
}
