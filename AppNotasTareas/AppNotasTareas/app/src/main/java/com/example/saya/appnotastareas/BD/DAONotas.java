package com.example.saya.appnotastareas.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAONotas {
    Context _contexto;
    SQLiteDatabase _adp; //objeto de base de datos


    public DAONotas(Context contexto){
        _contexto = contexto;
        // getWritableDatabase Crea y / o abre una base de datos que se usará para leer y escribir
        _adp = new BDAdapter(_contexto).getWritableDatabase();
    }

    //Metodo para insertar los datos de la Nota mediante el ContentValues
    //Android utiliza una clase llamada android.content.ContentValues para
    // retener los valores de un solo registro, que será el que se insertará.
    // Los ContentValues son un dicionario de pares clave/valor, al igual que
    // los nombres de columnas y valores de las bases de datos. La forma de
    // insertar un registro es rellenando el ContentValues primero y despues
    // decir a android.content.ContentResolver que lo inserte usando una URI.
    public long insertNota(Notas objN){
        ContentValues cv = new ContentValues();

        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[1], objN.getNombre());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[2], objN.getDescripcion());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[3], objN.getFecha());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[4], objN.getFoto());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[5], objN.getVideo());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[6], objN.getAudio());

        return _adp.insert(BDAdapter.TABLE_NAME_NOTAS,null,cv);
    }

    //Metodo para actualizar las notas, segun su identificador(id)
    public Object  updateOneByID(Notas objN){


        ContentValues cv = new ContentValues();

        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[0], objN.getIdNota());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[1], objN.getNombre());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[2], objN.getDescripcion());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[3], objN.getFecha());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[4], objN.getFoto());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[5], objN.getVideo());
        cv.put(BDAdapter.COLUMNAS_TABLE_NOTAS[6], objN.getAudio());

        String [] args = new String[]{String.valueOf(objN.getIdNota())};

        return _adp.update(BDAdapter.TABLE_NAME_NOTAS, cv, "_idNota=?", args);
    }


    //lista Notas, obetner todas las notas
    public List<Notas> getAll(){

        Cursor c = _adp.query(BDAdapter.TABLE_NAME_NOTAS, BDAdapter.COLUMNAS_TABLE_NOTAS,
                null,null,null,null,BDAdapter.COLUMNAS_TABLE_NOTAS[1]);

        List<Notas> lst = new ArrayList<Notas>();

        if( c.moveToFirst()){
            do {
                Notas u = new Notas(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));

                lst.add(u);

            }while(c.moveToNext());
        }
        return lst;
    }

    //lista Notas, obetner todas las notas en base al nombre
    public List<Notas> getAllNotasByName(String criterio){
        Cursor c;

        c = _adp.rawQuery("select * from notas where nombre like ? or descripcion like ?",
                new String[]{  "%" + criterio + "%"});

        List<Notas> list = new ArrayList<Notas>();

        if (c.moveToFirst()){
            do {
                Notas n = new Notas(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));

                list.add(n);
            } while (c.moveToNext());
        }
        return  list;
    }

    //Metodo para eliminar Notas segun su identificador(id)
    public int delete(int id){
        String [] args = new String[]{String.valueOf(id)};

        return  _adp.delete(BDAdapter.TABLE_NAME_NOTAS, "_idNota=?", args);
    }



}
