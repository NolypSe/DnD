package com.example.dnd;

public class ListElementos {
    public String nombre;
    public String ciudad;
    public String raza;
    public int imagen;


    public ListElementos(String nombre, String ciudad, String raza, int imagen) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.raza = raza;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
