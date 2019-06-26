package com.deuzex.seguros.database.handlers;

import android.accessibilityservice.GestureDescription;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deuzex.seguros.objects.Poliza;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DBHandlerPoliza {

    // Nombre de la tabla
    private static final String TABLE_NAME = "Polizas";
    // Columnas
    private static final String dni_usuario = "DNI_Usuario";
    private static final String num_poliza = "Num_Poliza";
    private static final String fecha_vig = "Fecha_vigencia";
    private static final String dias_vig = "Dias_vigencia";
    private static final String tipo_poliza = "Tipo_poliza";
    private static final String tipo_pago = "Metodo_pago";

    private static final int num_poliza_index = 0;
    private static final int dni_usuario_index = 1;
    private static final int fecha_vig_index = 2;
    private static final int dias_vig_index = 3;
    private static final int tipo_poliza_index = 4;
    private static final int tipo_pago_index = 5;
    private SQLiteDatabase db;

    public DBHandlerPoliza(SQLiteDatabase writableDatabase) {
        this.db = writableDatabase;
    }

    // Método que crea la tabla
    public static String getCreateQuery() {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                num_poliza + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                dni_usuario + " TEXT, " +
                fecha_vig + " TEXT, " +
                dias_vig + " TEXT, " +
                tipo_poliza + " TEXT, " +
                tipo_pago + " TEXT)";
        return query;
    }

    public static String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public void addPoliza(Poliza poliza) {
        ContentValues values = new ContentValues();

        values.put(dni_usuario, poliza.getDniUsuario());
        values.put(fecha_vig, poliza.getFechaVig());
        values.put(dias_vig, poliza.getVigencia());
        values.put(tipo_poliza, poliza.getTipoPoliza());
        values.put(tipo_pago, poliza.getTipoPago());
        long insertId = db.insert(TABLE_NAME, null, values);

        if (insertId == -1) {
            values = new ContentValues();

            values.put(dni_usuario, poliza.getDniUsuario());
            values.put(fecha_vig, poliza.getFechaVig());
            values.put(dias_vig, poliza.getVigencia());
            values.put(tipo_poliza, poliza.getTipoPoliza());
            values.put(tipo_pago, poliza.getTipoPago());
            insertId = db.update(TABLE_NAME, values,
                    (num_poliza + "=?"), new String[]{String.valueOf(poliza.getNumPoliza())});
        }
        db.close();
    }

    public Vector<Poliza> getAllPolizas() {
        String query = "SELECT * FROM " + TABLE_NAME;
        Vector<Poliza> ret = new Vector<Poliza>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Poliza aux = new Poliza();
                aux.setDniUsuario(c.getString(DBHandlerPoliza.dni_usuario_index));
                aux.setNumPoliza(c.getInt(DBHandlerPoliza.num_poliza_index));
                aux.setFechaVig(c.getString(DBHandlerPoliza.fecha_vig_index));
                aux.setTipoPoliza(c.getString(DBHandlerPoliza.tipo_poliza_index));
                aux.setTipoPago(c.getString(DBHandlerPoliza.tipo_pago_index));
                aux.setVigencia(c.getString(DBHandlerPoliza.dias_vig_index));

                ret.add(aux);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return ret;
    }

    public Vector<Poliza> getPolizaByDni(String dni) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + dni_usuario + " IN (\'" + dni + "\')";
        Vector<Poliza> ret = new Vector<Poliza>();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Poliza aux = new Poliza();
                aux.setDniUsuario(c.getString(DBHandlerPoliza.dni_usuario_index));
                aux.setNumPoliza(c.getInt(DBHandlerPoliza.num_poliza_index));
                aux.setFechaVig(c.getString(DBHandlerPoliza.fecha_vig_index));
                aux.setTipoPoliza(c.getString(DBHandlerPoliza.tipo_poliza_index));
                aux.setTipoPago(c.getString(DBHandlerPoliza.tipo_pago_index));
                aux.setVigencia(c.getString(DBHandlerPoliza.dias_vig_index));

                ret.add(aux);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ret;
    }

    public Poliza getAllDataPoliza(int numero_poliza) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + num_poliza + " IN (\'" + numero_poliza + "\')";
        Cursor cursor = db.rawQuery(query, null);
        Poliza poliza = new Poliza();

        if (cursor.moveToFirst()) {
            poliza.setNumPoliza(Integer.parseInt(cursor.getString(num_poliza_index)));
            poliza.setDniUsuario(cursor.getString(dni_usuario_index));
            poliza.setFechaVig(cursor.getString(fecha_vig_index));
            poliza.setVigencia(cursor.getString(dias_vig_index));
            poliza.setTipoPago(cursor.getString(tipo_pago_index));
            poliza.setTipoPoliza(cursor.getString(tipo_poliza_index));
        }
        cursor.close();
        db.close();
        return poliza;
    }

    public boolean deletePolizabyDni(String dni) {
        int n = db.delete(TABLE_NAME, dni_usuario + "=" + dni, null);
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePolizabyNroPoliza(String nro) {
        int n = db.delete(TABLE_NAME, num_poliza + "=" + nro, null);
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean searchPolizaByNum(int numPoliza) {
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + num_poliza + " IN (\'" + numPoliza + "\')";
        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
    }

    public List<String> getNumPolizas(String dni) {
        List<String> polizas = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + dni_usuario + " IN (\'" + dni + "\')";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                polizas.add(cursor.getString(num_poliza_index));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return polizas;
    }
}
