package com.deuzex.seguros.objects;

public class Usuario {

    private long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String num_direccion;
    private String depto;
    private String localidad;
    private String provincia;
    private String cod_postal;

    public Usuario(String dni, String nombre, String apellido,
                   String email, String direccion, String num_direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.num_direccion = num_direccion;
    }

    public Usuario(String dni) {
        this.dni = dni;
    }

    // USER DETAILS
    public Usuario(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario() {
    }

    // GET
    public long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumDireccion() {
        return num_direccion;
    }

    public String getDepto() {
        return depto;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCodPostal() {
        return cod_postal;
    }

    // SET
    public void setUserId(long id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNumDireccion(String num_direccion) {
        this.num_direccion = num_direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCodPostal(String cod_postal) {
        this.cod_postal = cod_postal;
    }
}
