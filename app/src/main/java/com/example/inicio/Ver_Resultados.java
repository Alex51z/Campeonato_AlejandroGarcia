package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Ver_Resultados extends AppCompatActivity {

    SQLiteHelper helper;
    SQLiteDatabase db;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_resultados);

        helper = new SQLiteHelper(this);
        db= helper.getWritableDatabase();
        lv= findViewById(R.id.listaMayor);
        getEquiposOrdenadosPorPuntos();
    }

    public void getEquiposOrdenadosPorPuntos() {
        String[] from = {EstructuraBBDD.Equipos.COLUMN_NAME_NOMBRE_EQUIPO, EstructuraBBDD.Equipos.COLUMN_NAME_CIUDAD, EstructuraBBDD.Equipos.COLUMN_NAME_PUNTOS_ACUMULADOS};
        Cursor cursor = db.query(EstructuraBBDD.Equipos.TABLE_NAME_EQUIPOS, from, null, null, null, null, EstructuraBBDD.Equipos.COLUMN_NAME_PUNTOS_ACUMULADOS + " DESC");
        int[] to= {R.id.impriNombre, R.id.impriCiudad, R.id.impriPuntos};

        SimpleCursorAdapter adaptador= new SimpleCursorAdapter(this, R.layout.lista, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adaptador);
    }
}