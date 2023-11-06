package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DisenioRazas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disenio_razas);
    }

    public void RazasNombre(View View) {
        String cadena = getResources().getResourceEntryName(View.getId());
        Toast.makeText(this, "Raza "+ cadena,Toast.LENGTH_SHORT).show();
    }
}