package com.example.indievents.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.indievents.DAO.GameDAO;
import com.example.indievents.DAO.StudioDAO;
import com.example.indievents.DAO.UserDAO;

import java.io.Serializable;

public class MiBD extends SQLiteOpenHelper implements Serializable {
    private static SQLiteDatabase db;
    //nombre de la base de datos
    private static final String database = "IndiEvents";
    //versión de la base de datos
    private static final int version = 3;
    //Instrucción SQL para crear la tabla de Users
    private String sqlCreacionUsers = "CREATE TABLE users ( id INTEGER PRIMARY KEY AUTOINCREMENT, nom STRING, username STRING, " +
            "password STRING, email STRING, dev INTEGER DEFAULT 0, organizador INTEGER DEFAULT 0, idStudio INTEGER);";
    //Instruccion SQL para crear la tabla de Studios
    private String sqlCreacionStudios = "CREATE TABLE studios ( id INTEGER PRIMARY KEY AUTOINCREMENT, nom STRING, email STRING, " +
            "web STRING);" ;
    //Instruccion SQL para crear la tabla de Games
    private String sqlCreacionGames = "CREATE TABLE games ( id INTEGER PRIMARY KEY AUTOINCREMENT, titul STRING, descripcio STRING," +
            " generes STRING, idStudio INTEGER);";


    private static MiBD instance = null;

    private static UserDAO userDAO;
    private static StudioDAO studioDAO;
    private static GameDAO gameDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public StudioDAO getStudioDAO() {
        return studioDAO;
    }

    public GameDAO getGameDAO() {
        return gameDAO;
    }


    public static MiBD getInstance(Context context) {
        if(instance == null) {
            instance = new MiBD(context);
            db = instance.getWritableDatabase();
            userDAO = new UserDAO();
            studioDAO = new StudioDAO();
            gameDAO = new GameDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB(){
        return db;
    }
    public static void closeDB(){db.close();};

    /**
     * Constructor de clase
     * */
    public MiBD(Context context) {
        super( context, database, null, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionUsers);
        db.execSQL(sqlCreacionStudios);
        db.execSQL(sqlCreacionGames);

        insercionDatos(db);
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion  );
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS users" );
            db.execSQL( "DROP TABLE IF EXISTS studios" );
            db.execSQL( "DROP TABLE IF EXISTS games" );

            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionUsers);
            db.execSQL(sqlCreacionStudios);
            db.execSQL(sqlCreacionGames);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
        }
    }

    /*public void insercionMovimiento(Movimiento m){
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, " +
                m.getTipo()+", "+m.getFechaOperacion().getTime()+", '"+m.getDescripcion()+"', "+m.getImporte()+", "+m.getCuentaOrigen().getId()+", "+m.getCuentaDestino().getId()+");");
    }
    public void actualizarSaldo(Cuenta c){
        db.execSQL("UPDATE cuentas SET saldoactual= "+c.getSaldoActual()+" WHERE banco='"+c.getBanco()+"' AND sucursal='"+c.getSucursal()+"' AND dc='"+c.getDc()+"' AND numerocuenta='"+c.getNumeroCuenta()+"';");
    }

    public boolean existeCuenta(String banco,String sucursal,String dc,String numCuenta){
        String sql="SELECT numerocuenta FROM cuentas WHERE banco='"+banco+"' AND sucursal='"+sucursal+"' AND dc='"+dc+"' AND numerocuenta='"+numCuenta+"';";
        Cursor c = db.rawQuery(sql,null);
        if (c.getCount() > 0) {
            return true;
        }
        return false;
    }*/

    private void insercionDatos(SQLiteDatabase db){
        // Insertamos los users
        db.execSQL("INSERT INTO users(id, nom, username, password, email, dev, organizador, idStudio) VALUES (1, 'Carlos Teruel Garcia', 'Katega', '1234', 'cartegar@gmail.com', 1, 0, 1);");
        db.execSQL("INSERT INTO users(id, nom, username, password, email, dev, organizador, idStudio) VALUES (2, 'Dragos Bogdan', 'Dragos', '1234', 'dragos@gmail.com', 1, 0, 1);");
        db.execSQL("INSERT INTO users(id, nom, username, password, email, dev, organizador, idStudio) VALUES (3, 'Bisan Teeko', 'Teeko', '1234', 'bisanteeko@gmail.com', 1, 0, 2);");

        // Insertamos los studios
        db.execSQL("INSERT INTO studios (id, nom, email, web) VALUES (1, 'Badana Tales', 'badanatales@gmail.com', 'www.badanatales.com');");
        db.execSQL("INSERT INTO studios (id, nom, email, web) VALUES (2, 'Birloncho Games', 'birlonchogames@gmail.com', 'www.birloncho.com');");

        // Insertamos los games
        db.execSQL("INSERT INTO games (id, titul, descripcio, generes, idStudio) VALUES (1, 'Valkyries to Valhalla', 'Un grupo de valkyrias que quieren llegar al Valhalla. Para ello deberán recorrer los diferentes mundos de la mitología nórdica.', 'Accion, Plataformas, Puzzles, Co-op', 1);");
        db.execSQL("INSERT INTO games (id, titul, descripcio, generes, idStudio) VALUES (2, 'TAP', 'Un grupo de chicas se adentra en un mundo virtual en el que deben aguantar un tiempo definido en cada una de las pantallas.', 'Accion, Arcade, Mobil', 1);");
        db.execSQL("INSERT INTO games (id, titul, descripcio, generes, idStudio) VALUES (3, 'Bola de algo', 'Una bola de algo que hace cosas.', 'Accion, Plataformas', 2);");
    }
}