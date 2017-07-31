package com.anderboys.cantarestext;

/**
 * Created by Windows on 10/05/2017.
 */

public class Producto {

    String nombre,descripcion;
    Integer p1,p2,p3,img;

    public Producto(String nombre, String descripcion, Integer p1, Integer p2, Integer p3, Integer img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
