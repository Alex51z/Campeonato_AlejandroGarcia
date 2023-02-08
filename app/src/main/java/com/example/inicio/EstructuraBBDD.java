package com.example.inicio;

import android.provider.BaseColumns;

public class EstructuraBBDD {

    public static final String SQL_CREATE_EQUIPOS =
            "CREATE TABLE IF NOT EXISTS " + Equipos.TABLE_NAME_EQUIPOS +
                    "(" + Equipos._ID + " integer PRIMARY KEY, "
                    + Equipos.COLUMN_NAME_NOMBRE_EQUIPO + " text, "
                    + Equipos.COLUMN_NAME_CIUDAD + " text, "
                    + Equipos.COLUMN_NAME_PUNTOS_ACUMULADOS + " integer, ";

    public static final String SQL_DELETE_EQUIPOS =
            "DROP TABLE IF EXISTS " + Equipos.TABLE_NAME_EQUIPOS;

    public static final String SQL_CREATE_PARTIDOS =
            "CREATE TABLE IF NOT EXISTS " + Partidos.TABLE_NAME_PARTIDOS +
                    "(" + Partidos._ID + " integer PRIMARY KEY, "
                    + Partidos.COLUMN_NAME_JORNADA + " integer, "
                    + Partidos.COLUMN_NAME_FECHA + " text, "
                    + Partidos.COLUMN_NAME_EQUIPO1 + " text, "
                    + Partidos.COLUMN_NAME_EQUIPO2 + " text, "
                    + Partidos.COLUMN_NAME_PUNTUACION_EQUIPO1 + " integer, "
                    + Partidos.COLUMN_NAME_PUNTUACION_EQUIPO2 + " integer);";

    public static final String SQL_DELETE_PARTIDOS =
            "DROP TABLE IF EXISTS " + Partidos.TABLE_NAME_PARTIDOS;

    private EstructuraBBDD() {
    }

    public static class Equipos implements BaseColumns {
        public static final String TABLE_NAME_EQUIPOS = "Equipos";
        public static final String COLUMN_NAME_NOMBRE_EQUIPO = "Nombre_equipo";
        public static final String COLUMN_NAME_CIUDAD = "Ciudad";
        public static final String COLUMN_NAME_PUNTOS_ACUMULADOS = "Puntos_acumulados";
    }

    public static class Partidos implements BaseColumns {
        public static final String TABLE_NAME_PARTIDOS = "Partidos";
        public static final String COLUMN_NAME_JORNADA = "Nº_jornada/encuentro";
        public static final String COLUMN_NAME_FECHA = "Fecha";
        public static final String COLUMN_NAME_EQUIPO1 = "Equipo1";
        public static final String COLUMN_NAME_EQUIPO2 = "Equipo2";
        public static final String COLUMN_NAME_PUNTUACION_EQUIPO1 = "Puntuación_equipo1";
        public static final String COLUMN_NAME_PUNTUACION_EQUIPO2 = "Puntuación_equipo2";
    }
}
