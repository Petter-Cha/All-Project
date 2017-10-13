package com.example.saya.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class OpenDB extends SQLiteOpenHelper {


    private static final String DB_NAME = "mydatabase";
    private static final int DB_VERSION = 2;
    public static final String[]COLUMNS_CONTACTOS =
            {"_id","name","e-mail",
                    "twitter","phone","birthdate"};
    public static final String TABLE_CONTACTOS_NAME="contacts";
    private  final String TABLE_CONTACTOS = "create table contacts ("+
            COLUMNS_CONTACTOS[0]+" integer primary key autoincrement, "+
            COLUMNS_CONTACTOS[1]+" varchar(100) null," +
            COLUMNS_CONTACTOS[2]+" varchar(100) not null,"+
            COLUMNS_CONTACTOS[3]+" varchar(100) null,"+
            COLUMNS_CONTACTOS[4]+" varchar(20) null,"+
            COLUMNS_CONTACTOS[5]+" date null);";


    public OpenDB(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
}

