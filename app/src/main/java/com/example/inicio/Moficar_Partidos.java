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

public class Moficar_Partidos extends AppCompatActivity {

    Button eliminar, modificar, insertar;
    EditText nombreOld, nombreNew, ciudad, encuentro, fecha, puntosInsertL, puntosInsertV, local, visitante;
    ListView lv;
    SQLiteDatabase db;
    SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moficar_partidos);

        helper= new SQLiteHelper(this);
        db= helper.getWritableDatabase();

        eliminar= findViewById(R.id.btnEliminarP);
        modificar= findViewById(R.id.btnActualizarNombreP);
        insertar= findViewById(R.id.btnInsertarP);
        nombreOld= findViewById(R.id.eTextOldP);
        nombreNew= findViewById(R.id.eTextNewP);
        ciudad= findViewById(R.id.eTextOldPC);
        encuentro= findViewById(R.id.eTextInsertNombreP);
        fecha= findViewById(R.id.eTextInsertCiudadP);
        puntosInsertL= findViewById(R.id.eTextPLocal);
        puntosInsertV= findViewById(R.id.eTextPVisitante);
        visitante= findViewById(R.id.eTextVisitante);
        local= findViewById(R.id.eTextLocal);

        lv= findViewById(R.id.listaP);
        consultar();

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePartidosByFecha(ciudad.getText().toString());
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarPartido(nombreOld.getText().toString(), nombreNew.getText().toString());
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int puntosVisi= Integer.parseInt(puntosInsertV.getText().toString());
                int puntosLocal= Integer.parseInt(puntosInsertL.getText().toString());
                insertarPartido(encuentro.getText().toString(), fecha.getText().toString(), visitante.getText().toString(), local.getText().toString(), puntosVisi, puntosLocal);
            }
        });
    }

    private void consultar() {
        Cursor cursor= db.query(EstructuraBBDD.Partidos.TABLE_NAME_PARTIDOS, null, null, null, null, null, null);
        String [] from= {EstructuraBBDD.Partidos.COLUMN_NAME_JORNADA, EstructuraBBDD.Partidos.COLUMN_NAME_FECHA, EstructuraBBDD.Partidos.COLUMN_NAME_PUNTUACION_EQUIPO1, EstructuraBBDD.Partidos.COLUMN_NAME_PUNTUACION_EQUIPO2, EstructuraBBDD.Partidos.COLUMN_NAME_PUNTUACION_EQUIPO1, EstructuraBBDD.Partidos.COLUMN_NAME_PUNTUACION_EQUIPO2};
        int [] to = {R.id.txtencuentro, R.id.txtFecha, R.id.txtVisitante, R.id.txtLocal, R.id.txtPuntosVi, R.id.txtPuntosLo};

        SimpleCursorAdapter adaptador= new SimpleCursorAdapter(this, R.layout.lista, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adaptador);
    }

    public void actualizarPartido(String nombreViejo, String nombreNuevo) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_JORNADA, nombreNuevo);
        String selection = EstructuraBBDD.Partidos.COLUMN_NAME_JORNADA + " LIKE ?";
        String[] selectionArgs = { nombreViejo };
        db.update(EstructuraBBDD.Partidos.TABLE_NAME_PARTIDOS, values, selection, selectionArgs);
        db.close();
    }

    public void deletePartidosByFecha(String fecha) {
        db.delete(EstructuraBBDD.Partidos.TABLE_NAME_PARTIDOS, EstructuraBBDD.Partidos.COLUMN_NAME_FECHA + " = ?", new String[] {fecha});
        db.close();
    }

    public void insertarPartido(String jornada, String fecha, String equipo1, String equipo2, int puntuacion1, int puntuacion2) {
        ContentValues values = new ContentValues();
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_JORNADA, jornada);
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_FECHA, fecha);
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_EQUIPO1, equipo1);
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_EQUIPO2, equipo2);
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_PUNTUACION_EQUIPO1, puntuacion1);
        values.put(EstructuraBBDD.Partidos.COLUMN_NAME_PUNTUACION_EQUIPO2, puntuacion2);
        long newRowId = db.insert(EstructuraBBDD.Partidos.TABLE_NAME_PARTIDOS, null, values);
        db.close();
    }
}