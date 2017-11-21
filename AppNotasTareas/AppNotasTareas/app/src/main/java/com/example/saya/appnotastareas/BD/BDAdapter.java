package com.example.saya.appnotastareas.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Clase para crear la base de datos con SQLite
// SQLiteOpenHelper Crea un objeto auxiliar para crear, abrir y / o administrar una base de datos.
public class BDAdapter extends SQLiteOpenHelper {

    public  static final String BD_NAME = "BDNT";

    //Creacion de la tabla de Notas, con sus respectivos campos
    //IdNota, Nombre, Descripcion, Fecha, Foto, Video y Audio
    private final String SQLITE_TABLE_NOTAS = "create table notas (" +
            "_idNota integer primary key autoincrement," +
            "nombre text not null," +
            "descripcion text not null," +
            "fecha text not null," +
            "foto text,"+
            "video text," +
            "audio text" + ")";

    //Creacion de la tabla de Tareas, con sus respectivos campos
    //IdTarea, Nombre, Descripcion, Fecha, Foto, Video, Audio y Recordatorio
    private final String SQLITE_TABLE_TAREAS = "create table tareas (" +
            "_idTarea integer primary key autoincrement," +
            "nombre text not null," +
            "descripcion text not null," +
            "fecha text not null," +
            "foto text," +
            "video text," +
            "audio text," +
            "recordatorio text not null"+")";

    //Creacion de las columnas
    public static final String [] COLUMNAS_TABLE_NOTAS = new String[] {"_idNota","nombre","descripcion","fecha","foto","video","audio"};
    public static final String [] COLUMNAS_TABLE_TAREAS = new String[] {"_idTarea","nombre","descripcion","fecha","foto","video","audio","recordatorio"};


    public static final String TABLE_NAME_NOTAS = "notas";
    public static final String TABLE_NAME_TAREAS = "tareas";

    public BDAdapter(Context context) { super(context,BD_NAME, null, 1);}

    // Metodos que se encargan de abrir la base de datos si existe (onOpen)
    // crearla si no es así (onCreate)
    // y actualizarla según sea necesario (onUpgrade).
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLITE_TABLE_NOTAS);
        sqLiteDatabase.execSQL(SQLITE_TABLE_TAREAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
