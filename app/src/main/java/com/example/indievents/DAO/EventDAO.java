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

public class EventDAO implements PojoDAO{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Event e = (Event) obj;
        contentValues.put("nom" , e.getNom());
        contentValues.put("descripcio", e.getDescripcio());
        contentValues.put("web", e.getWeb());
        contentValues.put("fechaInici", dateFormat.format(e.getFechaInici()));
        contentValues.put("fechaFinal", dateFormat.format(e.getFechaFinal()));
        contentValues.put("idAdmin", e.getIdAdmin());

        return MiBD.getDB().insert("events", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        Event e = (Event) obj;
        contentValues.put("nom" , e.getNom());
        contentValues.put("descripcio", e.getDescripcio());
        contentValues.put("web", e.getWeb());
        contentValues.put("fechaInici", dateFormat.format(e.getFechaInici()));
        contentValues.put("fechaFinal", dateFormat.format(e.getFechaFinal()));
        contentValues.put("idAdmin", e.getIdAdmin());

        String condicion = "id=" + String.valueOf(e.getId());

        int resultado = MiBD.getDB().update("events", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        Event e = (Event) obj;
        String condicion = "id=" + String.valueOf(e.getId());

        //Se borra el event indicado en el campo de texto
        MiBD.getDB().delete("events", condicion, null);
    }

    @Override
    public Object search(Object obj) throws ParseException {
        Event e = (Event) obj;

        String condicion = "id=" + String.valueOf(e.getId());

        String[] columnas = {
                "id","nom","descripcio","web","fechaInici","fechaFinal","idAdmin"
        };

        Cursor cursor = MiBD.getDB().query("events", columnas, condicion, null, null, null, null);
        Event nuevoEvent = null;
        if (cursor.moveToFirst()) {
            nuevoEvent = new Event();
            nuevoEvent.setId(cursor.getInt(0));
            nuevoEvent.setNom(cursor.getString(1));
            nuevoEvent.setDescripcio(cursor.getString(2));
            nuevoEvent.setWeb(cursor.getString(3));
            nuevoEvent.setFechaInici(dateFormat.parse(cursor.getString(4)));
            nuevoEvent.setFechaFinal(dateFormat.parse(cursor.getString(5)));
            nuevoEvent.setIdAdmin(cursor.getInt(6));

            nuevoEvent.setStudiosParticipants(MiBD.getInstance(null).getEventStudioDAO().getStudios(e));
            nuevoEvent.setDevelopersParticipants(MiBD.getInstance(null).getEventUserDAO().getUsers(e));
        }
        return nuevoEvent;
    }

    public Object searchAlt(Object obj) throws ParseException {
        Event e = (Event) obj;

        String condicion = "id=" + String.valueOf(e.getId());

        String[] columnas = {
                "id","nom","descripcio","web","fechaInici","fechaFinal","idAdmin"
        };

        Cursor cursor = MiBD.getDB().query("events", columnas, condicion, null, null, null, null);
        Event nuevoEvent = null;
        if (cursor.moveToFirst()) {
            nuevoEvent = new Event();
            nuevoEvent.setId(cursor.getInt(0));
            nuevoEvent.setNom(cursor.getString(1));
            nuevoEvent.setDescripcio(cursor.getString(2));
            nuevoEvent.setWeb(cursor.getString(3));
            nuevoEvent.setFechaInici(dateFormat.parse(cursor.getString(4)));
            nuevoEvent.setFechaFinal(dateFormat.parse(cursor.getString(5)));
            nuevoEvent.setIdAdmin(cursor.getInt(6));
        }
        return nuevoEvent;
    }

    @Override
    public ArrayList getAll() throws ParseException {
        ArrayList<Event> listaEvents = new ArrayList<Event>();
        String[] columnas = {
                "id","nom","descripcio","web","fechaInici","fechaFinal","idAdmin"
        };
        Cursor cursor = MiBD.getDB().query("events", columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Event nuevoEvent = new Event();
                nuevoEvent.setId(cursor.getInt(0));
                nuevoEvent.setNom(cursor.getString(1));
                nuevoEvent.setDescripcio(cursor.getString(2));
                nuevoEvent.setWeb(cursor.getString(3));
                nuevoEvent.setFechaInici(dateFormat.parse(cursor.getString(4)));
                nuevoEvent.setFechaFinal(dateFormat.parse(cursor.getString(5)));
                nuevoEvent.setIdAdmin(cursor.getInt(6));

                nuevoEvent.setStudiosParticipants(MiBD.getInstance(null).getEventStudioDAO().getStudios(nuevoEvent));
                nuevoEvent.setDevelopersParticipants(MiBD.getInstance(null).getEventUserDAO().getUsers(nuevoEvent));

                listaEvents.add(nuevoEvent);
            } while(cursor.moveToNext());
        }
        return listaEvents;
    }

    public ArrayList getAllIdAdmin(Object obj) throws ParseException {
        ArrayList<Event> listaEvents = new ArrayList<Event>();

        User u = (User)obj;
        String condicion = "idAdmin=" + String.valueOf(u.getId());
        String[] columnas = {
                "id","nom","descripcio","web","fechaInici","fechaFinal","idAdmin"
        };
        Cursor cursor = MiBD.getDB().query("events", columnas, condicion, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Event nuevoEvent = new Event();
                nuevoEvent.setId(cursor.getInt(0));
                nuevoEvent.setNom(cursor.getString(1));
                nuevoEvent.setDescripcio(cursor.getString(2));
                nuevoEvent.setWeb(cursor.getString(3));
                nuevoEvent.setFechaInici(dateFormat.parse(cursor.getString(4)));
                nuevoEvent.setFechaFinal(dateFormat.parse(cursor.getString(5)));
                nuevoEvent.setIdAdmin(cursor.getInt(6));

                nuevoEvent.setStudiosParticipants(MiBD.getInstance(null).getEventStudioDAO().getStudios(nuevoEvent));
                nuevoEvent.setDevelopersParticipants(MiBD.getInstance(null).getEventUserDAO().getUsers(nuevoEvent));

                listaEvents.add(nuevoEvent);
            } while(cursor.moveToNext());
        }
        return listaEvents;
    }


}
