package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class VerCompanieros extends AppCompatActivity {

    List<ListElementos> elementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_companieros);

        init();
    }


    public void init(){
        elementos = new ArrayList<>();
        elementos.add(new ListElementos("Astarion","Puerta de Baldur", "Elfo",R.mipmap.astarion_foreground));
        elementos.add(new ListElementos("Corazon Sombrio","Puerta de Baldur", "Semielfo",R.mipmap.shadowheart));
        elementos.add(new ListElementos("Gale","Waterdeep", "Humano",R.mipmap.gale));
        elementos.add(new ListElementos("Lae zel","Plano Astral", "Githyanki",R.mipmap.laezel));
        elementos.add(new ListElementos("Wyll","Puerta de Baldur", "Humano",R.mipmap.wyll));
        elementos.add(new ListElementos("Karlach","Averno", "Tiefling",R.mipmap.karlach));
        elementos.add(new ListElementos("Halsin","Arboleda Esmeralda", "Elfo",R.mipmap.halsin));
        elementos.add(new ListElementos("Minthara","UnderDark", "Drow",R.mipmap.minthara));
        elementos.add(new ListElementos("Minsc","Puerta de Baldur", "Humano",R.mipmap.minsc));
        elementos.add(new ListElementos("Jaheira","Ultima Luz", "Semielfo",R.mipmap.jaheira));

        ListaAdaptador listaAdaptador = new ListaAdaptador(elementos, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerCompanieros);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listaAdaptador);
    }
}