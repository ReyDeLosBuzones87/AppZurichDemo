package com.deuzex.seguros.objects;

public class TipoPoliza {
    private String id;
    private String nombre;

    // GET
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // SET
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
