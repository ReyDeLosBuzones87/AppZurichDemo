package com.deuzex.seguros.objects;

public class Siniestro {

    private int nro_poliza;
    private String fecha_siniestro;
    private String hora_siniestro;
    private String cod_postal;
    private String localidad;
    private String provincia;
    private String pais;
    private String narracion;
    private String foto_uri;
    private String latitud;
    private String longitud;

    public int getNro_poliza() {
        return nro_poliza;
    }

    public String getFecha_siniestro() {
        return fecha_siniestro;
    }

    public String getHora_siniestro() {
        return hora_siniestro;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPais() {
        return pais;
    }

    public String getNarracion() {
        return narracion;
    }

    public String getFoto_uri() {
        return foto_uri;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setNro_poliza(int nro_poliza) {
        this.nro_poliza = nro_poliza;
    }

    public void setFecha_siniestro(String fecha_siniestro) {
        this.fecha_siniestro = fecha_siniestro;
    }

    public void setHora_siniestro(String hora_siniestro) {
        this.hora_siniestro = hora_siniestro;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setNarracion(String narracion) {
        this.narracion = narracion;
    }

    public void setFoto_uri(String foto_uri) {
        this.foto_uri = foto_uri;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
