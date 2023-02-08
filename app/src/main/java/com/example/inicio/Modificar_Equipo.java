package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Modificar_Equipo extends AppCompatActivity {

    Button eliminar, modificar, insertar;
    EditText nombreOld, nombreNew, ciudad, nombreInsert, ciudadInsert, puntosInsert;
    ListView lv;
    SQLiteDatabase db;
    SQLiteHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_equipo);

        helper= new SQLiteHelper(this);
        db= helper.getWritableDatabase();

        eliminar= findViewById(R.id.btnEliminar);
        modificar= findViewById(R.id.btnActualizarNombre);
        insertar= findViewById(R.id.btnInsertar);
        nombreOld= findViewById(R.id.eTextOld);
        nombreNew= findViewById(R.id.eTextNew);
        ciudad= findViewById(R.id.eTextOldC);
        nombreInsert= findViewById(R.id.eTextInsertNombre);
        ciudadInsert= findViewById(R.id.eTextInsertCiudad);
        puntosInsert= findViewById(R.id.eTextInsetPuntos);
        lv= findViewById(R.id.lista);
        consultar();

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarNombreEquipo(nombreOld.getText().toString(), nombreNew.getText().toString());
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarEquiposPorCiudad(ciudad.getText().toString());
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int puntos= Integer.parseInt(puntosInsert.getText().toString());
                insertarNuevoEquipo(nombreInsert.getText().toString(), ciudadInsert.getText().toString(), puntos);
            }
        });
    }

    public void actualizarNombreEquipo(String viejo, String nuevo) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Equipos.COLUMN_NAME_NOMBRE_EQUIPO, nuevo);
        db.update(EstructuraBBDD.Equipos.TABLE_NAME_EQUIPOS, values, EstructuraBBDD.Equipos.COLUMN_NAME_NOMBRE_EQUIPO + " = ?", new String[]{viejo});
        db.close();
    }

    public void eliminarEquiposPorCiudad(String ciudad) {
        db.delete(EstructuraBBDD.Equipos.TABLE_NAME_EQUIPOS, EstructuraBBDD.Equipos.COLUMN_NAME_CIUDAD + " = ?", new String[]{ciudad});
        db.close();
    }

    public void insertarNuevoEquipo(String nombre, String ciudad, int puntos) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Equipos.COLUMN_NAME_NOMBRE_EQUIPO, nombre);
        values.put(EstructuraBBDD.Equipos.COLUMN_NAME_CIUDAD, ciudad);
        values.put(EstructuraBBDD.Equipos.COLUMN_NAME_PUNTOS_ACUMULADOS, puntos);
        db.insert(EstructuraBBDD.Equipos.TABLE_NAME_EQUIPOS, null, values);
        db.close();
    }

    private void consultar() {
        Cursor cursor= db.query(EstructuraBBDD.Equipos.TABLE_NAME_EQUIPOS, null, null, null, null, null, null);
        String [] from= {EstructuraBBDD.Equipos.COLUMN_NAME_NOMBRE_EQUIPO, EstructuraBBDD.Equipos.COLUMN_NAME_CIUDAD, EstructuraBBDD.Equipos.COLUMN_NAME_PUNTOS_ACUMULADOS};
        int [] to = {R.id.impriNombre, R.id.impriCiudad, R.id.impriPuntos};

        SimpleCursorAdapter adaptador= new SimpleCursorAdapter(this, R.layout.lista, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adaptador);
    }
}