package com.example.saya.appnotastareas.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class DAOTareas {

    Context _contextoN;
    SQLiteDatabase _adpN;


    public DAOTareas(Context contexto){
        _contextoN = contexto;
        _adpN = new BDAdapter(_contextoN).getWritableDatabase();
    }

    public long insertTara(Tareas objT){
        ContentValues cv = new ContentValues();

        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[1], objT.getNombre());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[2], objT.getDescripcion());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[3], objT.getFecha());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[4], objT.getFoto());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[5], objT.getVideo());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[6], objT.getAudio());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[7], objT.getRecordatorio());

        return _adpN.insert(BDAdapter.TABLE_NAME_TAREAS,null,cv);
    }

    //Metodo para actualizar las notas, segun su identificador(id)
    public Object  updateOneByIDN(Tareas objT){


        ContentValues cv = new ContentValues();

        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[0], objT.getIdTareas());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[1], objT.getNombre());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[2], objT.getDescripcion());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[3], objT.getFecha());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[4], objT.getFoto());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[5], objT.getVideo());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[6], objT.getAudio());
        cv.put(BDAdapter.COLUMNAS_TABLE_TAREAS[7], objT.getRecordatorio());

        String [] args = new String[]{String.valueOf(objT.getIdTareas())};

        return _adpN.update(BDAdapter.TABLE_NAME_TAREAS, cv, "_idTarea=?", args);
    }
    //lista Tareas, obetner todas las tareas
    public List<Tareas> getAllTarea(){

        Cursor c = _adpN.query(BDAdapter.TABLE_NAME_TAREAS, BDAdapter.COLUMNAS_TABLE_TAREAS,
                null,null,null,null,BDAdapter.COLUMNAS_TABLE_TAREAS[1]);

        List<Tareas> lst = new ArrayList<Tareas>();

        if( c.moveToFirst()){
            do {
                Tareas bu = new Tareas(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));

                lst.add(bu);

            }while(c.moveToNext());
        }
        return lst;
    }
    //lista tareas, obetner todas las tareas en base al nombre
    public List<Tareas> getAllNotasByNameN(String criterio){
        Cursor c;

        c = _adpN.rawQuery("select * from tareas where nombre like ? or descripcion like ?",
                new String[]{  "%" + criterio + "%"});

        List<Tareas> listn = new ArrayList<Tareas>();

        if (c.moveToFirst()){
            do {
                Tareas n = new Tareas(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));

                listn.add(n);
            } while (c.moveToNext());
        }
        return  listn;
    }
    //Metodo para eliminar Notas segun su identificador(id)
    public int deleteN(int id){
        String [] args = new String[]{String.valueOf(id)};

        return  _adpN.delete(BDAdapter.TABLE_NAME_NOTAS, "_idTarea=?", args);
    }
    public int deleteT(int id){
        String [] args = new String[]{String.valueOf(id)};
        return  _adpN.delete(BDAdapter.TABLE_NAME_NOTAS, "_idTarea=?", args);
    }
}
