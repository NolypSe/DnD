package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MisPersonajes extends AppCompatActivity {
    List<Personaje> personajes;
    RecyclerView recyclerView;
    daoPersonaje dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_personajes);

        recyclerView = findViewById(R.id.recyclerPersonajes);
        dao = new daoPersonaje(this);

        int usuarioActualId = UsuarioActual.getInstance().getUsuarioActualId();

        personajes = dao.verPersonajesUsuario(usuarioActualId);

        PersonajeAdaptador adapter = new PersonajeAdaptador(personajes, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }

}