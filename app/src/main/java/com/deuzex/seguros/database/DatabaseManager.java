package com.deuzex.seguros.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.deuzex.seguros.database.handlers.DBHandlerPoliza;
import com.deuzex.seguros.database.handlers.DBHandlerSiniestro;
import com.deuzex.seguros.database.handlers.DBHandlerUsuario;
import com.deuzex.seguros.objects.Poliza;
import com.deuzex.seguros.objects.Siniestro;
import com.deuzex.seguros.objects.Usuario;

import java.util.List;
import java.util.Vector;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DB_VERSION = 13;
    private static final String DB_NAME = "DEMO_SEGUROS_DB";
    private DatabaseManager dbm;
    private String DB_PATH = "";

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
        dbm = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando base de datos..");
        // LAMA A CADA HANDLER DE CADA TABLA PARA CREARLA
        db.execSQL(DBHandlerUsuario.getCreateQuery());
        db.execSQL(DBHandlerPoliza.getCreateQuery());
        db.execSQL(DBHandlerSiniestro.getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(this.getClass().toString(), "Dropeando tablas..");
        // TUMBA LAS TABLAS Y LAS VUELVE A CREAR
        db.execSQL(DBHandlerUsuario.getDropQuery());
        db.execSQL(DBHandlerPoliza.getDropQuery());
        db.execSQL(DBHandlerSiniestro.getDropQuery());
        onCreate(db);
    }

    //////////////////////////////// USUARIO //////////////////////////////////

    public void addUsuario(final Usuario usuario) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        handlerUsuario.addUsuario(usuario);
    }

    public Vector<Usuario> listAllUsuarios() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        return handlerUsuario.getAllUsuarios();
    }

    public boolean searchUserByDni(String dni) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        return handlerUsuario.searchUserByDni(dni);
    }

    public Usuario getAllDataUser(String dni_user) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        return handlerUsuario.getAllDataUser(dni_user);
    }

    public boolean deleteUser(String dni) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        return handlerUsuario.deleteUsuario(dni);
    }

    public String getFirstUser() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        return handlerUsuario.getFirstUser();
    }

    /////////////////// POLIZAS DE SEGURO ///////////////////////////

    public void addPoliza(final Poliza poliza) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        handlerPoliza.addPoliza(poliza);
    }

    public boolean deletePoliza(String nro) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        return handlerPoliza.deletePolizabyNroPoliza(nro);
    }

    public Usuario setDatosUsuario(String dni) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerUsuario handlerUsuario = new DBHandlerUsuario(db);

        return handlerUsuario.setDatosUsuario(dni);
    }

    public Vector<Poliza> listAllPolizas() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        return handlerPoliza.getAllPolizas();
    }

    public Vector<Poliza> getPolizaByDni(String dni) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH, null,
                SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        return handlerPoliza.getPolizaByDni(dni);
    }

    public boolean searchPolizaByNum(int numPoliza) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        return handlerPoliza.searchPolizaByNum(numPoliza);
    }

    public Poliza getAllDataPoliza(int numero_poliza) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        return handlerPoliza.getAllDataPoliza(numero_poliza);
    }

    ////////////// SINIESTROS ///////////////////////
    public void addSiniestro(final Siniestro siniestro) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerSiniestro handlerSiniestro = new DBHandlerSiniestro(db);

        handlerSiniestro.addSiniestro(siniestro);
    }

    public List<String> getNumPolizas(String dni) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                DB_PATH, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);

        return handlerPoliza.getNumPolizas(dni);
    }
}
