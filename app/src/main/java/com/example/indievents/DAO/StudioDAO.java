package com.example.indievents.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.indievents.db.MiBD;
import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.util.ArrayList;

public class StudioDAO implements PojoDAO{

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Studio c = (Studio) obj;
        contentValues.put("nom" , c.getNom());
        contentValues.put("email", c.getEmail());
        contentValues.put("web", c.getWeb());
        contentValues.put("idAdmin", c.getStudioAdmin());
        return MiBD.getDB().insert("studios", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        Studio c = (Studio) obj;
        contentValues.put("nom" , c.getNom());
        contentValues.put("email", c.getEmail());
        contentValues.put("web", c.getWeb());
        contentValues.put("idAdmin", c.getStudioAdmin());

        String condicion = "id=" + String.valueOf(c.getId());

        int resultado = MiBD.getDB().update("studios", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        Studio c = (Studio) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el cliente indicado en el campo de texto
        MiBD.getDB().delete("studios", condicion, null);
    }

    @Override
    public Object search(Object obj) throws ParseException {
        Studio c = (Studio) obj;

        String condicion = "id=" + String.valueOf(c.getId());

        String[] columnas = {
                "id","nom","email","web","idAdmin"
        };

        Cursor cursor = MiBD.getDB().query("studios", columnas, condicion, null, null, null, null);
        Studio nuevoStudio = null;
        if (cursor.moveToFirst()) {
            nuevoStudio = new Studio();
            nuevoStudio.setId(cursor.getInt(0));
            nuevoStudio.setNom(cursor.getString(1));
            nuevoStudio.setEmail(cursor.getString(2));
            nuevoStudio.setWeb(cursor.getString(3));
            c.setStudioAdmin(cursor.getInt(4));

            // Obtenemos la lista de users que tiene el studio
            nuevoStudio.setDevelopers(MiBD.getInstance(null).getUserDAO().getUsers(nuevoStudio));
            // Obtenemos la lista de games que tiene el studio
            nuevoStudio.setJocs(MiBD.getInstance(null).getGameDAO().getGames(nuevoStudio));
            // Obtenemos la lista de events que tiene el studio
            nuevoStudio.setEventosActius(MiBD.getInstance(null).getEventStudioDAO().getEvents(nuevoStudio));


        }
        return nuevoStudio;
    }

    @Override
    public ArrayList getAll() throws ParseException {
        ArrayList<Studio> listaStudios = new ArrayList<Studio>();
        String[] columnas = {
                "id","nom","email","web","idAdmin"
        };
        Cursor cursor = MiBD.getDB().query("studios", columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Studio c = new Studio();
                c.setId(cursor.getInt(0));
                c.setNom(cursor.getString(1));
                c.setEmail(cursor.getString(2));
                c.setWeb(cursor.getString(3));
                c.setStudioAdmin(cursor.getInt(4));

                // Obtenemos la lista de users que tiene el studio
                c.setDevelopers(MiBD.getInstance(null).getUserDAO().getUsers(c));
                // Obtenemos la lista de games que tiene el studio
                c.setJocs(MiBD.getInstance(null).getGameDAO().getGames(c));
                // Obtenemos la lista de events que tiene el studio
                c.setEventosActius(MiBD.getInstance(null).getEventStudioDAO().getEvents(c));

                listaStudios.add(c);

            } while(cursor.moveToNext());
        }
        return listaStudios;
    }
}
