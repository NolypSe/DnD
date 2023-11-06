package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreacionPersonaje extends AppCompatActivity {

    android.widget.Spinner _spnnrRaza, _spnnrClase;
    int maxCheckBoxes = 1;
    int selectedCheckBoxes = 0;

    int[] checkBoxIds = {
            R.id.chkErudito,
            R.id.chkCriminal,
            R.id.chkAcolito,
            R.id.chkAnimador,
            R.id.chkForastero,
            R.id.chkHuerfano,
            R.id.chkNoble,
            R.id.chkSoldado
    };

    daoPersonaje dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_personaje);

        dao = new daoPersonaje(this);

        for (int checkBoxId : checkBoxIds) {
            CheckBox checkBox = findViewById(checkBoxId);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (selectedCheckBoxes < maxCheckBoxes) {
                            selectedCheckBoxes++;
                        } else {
                            checkBox.setChecked(false);
                            Toast.makeText(CreacionPersonaje.this,"Maximo un transfondo",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        selectedCheckBoxes--;
                    }
                }
            });
        }

        _spnnrRaza = findViewById(R.id.spnnrRaza);
        _spnnrClase = findViewById(R.id.spnnrClase);

        String[] respuestaRaza = {"Elfo","Tiefling","Drow","Humano","Githyanki","Enano","Semielfo","Mediano","Gnomo","Draconido","Semiorco"};
        ArrayAdapter<String> adapterRaza = new ArrayAdapter<>(this, R.layout.spinner_item, respuestaRaza);
        _spnnrRaza.setAdapter(adapterRaza);

        String[] respuestaClase = {"Barbaro","Bardo","Clerigo","Druida","Guerrero","Monje","Paladin","Explorador","Picaro","Hechicero","Brujo","Mago"};
        ArrayAdapter<String> adapterClase = new ArrayAdapter<>(this, R.layout.spinner_item, respuestaClase);
        _spnnrClase.setAdapter(adapterClase);



        Intent intent = getIntent();
        if (intent.hasExtra("editarPersonaje")) {
            Personaje personaje = (Personaje) intent.getSerializableExtra("editarPersonaje");
            rellenarCamposConDatos(personaje);

            Button Editar = findViewById(R.id.btnCrearPersonaje);
            Editar.setText("Guardar cambios");
        }
    }

    private void rellenarCamposConDatos(Personaje personaje) {
        _spnnrRaza.setSelection(obtenerPosicionSpinner(_spnnrRaza, personaje.getRaza()));
        _spnnrClase.setSelection(obtenerPosicionSpinner(_spnnrClase, personaje.getClase()));

        EditText editTextNombre = findViewById(R.id.iTxtNombre);
        editTextNombre.setText(personaje.getNombre());

        if (personaje.getGenero().equals("Masculino")) {
            RadioButton rdoMasculino = findViewById(R.id.rdoMasculino);
            rdoMasculino.setChecked(true);
        } else if (personaje.getGenero().equals("Femenino")) {
            RadioButton rdoFemenino = findViewById(R.id.rdoFemenino);
            rdoFemenino.setChecked(true);
        }

        String[] trasfondos = personaje.getTrasfondo().split(", ");
        for (String trasfondo : trasfondos) {
            for (int checkBoxId : checkBoxIds) {
                CheckBox checkBox = findViewById(checkBoxId);
                if (checkBox.getText().toString().equals(trasfondo)) {
                    checkBox.setChecked(true);
                    break;
                }
            }
        }
    }

    private int obtenerPosicionSpinner(android.widget.Spinner spinner, String item) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
        return adapter.getPosition(item);
    }

    public void RazasPreview(View view){
        Intent preview = new Intent(this, DisenioRazas.class);
        startActivity(preview);
    }

    public void CrearPersonaje(View view){
        String razaSeleccionada = _spnnrRaza.getSelectedItem().toString();
        String claseSeleccionada = _spnnrClase.getSelectedItem().toString();
        String generoSeleccionado = obtenerGeneroSeleccionado();
        String nombre = obtenerNombre();
        String trasfondo = obtenerTrasfondo();

        int usuarioActualId = UsuarioActual.getInstance().getUsuarioActualId();

        if (getIntent().hasExtra("editarPersonaje")) {
            Personaje personajeEditado = new Personaje(nombre, razaSeleccionada, claseSeleccionada, trasfondo, generoSeleccionado);
            Personaje personaje = (Personaje) getIntent().getSerializableExtra("editarPersonaje");
            personajeEditado.setId(personaje.getId());
            boolean editExitoso = dao.editar(personajeEditado, usuarioActualId);

            if (editExitoso) {
                Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al guardar los cambios", Toast.LENGTH_SHORT).show();
            }
        } else {
            Personaje nuevoPersonaje = new Personaje(nombre, razaSeleccionada, claseSeleccionada, trasfondo, generoSeleccionado);
            boolean insertExitoso = dao.insertar(nuevoPersonaje, usuarioActualId);

            if (insertExitoso) {
                Toast.makeText(this, "Personaje creado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al crear el personaje", Toast.LENGTH_SHORT).show();
            }
        }

        Intent intent = new Intent(this, PersonajeCreado.class);

        intent.putExtra("raza", razaSeleccionada);
        intent.putExtra("clase", claseSeleccionada);
        intent.putExtra("genero", generoSeleccionado);
        intent.putExtra("nombre", nombre);
        intent.putExtra("trasfondo", trasfondo);

        startActivity(intent);
    }

    private String obtenerGeneroSeleccionado() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup4);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
    }

    private String obtenerNombre() {
        EditText editTextNombre = findViewById(R.id.iTxtNombre);
        return editTextNombre.getText().toString();
    }

    private String obtenerTrasfondo() {
        StringBuilder trasfondosSeleccionados = new StringBuilder();
        for (int checkBoxId : checkBoxIds) {
            CheckBox checkBox = findViewById(checkBoxId);
            if (checkBox.isChecked()) {
                if (trasfondosSeleccionados.length() > 0) {
                    trasfondosSeleccionados.append(", ");
                }
                trasfondosSeleccionados.append(checkBox.getText());
            }
        }

        return trasfondosSeleccionados.toString();
    }
}

