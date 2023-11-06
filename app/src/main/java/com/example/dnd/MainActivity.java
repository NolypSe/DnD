package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1;
    Button ingresar;
    EditText txtUsuario;
    EditText txtPassword;
    daoUsuario dao;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.pb1);
        ingresar = findViewById(R.id.btnIngresar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);

        dao = new daoUsuario(MainActivity.this);

        ingresar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String usuario = txtUsuario.getText().toString();
                String password = txtPassword.getText().toString();
                if (usuario.isEmpty() || password.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Por favor, ingrese el usuario y la contraseña.", Toast.LENGTH_SHORT).show();
                } else if (dao.login(usuario, password)) {

                    UsuarioActual app = UsuarioActual.getInstance();
                    app.setLoggedIn(true);
                    int usuarioId = dao.getIdDeUsuario(usuario);
                    app.setUsuarioActualId(usuarioId);

                    pb1.setVisibility(View.VISIBLE);

                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            counter++;

                            pb1.setProgress(counter);
                            if (counter == 30) {
                                timer.cancel();

                                Intent menu = new Intent(MainActivity.this, LinearLayoutMenu.class);
                                startActivity(menu);
                            }
                        }
                    };
                    timer.schedule(timerTask, 30, 30);
                } else {

                    Toast.makeText(MainActivity.this, "Usuario o contraseña inválida", Toast.LENGTH_SHORT).show();
                }
        }
        });
}

    public void Ingresar(View view){
        Intent menu = new Intent(this, LinearLayoutMenu.class);
        startActivity(menu);
    }

    public void Visitanos(View view){
        Intent menu = new Intent(this, Visitanos.class);
        startActivity(menu);
    }

    public void Registrarse(View view){
        Intent menu = new Intent(this, Registro.class);
        startActivity(menu);
    }
}