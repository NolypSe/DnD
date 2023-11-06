package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LinearLayoutMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_menu);
    }

    public void TableClases(View view){
        Intent clases = new Intent(this, TableLayaoutClases.class);
        startActivity(clases);
    }

    public void Creacion(View view){
        Intent creacion = new Intent(this, CreacionPersonaje.class);
        startActivity(creacion);
    }

    public void Companieros(View view){
        Intent companieros = new Intent(this, VerCompanieros.class);
        startActivity(companieros);
    }

    public void Personajes(View view){
        Intent companieros = new Intent(this, MisPersonajes.class);
        startActivity(companieros);
    }

    public void CerrarSesion(View view) {
        UsuarioActual.getInstance().setUsuarioActualId(-1);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}