package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    daoUsuario dao;
    ArrayList<Usuario> lista;
    Usuario c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        dao= new daoUsuario(Registro.this);
        Button insertar = findViewById(R.id.btnRegistro);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText usuario = findViewById(R.id.txtUsuarioRegistro);
                final EditText password = findViewById(R.id.txtPasswordRegistro);
                final EditText confirmarPassword = findViewById(R.id.txtPasswordConfirmarRegistro);
                String username = usuario.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmarPassword.getText().toString();

                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confirmPass)) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden. Vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
                } else if (usuarioExistente(username)) {
                        Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                    try {
                        c = new Usuario(username, pass);

                        if (dao.insertar(c)) {
                            Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Registro.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
        private boolean usuarioExistente(String username) {
            return dao.existeUsuario(username);
        }
}




