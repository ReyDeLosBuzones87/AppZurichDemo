package com.deuzex.seguros.objects;

public class Poliza {

    private String dni_usuario;
    private int num_poliza;
    private String fecha_vig;
    private String vigencia;
    private String tipo_poliza;
    private String tipo_pago;

    public Poliza(String dni_usuario, int num_poliza, String fecha_vig,
                  String vigencia, String tipo_poliza, String tipo_pago) {
        this.dni_usuario = dni_usuario;
        this.num_poliza = num_poliza;
        this.fecha_vig = fecha_vig;
        this.vigencia = vigencia;
        this.tipo_poliza = tipo_poliza;
        this.tipo_pago = tipo_pago;
    }

    public Poliza() {
    }

    // GET

    public String getDniUsuario() {
        return dni_usuario;
    }

    public int getNumPoliza() {return num_poliza;}

    public String getFechaVig() {
        return fecha_vig;
    }

    public String getVigencia() {
        return vigencia;
    }

    public String getTipoPoliza() {
        return tipo_poliza;
    }

    public String getTipoPago() {
        return tipo_pago;
    }

    // SET

    public void setDniUsuario(String dni_usuario) {
        this.dni_usuario = dni_usuario;
    }

    public void setNumPoliza(int num_poliza) {this.num_poliza = num_poliza;}

    public void setFechaVig(String fecha_vig) {
        this.fecha_vig = fecha_vig;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public void setTipoPoliza(String tipo_poliza) {
        this.tipo_poliza = tipo_poliza;
    }

    public void setTipoPago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }
}
