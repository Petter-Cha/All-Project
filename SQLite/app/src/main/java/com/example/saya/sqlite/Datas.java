package com.example.saya.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Patterns;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Datas extends AppCompatActivity {
    Button save;
    Button actualizar;
    Button eliminar;
    EditText id;
    EditText nombre;
    EditText email;
    EditText twiter;
    EditText tel;
    EditText fec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datas);

        actualizar = (Button)findViewById(R.id.btnactualizar);
        eliminar = (Button)findViewById(R.id.btndelete);
        id = (EditText) findViewById(R.id.txtid);
        nombre = (EditText) findViewById(R.id.txtnombre);
        email = (EditText) findViewById(R.id.txtemail);
        twiter = (EditText) findViewById(R.id.txttwiter);
        tel = (EditText) findViewById(R.id.txttel);
        fec = (EditText) findViewById(R.id.txtfecha);


        insertar();
        buscarContact();
        actualizar();
        EliminarContact();

    }

    public void buscarContact(){

        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Pattern p = Pattern.compile("[0-9]+");
                DaoContacts dao = new DaoContacts(Datas.this);
                ArrayList<Contact> Contact;

                Contact = (ArrayList<Contact>) dao.obtenercontacto(id.getText().toString());
                if(id.getText().length()>0 && p.matcher(id.getText().toString()).matches()==true && Contact.size()>0) {
                    /*id.setText(Contact.get(0).getId());*/
                    nombre.setText(Contact.get(0).getNombre());
                    email.setText(Contact.get(0).getCorreo_electronico());
                    twiter.setText(Contact.get(0).getTwitter());
                    tel.setText(Contact.get(0).getTelefono());
                    fec.setText(Contact.get(0).getFecha_nacimiento());
                }else{
                    nombre.setText("");
                    email.setText("");
                    twiter.setText("");
                    tel.setText("");
                    fec.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    public void actualizar(){
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DaoContacts buscar = new DaoContacts(Datas.this);

                    Contact Contact1 = new Contact(Integer.parseInt(id.getText().toString()),nombre.getText().toString(),email.getText().toString(),twiter.getText().toString(),tel.getText().toString(),fec.getText().toString());
                    if(validacion().length()==0) {
                        if (buscar.update(Contact1) > 0) {
                            Toast.makeText(getBaseContext(), "Contact Actualizado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "Ocurrio un Error al Actualizar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception err){
                    Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void insertar(){
        save=(Button)findViewById(R.id.btnsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent atras = new Intent();

                if(validacion().length()==0) {
                    Contact alum = new Contact();
                    alum.setNombre(nombre.getText().toString());
                    alum.setCorreo_electronico(email.getText().toString());
                    alum.setTwitter(twiter.getText().toString());
                    alum.setTelefono(tel.getText().toString());
                    alum.setFecha_nacimiento(fec.getText().toString());

                    atras.putExtra("miContact", alum);

                    setResult(RESULT_OK, atras);
                    finish();
                }

            }
        });
    }

    public void EliminarContact(){

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DaoContacts dao = new DaoContacts(Datas.this);
                    Pattern p = Pattern.compile("[0-9]+");
                    if(id.getText().length()>0 && p.matcher(id.getText().toString()).matches()==true) {
                        if(dao.delete(id.getText().toString())>0){
                            Toast.makeText(getBaseContext(),"Contact Eliminado",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getBaseContext(),"Contact no se pudo Eliminado",Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (Exception err){
                    Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public String validacion(){

        String inconvenientes="";


        if(nombre.getText().toString().length()>0){
            nombre.setError(null);
        }else{
            inconvenientes+=">Nombre Obligatorio";
            nombre.setError("Nombre Obligatorio");

        }

        if(email.getText().toString().length()>0) {
            if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() == true) {
                email.setError(null);
            } else {
                inconvenientes += ">Correo Electronico Invalid";
                email.setError("Correo Invalido");
            }
        }

        if(tel.getText().toString().length()>0){
            tel.setError(null);
        }else{
            inconvenientes+=">Telefono Obligatorio";
            tel.setError("Telefono Obligatorio");

        }

        Pattern p = Pattern.compile("([0-9]{2})[/]([0-9]{2})[/]([0-9]{2})");
        if(p.matcher(fec.getText().toString()).matches()==true){
            fec.setError(null);
        }else{
            inconvenientes+=">Formato de Fecha Incorrecto";
            fec.setError("Formato de Fecha Incorrecto");
        }



        return inconvenientes;
    }


}
