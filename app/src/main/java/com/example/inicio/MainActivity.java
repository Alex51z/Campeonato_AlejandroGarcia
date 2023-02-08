package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button modiEquipos;
    Button modiPartidos;
    Button verPartido;
    Button salir;
    Boolean sonido= false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mp= MediaPlayer.create(this, R.raw.guns);

        modiEquipos= findViewById(R.id.modificarEqui);
        modiPartidos= findViewById(R.id.modificarPar);
        verPartido= findViewById(R.id.verPartidos);
        salir= findViewById(R.id.salirAplicacion);

        modiPartidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificiarPartidos();
            }
        });

        modiEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarEquipos();
            }
        });

        verPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verPartidos();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void verPartidos() {
        Intent i= new Intent(this, Ver_Resultados.class);
        startActivity(i);
    }

    public void modificarEquipos() {
        Intent i= new Intent(this, Modificar_Equipo.class);
        startActivity(i);
    }

    public void modificiarPartidos() {
        Intent i= new Intent(this, Moficar_Partidos.class);
        startActivity(i);
    }
}