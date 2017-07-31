package com.anderboys.cantarestext;

/**
 * Created by Administrador on 05/04/2017.
 */
public class Persona {
    String nombre;
    String apellido;
    Integer img;

    public Persona(String nombre, String apellido, Integer img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getImg() {
        return img;
    }
}


