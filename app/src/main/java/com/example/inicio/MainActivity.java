package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button modiEquipos;
    Button modiPartidos;
    Button verPartido;
    Button salir;
    Button sonidoS;
    Button sonidoN;

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
        sonidoN= findViewById(R.id.btnDesactivarS);
        sonidoS= findViewById(R.id.btnActivasrS);

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

        sonidoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOnSound(MainActivity.this);
            }
        });

        sonidoN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffSound(MainActivity.this);
            }
        });
    }

    public void turnOffSound(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }
    }

    public void turnOnSound(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        }
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