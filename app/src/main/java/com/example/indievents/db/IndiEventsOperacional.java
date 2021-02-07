package com.example.indievents.db;

import android.content.Context;

import com.example.indievents.pojo.Event;
import com.example.indievents.pojo.EventStudio;
import com.example.indievents.pojo.EventUser;
import com.example.indievents.pojo.Game;
import com.example.indievents.pojo.Studio;
import com.example.indievents.pojo.User;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

public class IndiEventsOperacional implements Serializable {
    private MiBD miBD;

    protected IndiEventsOperacional(Context context){
        miBD = MiBD.getInstance(context);
    }

    private static IndiEventsOperacional instance = null;

    //***************************************
    // Interfaz publica de la API de IndiEvents
    //***************************************

    // Constructor. Obtiene una instancia del mismo para operar
    public static IndiEventsOperacional getInstance(Context context) {
        if(instance == null) {
            instance = new IndiEventsOperacional(context);
        }
        return instance;
    }

    // Operacion Login: Verifica que el user existe y que su contraseña es correcta. Recibira un user
    // que solo contendrá el username y la password.
    public User login(User u){
        User aux = (User) miBD.getUserDAO().search(u);
        if(aux==null){
            return null;
        }else if (aux.getPassword().equals(u.getPassword())){
            return aux;
        }else{
            return null;
        }
    }

    public User comprobarRegistro(User u){
        User aux = (User) miBD.getUserDAO().search(u);
        if(aux==null)
            return null;
        else
            return aux;
    }

    public void registrarUsuario(User u){
        miBD.getUserDAO().add(u);
    }

    // Operacion changePassword: Cambia la password del cliente. Recibirá el cliente de la aplicación con la password cambiada.
    // Si devuelve un 1 es que ha verificado el cambio de password como correcto y todo ha ido bien, mientras que si devuelve
    // mientras que si devuelve un 0 no ha verificado el cambio de password como correcto y ha habido un error al cambiarlo.
    public int changePassword(User u){
        int resultado = miBD.getUserDAO().update(u);
        if (resultado==0) {
            return 0;
        } else {
            return 1;
        }
    }

    // Operacion getUsers: Obtiene un ArrayList de los users de un studio que recibe como parámetro
    public ArrayList<User> getUsers(Studio s){
        return miBD.getUserDAO().getUsers(s);
    }

    // Operacion getGames: Obtiene un ArrayList de los games de una studio que recibe como parámetro
    public ArrayList<Game> getGames(Studio s){
        return miBD.getGameDAO().getGames(s);
    }

    // Operacion getUsers: Obtiene un ArrayList de los users de un studio que recibe como parámetro
    public ArrayList<User> getStudios(){
        return miBD.getStudioDAO().getAll();
    }
    // Operacion getUsers: Obtiene un ArrayList de los users de un studio que recibe como parámetro
    public ArrayList<Event> getEvents() throws ParseException {
        return miBD.getEventDAO().getAll();
    }
    // Operacion getUsers: Obtiene un ArrayList de los users de un studio que recibe como parámetro
    public ArrayList<EventStudio> getEventsStudios() throws ParseException {
        return miBD.getEventStudioDAO().getAll();
    }
    // Operacion getUsers: Obtiene un ArrayList de los users de un studio que recibe como parámetro
    public ArrayList<EventUser> getEventsUsers() throws ParseException {
        return miBD.getEventUserDAO().getAll();
    }


    /* Operacion transferencia: Desde una cuenta hace transferencia a otra cuenta, siempre que en la cuenta origen haya dinero disponible.

       Restricciones:

         - La comprobacion de la existencia de la cuenta destino se realizará dentro del método. La cuenta de origen existe por defecto, ya que el alumno ha de poder seleccionarla.
         - En caso de no existir la cuenta destino se devolvera como entero un 1.
         - La fecha de la operación será la fecha del sistema. Recordar que es almacenada como un long.
         - No se permitirá realizar una transferencia si la cuenta se queda en negativo. En este caso se devolvera como entero un 2.
         - Solo se permiten movimiento en las cuentas locales al banco, por lo que ambas cuentas deben existir.
         - La operación se ha de ver reflejada en las dos cuentas: el descuento en una y el ingreso en otra.
         - El campo tipo en la tabla de movimientos indica como es el movimiento. 0 indica que es un descuento, 1 indica que es un ingreso y 2 indica que es un reintegro por un cajero.
         - El movimiento que viene como parametro en el metodo, que viene en la variable movimientoTransferencia ha de ser de tipo 0.
         - Si la operacion es correcta se devuelve un 0
    */

    /*public int transferencia(Movimiento movimientoCuentaOrigen, Movimiento movimientoCuentaDestino){
        if(movimientoCuentaOrigen.getCuentaOrigen().getSaldoActual() >= movimientoCuentaOrigen.getImporte()){
            movimientoCuentaOrigen.getCuentaOrigen().setSaldoActual(movimientoCuentaOrigen.getCuentaOrigen().getSaldoActual() - movimientoCuentaOrigen.getImporte());
            movimientoCuentaDestino.getCuentaOrigen().setSaldoActual(movimientoCuentaDestino.getCuentaOrigen().getSaldoActual() + movimientoCuentaDestino.getImporte());

            miBD.actualizarSaldo(movimientoCuentaOrigen.getCuentaOrigen());
            miBD.actualizarSaldo(movimientoCuentaDestino.getCuentaOrigen());

            movimientoCuentaOrigen.setImporte(movimientoCuentaOrigen.getImporte() * -1);
            miBD.insercionMovimiento(movimientoCuentaOrigen);
            miBD.insercionMovimiento(movimientoCuentaDestino);

            return 0;
        }
        return 2;
    }

    // Operacion getMovimientosTipo: Obtiene un ArrayList de los movimientos de un tipo específico de una cuenta que recibe como parámetro
    public ArrayList<Movimiento> getMovimientosTipo(Cuenta c, int tipo){
        return miBD.getMovimientoDAO().getMovimientosTipo(c, tipo);
    }*/
}
