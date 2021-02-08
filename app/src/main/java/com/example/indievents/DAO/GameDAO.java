package com.example.indievents.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.indievents.db.MiBD;
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.util.ArrayList;

public class GameDAO implements PojoDAO{
    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Game c = (Game) obj;
        contentValues.put("titul" , c.getTitul());
        contentValues.put("descripcio", c.getDescripcio());
        contentValues.put("generes", c.getGeneres());
        contentValues.put("idStudio", c.getStudio().getId());
        return MiBD.getDB().insert("games", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        Game c = (Game) obj;
        contentValues.put("titul" , c.getTitul());
        contentValues.put("descripcio", c.getDescripcio());
        contentValues.put("generes", c.getGeneres());

        String condicion = "id=" + String.valueOf(c.getId());

        int resultado = MiBD.getDB().update("games", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        Game c = (Game) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el cliente indicado en el campo de texto
        MiBD.getDB().delete("games", condicion, null);
    }

    @Override
    public Object search(Object obj) throws ParseException {
        Game c = (Game) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        String[] columnas = {
                "id","titul","descripcio","generes","idStudio"
        };

        Cursor cursor = MiBD.getDB().query("games", columnas, condicion, null, null, null, null);
        Game nuevoGame = null;
        if (cursor.moveToFirst()) {
            nuevoGame = new Game();
            nuevoGame.setId(cursor.getInt(0));
            nuevoGame.setTitul(cursor.getString(1));
            nuevoGame.setDescripcio(cursor.getString(2));
            nuevoGame.setGeneres(cursor.getString(3));

            // Obtenemos el studio y lo asignamos
            Studio a = new Studio();
            a.setId(cursor.getInt(6));
            a = (Studio) MiBD.getInstance(null).getStudioDAO().search(a);
            c.setStudio(a);
        }
        return nuevoGame;
    }

    @Override
    public ArrayList getAll() throws ParseException {
        ArrayList<Game> listaGames = new ArrayList<Game>();
        String[] columnas = {
                "id","titul","descripcio","generes","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("games", columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Game nuevoGame = new Game();
                nuevoGame = new Game();
                nuevoGame.setId(cursor.getInt(0));
                nuevoGame.setTitul(cursor.getString(1));
                nuevoGame.setDescripcio(cursor.getString(2));
                nuevoGame.setGeneres(cursor.getString(3));

                // Obtenemos el studio y lo asignamos
                Studio a = new Studio();
                a.setId(cursor.getInt(6));
                a = (Studio) MiBD.getInstance(null).getStudioDAO().search(a);
                nuevoGame.setStudio(a);

            } while(cursor.moveToNext());
        }
        return listaGames;
    }

    public ArrayList getGames(Studio studio) {
        ArrayList<Game> listaGames = new ArrayList<Game>();
        String condicion = "idStudio=" + String.valueOf(studio.getId());
        String[] columnas = {
                "id", "titul", "descripcio", "generes", "idStudio"
        };
        Cursor cursor = MiBD.getDB().query("games", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Game nuevoGame = new Game();
                nuevoGame = new Game();
                nuevoGame.setId(cursor.getInt(0));
                nuevoGame.setTitul(cursor.getString(1));
                nuevoGame.setDescripcio(cursor.getString(2));
                nuevoGame.setGeneres(cursor.getString(3));

                nuevoGame.setStudio(studio);

                listaGames.add(nuevoGame);

            } while (cursor.moveToNext());
        }
        return listaGames;
    }
}
