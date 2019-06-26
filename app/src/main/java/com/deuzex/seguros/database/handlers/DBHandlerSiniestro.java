package com.deuzex.seguros.database.handlers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.deuzex.seguros.objects.Siniestro;

public class DBHandlerSiniestro {

    private static final String TABLE_NAME = "Siniestros";

    private static final String nro_poliza = "num_poliza";
    private static final String fecha_siniestro = "hecha_siniestro";
    private static final String hora_siniestro = "hora_siniestro";
    private static final String cod_postal = "cod_postal";
    private static final String localidad = "localidad";
    private static final String provincia = "provincia";
    private static final String pais = "pais";
    private static final String narracion = "narracion";
    private static final String foto_uri = "foto";
    private static final String latitud = "latitud";
    private static final String longitud = "longitud";

    private static final int nro_poliza_index = 0;
    private static final int fecha_siniestro_index = 1;
    private static final int hora_siniestro_index = 2;
    private static final int cod_postal_index = 3;
    private static final int localidad_index = 4;
    private static final int provincia_index = 5;
    private static final int pais_index = 6;
    private static final int narracion_index = 7;
    private static final int foto_uri_index = 8;
    private static final int latitud_index = 9;
    private static final int longitud_index = 10;

    private SQLiteDatabase db;

    public DBHandlerSiniestro(SQLiteDatabase writableDatabase) {
        this.db = writableDatabase;
    }

    public static String getCreateQuery() {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                nro_poliza + " INTEGER PRIMARY KEY, " +
                fecha_siniestro + " TEXT, " +
                hora_siniestro + " TEXT, " +
                cod_postal + " TEXT, " +
                localidad + " TEXT, " +
                provincia + " TEXT, " +
                pais + " TEXT, " +
                narracion + " TEXT, " +
                foto_uri + " TEXT, " +
                latitud + " REAL, " +
                longitud + " REAL )";
        return query;
    }

    public static String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public void addSiniestro(Siniestro siniestro) {
        ContentValues values = new ContentValues();

        values.put(nro_poliza, siniestro.getNro_poliza());
        values.put(fecha_siniestro, siniestro.getFecha_siniestro());
        values.put(hora_siniestro, siniestro.getHora_siniestro());
        values.put(cod_postal, siniestro.getCod_postal());
        values.put(localidad, siniestro.getLocalidad());
        values.put(provincia, siniestro.getProvincia());
        values.put(pais, siniestro.getPais());
        values.put(narracion, siniestro.getNarracion());
        values.put(foto_uri, siniestro.getFoto_uri());
        values.put(latitud, siniestro.getLatitud());
        values.put(longitud, siniestro.getLongitud());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
