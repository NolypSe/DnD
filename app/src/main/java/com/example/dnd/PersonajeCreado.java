package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PersonajeCreado extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje_creado);

        Intent intent = getIntent();
        String raza = intent.getStringExtra("raza");
        String clase = intent.getStringExtra("clase");
        String genero = intent.getStringExtra("genero");
        String nombre = intent.getStringExtra("nombre");
        String trasfondo = intent.getStringExtra("trasfondo");


        TextView txtNombre = findViewById(R.id.txtNombre);
        txtNombre.setText(nombre);

        TextView txtRazaS = findViewById(R.id.txtRazaS);
        txtRazaS.setText(raza);

        TextView txtClaseS = findViewById(R.id.txtClaseS);
        txtClaseS.setText(clase);

        TextView txtGeneroS = findViewById(R.id.txtGeneroS);
        txtGeneroS.setText(genero);

        TextView txtTransfondoS = findViewById(R.id.txtTransfondoS);
        txtTransfondoS.setText(trasfondo);




        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {

                    Toast.makeText(PersonajeCreado.this, "Gracias por tu valoración", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void LinearMenu2(View view){
        Intent menu = new Intent(this, LinearLayoutMenu.class);
        startActivity(menu);
    }
}