package com.example.indievents.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.indievents.db.MiBD;
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.text.ParseException;
import java.util.ArrayList;

public class UserDAO implements PojoDAO{
    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        User c = (User) obj;
        contentValues.put("nom" , c.getNom());
        contentValues.put("username", c.getUsername());
        contentValues.put("password", c.getPassword());
        contentValues.put("email", c.getEmail());
        contentValues.put("dev", c.isDev() ? 1 : 0);
        contentValues.put("organizador", 0);
        contentValues.putNull("idStudio");
        return MiBD.getDB().insert("users", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        User c = (User) obj;
        contentValues.put("nom" , c.getNom());
        contentValues.put("username", c.getUsername());
        contentValues.put("password", c.getPassword());
        contentValues.put("email", c.getEmail());

        String condicion = "id=" + String.valueOf(c.getId());

        int resultado = MiBD.getDB().update("users", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        User c = (User) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el user indicado en el campo de texto
        MiBD.getDB().delete("users", condicion, null);
    }

    @Override
    public Object search(Object obj) throws ParseException {
        User c = (User) obj;

        String condicion = "";
        if(TextUtils.isEmpty(c.getUsername())){
            condicion = "id=" + String.valueOf(c.getId());
        }else{
            condicion = "username=" + "'" + c.getUsername() + "'";
        }

        String[] columnas = {
                "id","nom","username","password","email","dev","organizador","idStudio"
        };

        Cursor cursor = MiBD.getDB().query("users", columnas, condicion, null, null, null, null);
        User nuevoUser = null;
        if (cursor.moveToFirst()) {
            nuevoUser = new User();
            nuevoUser.setId(cursor.getInt(0));
            nuevoUser.setNom(cursor.getString(1));
            nuevoUser.setUsername(cursor.getString(2));
            nuevoUser.setPassword(cursor.getString(3));
            nuevoUser.setEmail(cursor.getString(4));
            nuevoUser.setDev(cursor.getInt(5) != 0);
            nuevoUser.setOrganitzador(cursor.getInt(6) != 0);

            // Obtenemos el studio y lo asignamos
            Studio a = new Studio();
            a.setId(cursor.getInt(7));
            a = (Studio) MiBD.getInstance(null).getStudioDAO().search(a);
            nuevoUser.setStudio(a);

            // Obtenemos la lista de events que tiene el studio
            nuevoUser.setEventosEnSolitari(MiBD.getInstance(null).getEventUserDAO().getEvents(nuevoUser));
        }
        return nuevoUser;
    }

    @Override
    public ArrayList getAll() throws ParseException {
        ArrayList<User> listaUsers = new ArrayList<User>();
        String[] columnas = {
                "id","nom","username","password","email","dev","organizador","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("users", columnas, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                User nuevoUser = new User();
                nuevoUser = new User();
                nuevoUser.setId(cursor.getInt(0));
                nuevoUser.setNom(cursor.getString(1));
                nuevoUser.setUsername(cursor.getString(2));
                nuevoUser.setPassword(cursor.getString(3));
                nuevoUser.setEmail(cursor.getString(4));
                nuevoUser.setDev(cursor.getInt(5) != 0);
                nuevoUser.setOrganitzador(cursor.getInt(6) != 0);

                // Obtenemos el studio y lo asignamos
                Studio a = new Studio();
                a.setId(cursor.getInt(6));
                a = (Studio) MiBD.getInstance(null).getStudioDAO().search(a);
                nuevoUser.setStudio(a);

                // Obtenemos la lista de events que tiene el studio
                nuevoUser.setEventosEnSolitari(MiBD.getInstance(null).getEventUserDAO().getEvents(nuevoUser));

                listaUsers.add(nuevoUser);

            } while(cursor.moveToNext());
        }
        return listaUsers;
    }

    public ArrayList getUsers(Studio studio) {
        ArrayList<User> listaUsers = new ArrayList<User>();
        String condicion = "idStudio=" + String.valueOf(studio.getId());
        String[] columnas = {
                "id","nom","username","password","email","dev","organizador","idStudio"
        };
        Cursor cursor = MiBD.getDB().query("users", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                User nuevoUser = new User();
                nuevoUser.setId(cursor.getInt(0));
                nuevoUser.setNom(cursor.getString(1));
                nuevoUser.setUsername(cursor.getString(2));
                nuevoUser.setPassword(cursor.getString(3));
                nuevoUser.setEmail(cursor.getString(4));
                nuevoUser.setDev(cursor.getInt(5) != 0);
                nuevoUser.setOrganitzador(cursor.getInt(6) != 0);

                nuevoUser.setStudio(studio);

                listaUsers.add(nuevoUser);

            } while (cursor.moveToNext());
        }
        return listaUsers;
    }
}
