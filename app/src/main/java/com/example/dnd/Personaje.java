package com.example.dnd;

import java.io.Serializable;

public class Personaje implements Serializable {

    int id;
    String nombre;
    String raza;
    String clase;
    String trasfondo;
    String genero;
    int usuarioId;

    public Personaje() {
    }


    public Personaje(String nombre, String raza, String clase, String trasfondo, String genero) {
        this.nombre = nombre;
        this.raza = raza;
        this.clase = clase;
        this.trasfondo = trasfondo;
        this.genero = genero;
    }

    public Personaje(int id, String nombre, String raza, String clase, String trasfondo, String genero, int usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.clase = clase;
        this.trasfondo = trasfondo;
        this.genero = genero;
        this.usuarioId = usuarioId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTrasfondo() {
        return trasfondo;
    }

    public void setTrasfondo(String trasfondo) {
        this.trasfondo = trasfondo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
