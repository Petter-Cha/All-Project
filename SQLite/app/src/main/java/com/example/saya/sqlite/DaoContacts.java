package com.example.saya.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DaoContacts {
    private Context _contexto;
    private SQLiteDatabase _midb;

    public DaoContacts(Context contexto){
        this._contexto = contexto;
        this._midb = new OpenDB(contexto).getWritableDatabase();
    }


    public long insert(Contact c){

        ContentValues cv = new ContentValues();

        cv.put(OpenDB.COLUMNS_CONTACTOS[1],c.getNombre());
        cv.put(OpenDB.COLUMNS_CONTACTOS[2],c.getCorreo_electronico());
        cv.put(OpenDB.COLUMNS_CONTACTOS[3],c.getTwitter());
        cv.put(OpenDB.COLUMNS_CONTACTOS[4],c.getTelefono());
        cv.put(OpenDB.COLUMNS_CONTACTOS[5],c.getFecha_nacimiento());

        return _midb.insert(OpenDB.TABLE_CONTACTOS_NAME,null,cv) ;

    }



    public long update(Contact c){
        ContentValues cv = new ContentValues();

        cv.put(OpenDB.COLUMNS_CONTACTOS[1],c.getNombre());
        cv.put(OpenDB.COLUMNS_CONTACTOS[2],c.getCorreo_electronico());
        cv.put(OpenDB.COLUMNS_CONTACTOS[3],c.getTwitter());
        cv.put(OpenDB.COLUMNS_CONTACTOS[4],c.getTelefono());
        cv.put(OpenDB.COLUMNS_CONTACTOS[5],c.getFecha_nacimiento());

        return _midb.update(OpenDB.TABLE_CONTACTOS_NAME,
                cv,
                "_id=?",
                new String[] { String.valueOf( c.id)});

    }

    public int delete(String id){
        return  _midb.delete("Contacts","_id='"+id+"'",null);
    }

    public List<Contact> getAll(){
        List<Contact> ls=null;

        Cursor c = _midb.query(OpenDB.TABLE_CONTACTOS_NAME,
                OpenDB.COLUMNS_CONTACTOS,
                null,
                null,
                null,
                null,
                OpenDB.COLUMNS_CONTACTOS[1]);

        if (c.moveToFirst()) {
            ls = new ArrayList<Contact>();
            do {
                Contact ctc = new Contact();

                ctc.setId(
                        c.getInt(
                                c.getColumnIndex(
                                        OpenDB.COLUMNS_CONTACTOS[0])
                        )
                );

                ctc.setId(c.getInt(0));
                ctc.setNombre(c.getString(1));
                ctc.setCorreo_electronico(c.getString(2));
                ctc.setTwitter(c.getString(3));
                ctc.setTelefono(c.getString(4));
                ctc.setFecha_nacimiento(c.getString(5));

                ls.add(ctc);

            }while(c.moveToNext());
        }

        return ls;
    }


    public List<Contact> Buscar(String nombre){
        List<Contact> ls=null;

        Cursor c = _midb.query(OpenDB.TABLE_CONTACTOS_NAME,
                OpenDB.COLUMNS_CONTACTOS,
                null,
                null,
                null,
                null,
                OpenDB.COLUMNS_CONTACTOS[1]);

        if (c.moveToFirst()) {
            ls = new ArrayList<Contact>();
            do {
                Contact ctc = new Contact();

                ctc.setId(
                        c.getInt(
                                c.getColumnIndex(
                                        OpenDB.COLUMNS_CONTACTOS[0])
                        )
                );

                ctc.setId(c.getInt(0));
                ctc.setNombre(c.getString(1));
                ctc.setCorreo_electronico(c.getString(2));
                ctc.setTwitter(c.getString(3));
                ctc.setTelefono(c.getString(4));
                ctc.setFecha_nacimiento(c.getString(5));


                if(c.getString(1).toUpperCase().startsWith(nombre.toUpperCase())) {
                    ls.add(ctc);
                }

            }while(c.moveToNext());
        }

        return ls;
    }

    public List<Contact> getAllStudentsList() {
        List<Contact> studentsArrayList = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + "Contacts";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Contact students = new Contact();
                students.id = c.getInt(c.getColumnIndex("_id"));
                students.nombre = c.getString(c.getColumnIndex("nombre"));
                students.correo_electronico = c.getString(c.getColumnIndex("correo_electronico"));
                students.twitter = c.getString(c.getColumnIndex("twitter"));
                students.telefono = c.getString(c.getColumnIndex("telefono"));
                students.fecha_nacimiento = c.getString(c.getColumnIndex("fecha_nacimiento"));
                studentsArrayList.add(students);
            } while (c.moveToNext());
        }
        return studentsArrayList;
    }

    public List<Contact> buscarcontacto(String name) {
        List<Contact> studentsArrayList = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM Contacts WHERE nombre LIKE '"+name+"%'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Contact students = new Contact();
                students.id = c.getInt(c.getColumnIndex("_id"));
                students.nombre = c.getString(c.getColumnIndex("nombre"));
                students.correo_electronico = c.getString(c.getColumnIndex("correo_electronico"));
                students.twitter = c.getString(c.getColumnIndex("twitter"));
                students.telefono = c.getString(c.getColumnIndex("telefono"));
                students.fecha_nacimiento = c.getString(c.getColumnIndex("fecha_nacimiento"));
                studentsArrayList.add(students);
            } while (c.moveToNext());
        }
        return studentsArrayList;
    }



    public ArrayList<Contact> obtenercontacto(String id) {
        ArrayList<Contact> studentsArrayList = new ArrayList<Contact>();
        String selectQuery = "select * from Contacts where _id='"+id+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Contact students = new Contact();
                students.id = c.getInt(c.getColumnIndex("_id"));
                students.nombre = c.getString(c.getColumnIndex("nombre"));
                students.correo_electronico = c.getString(c.getColumnIndex("correo_electronico"));
                students.twitter = c.getString(c.getColumnIndex("twitter"));
                students.telefono = c.getString(c.getColumnIndex("telefono"));
                students.fecha_nacimiento = c.getString(c.getColumnIndex("fecha_nacimiento"));
                studentsArrayList.add(students);
            } while (c.moveToNext());
        }
        return studentsArrayList;
    }


}
