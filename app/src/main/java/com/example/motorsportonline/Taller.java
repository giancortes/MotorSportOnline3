package com.example.motorsportonline;

public class Taller {
    private String Nombre;
    private String Ubicacion;
    private String Horario;
    private String Contacto;

    public Taller(){}
    public Taller(String Nombre, String Ubicacion, String Horario, String Contacto) {
        this.Nombre = Nombre;
        this.Ubicacion = Ubicacion;
        this.Horario = Horario;
        this.Contacto = Contacto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String Contacto) {
        this.Contacto = Contacto;
    }
}

