package com.deuzex.seguros.database.handlers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deuzex.seguros.objects.Usuario;

import java.util.Vector;

public class DBHandlerUsuario {

    // TODO Table_Version: 6
    private static final String TABLE_NAME = "Usuarios";

    private static final String rowid = "ROWID";
    private static final String dni_usuario = "Dni";
    private static final String nombre_usuario = "Nombre";
    private static final String apellido_usuario = "Apellido";
    private static final String email_usuario = "Email";
    private static final String telefono_usuario = "Telefono";
    private static final String direccion_usuario = "Direccion";
    private static final String nrodireccion_usuario = "Nro";
    private static final String depto_usuario = "Depto";
    private static final String localidad_usuario = "Localidad";
    private static final String provincia_usuario = "Provincia";
    private static final String codpostal_usuario = "Cod_Postal";

    private static final int dni_usuario_index = 0;
    private static final int nombre_usuario_index = 1;
    private static final int apellido_usuario_index = 2;
    private static final int email_usuario_index = 3;
    private static final int telefono_usuario_index = 4;
    private static final int direccion_usuario_index = 5;
    private static final int nrodireccion_usuario_index = 6;
    private static final int depto_usuario_index = 7;
    private static final int localidad_usuario_index = 8;
    private static final int provincia_usuario_index = 9;
    private static final int codpostal_usuario_index = 10;

    private SQLiteDatabase db;
    public static boolean esNuevoUser;

    public DBHandlerUsuario(SQLiteDatabase writableDatabase) {
        this.db = writableDatabase;
    }

    // CREA Y DROPEA LA TABLA USUARIOS
    public static String getCreateQuery() {
		String query = "CREATE TABLE " + TABLE_NAME + " (" +
                dni_usuario + " TEXT, " +
                nombre_usuario + " TEXT, " +
                apellido_usuario + " TEXT, " +
                email_usuario + " TEXT, " +
                telefono_usuario + " TEXT, " +
                direccion_usuario + " TEXT, " +
                nrodireccion_usuario + " TEXT, " +
                depto_usuario + " TEXT, " +
                localidad_usuario + " TEXT, " +
                provincia_usuario + " TEXT, " +
                codpostal_usuario + " TEXT, " +
                "PRIMARY KEY (" + dni_usuario + "))";
        return query;
    }

    public static String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    private static final String[] allColumns = {
            dni_usuario, nombre_usuario, apellido_usuario, email_usuario, telefono_usuario,
            direccion_usuario, nrodireccion_usuario, depto_usuario, localidad_usuario,
            provincia_usuario, codpostal_usuario
    };

    private static final String[] onlyDni = {dni_usuario};

    public void addUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();

        esNuevoUser = true;
        values.put(dni_usuario, usuario.getDni());
        values.put(nombre_usuario, usuario.getNombre());
        values.put(apellido_usuario, usuario.getApellido());
        values.put(email_usuario, usuario.getEmail());
        values.put(telefono_usuario, usuario.getTelefono());
        values.put(direccion_usuario, usuario.getDireccion());
        values.put(nrodireccion_usuario, usuario.getNumDireccion());
        values.put(depto_usuario, usuario.getDepto());
        values.put(localidad_usuario, usuario.getLocalidad());
        values.put(provincia_usuario, usuario.getProvincia());
        values.put(codpostal_usuario, usuario.getCodPostal());
        long insertId = db.insert(TABLE_NAME, null, values);

        if (insertId == -1) {
            esNuevoUser = false;
            values = new ContentValues();

            values.put(nombre_usuario, usuario.getNombre());
            values.put(apellido_usuario, usuario.getApellido());
            values.put(email_usuario, usuario.getEmail());
            values.put(telefono_usuario, usuario.getTelefono());
            values.put(direccion_usuario, usuario.getDireccion());
            values.put(nrodireccion_usuario, usuario.getNumDireccion());
            values.put(depto_usuario, usuario.getDepto());
            values.put(localidad_usuario, usuario.getLocalidad());
            values.put(provincia_usuario, usuario.getProvincia());
            values.put(codpostal_usuario, usuario.getCodPostal());
            insertId = db.update(TABLE_NAME, values,
                    (dni_usuario + " =?"), new String[]{usuario.getDni()});
        }
        db.close();
    }

    // Recuperar un usuario por DNI
    public Usuario getUserByDni(String dni) {
        Usuario ret = null;

        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + dni_usuario + " IN (\'" + dni + "\')";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            ret = new Usuario(cursor.getString(dni_usuario_index));
        }
        db.close();
        return ret;
    }

    public String getFirstUser() {
        String query = "SELECT * FROM " + TABLE_NAME +
                " ORDER BY " + rowid + " ASC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        String ret = "";

        if (cursor.moveToFirst()) {
            ret = cursor.getString(dni_usuario_index);
        }
        db.close();
        return ret;
    }

    public Vector<Usuario> getAllUsuarios() {
        String query = "SELECT * FROM " + TABLE_NAME;
        Vector<Usuario> ret = new Vector<Usuario>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Usuario aux = new Usuario();
                aux.setDni(c.getString(DBHandlerUsuario.dni_usuario_index));
                aux.setNombre(c.getString(DBHandlerUsuario.nombre_usuario_index));
                aux.setApellido(c.getString(DBHandlerUsuario.apellido_usuario_index));
                aux.setEmail(c.getString(DBHandlerUsuario.email_usuario_index));
                aux.setTelefono(c.getString(DBHandlerUsuario.telefono_usuario_index));
                aux.setDireccion(c.getString(DBHandlerUsuario.direccion_usuario_index));
                aux.setNumDireccion(c.getString(DBHandlerUsuario.nrodireccion_usuario_index));
                aux.setDepto(c.getString(DBHandlerUsuario.depto_usuario_index));
                aux.setLocalidad(c.getString(DBHandlerUsuario.localidad_usuario_index));
                aux.setProvincia(c.getString(DBHandlerUsuario.provincia_usuario_index));
                aux.setCodPostal(c.getString(DBHandlerUsuario.codpostal_usuario_index));

                ret.add(aux);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ret;
    }

    public boolean deleteUsuario(String dni) {
        boolean bool = false;
        DBHandlerPoliza handlerPoliza = new DBHandlerPoliza(db);
        if (handlerPoliza.deletePolizabyDni(dni)) {
            int n = db.delete(TABLE_NAME, dni_usuario + "=" + dni, null);
            if (n == 1) {
                bool = true;
            } else {
                bool = false;
            }
        }
        return bool;
    }

    public boolean searchUserByDni(String dni) {
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + dni_usuario + " IN (\'" + dni + "\')";
        Cursor cursor = db.rawQuery(query, null);
        //db.close();
        return cursor.moveToFirst();
    }

    public Usuario getAllDataUser(String dni_user) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                dni_usuario + " IN (\'" + dni_user + "\')";
        Cursor cursor = db.rawQuery(query, null);
        Usuario usuario = new Usuario();

        if (cursor.moveToFirst()) {
            usuario.setDni(cursor.getString(dni_usuario_index));
            usuario.setNombre(cursor.getString(nombre_usuario_index));
            usuario.setApellido(cursor.getString(apellido_usuario_index));
            usuario.setEmail(cursor.getString(email_usuario_index));
            usuario.setDireccion(cursor.getString(direccion_usuario_index));
            usuario.setNumDireccion(cursor.getString(nrodireccion_usuario_index));
            usuario.setDepto(cursor.getString(depto_usuario_index));
            usuario.setTelefono(cursor.getString(telefono_usuario_index));
            usuario.setCodPostal(cursor.getString(codpostal_usuario_index));
            usuario.setLocalidad(cursor.getString(localidad_usuario_index));
            usuario.setProvincia(cursor.getString(provincia_usuario_index));
        }

        cursor.close();
        db.close();
        return usuario;
    }

    public Usuario setDatosUsuario(String dni) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                dni_usuario + " IN (\'" + dni + "\')";
        Cursor cursor = db.rawQuery(query, null);
        Usuario usu = new Usuario();
        if (cursor.moveToFirst()) {
            usu.setNombre(cursor.getString(nombre_usuario_index));
            usu.setApellido(cursor.getString(apellido_usuario_index));
        }
        cursor.close();
        db.close();
        return usu;
    }
}
