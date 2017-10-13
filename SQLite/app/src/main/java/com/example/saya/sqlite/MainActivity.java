package com.example.saya.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView List;
    TextView txtbuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List =(ListView)findViewById(R.id.listaContactos);

        cargardatos();
        busqueda();
    }

    public void busqueda(){
        txtbuscar=(TextView)findViewById(R.id.txtbuscar);

        txtbuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtbuscar.getText().length()==0){
                    cargardatos();
                }else{
                    buscar(txtbuscar.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    ArrayAdapter<Contact> adp;
    public void cargardatos(){
        DaoContacts dao = new DaoContacts(MainActivity.this);
        adp = new ArrayAdapter<Contact>(MainActivity.this,
                android.R.layout.simple_list_item_1,dao.getAllStudentsList());
        List.setAdapter(adp);
    }

    public void buscar(String cad){
        DaoContacts dao = new DaoContacts(MainActivity.this);
        ArrayAdapter<Contact> adp = new ArrayAdapter<Contact>(MainActivity.this,
                android.R.layout.simple_list_item_1,dao.buscarcontacto(cad));

        List.setAdapter(adp);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK)
        {

            try {

               
                Contact objconta = (Contact) data.getSerializableExtra("micontacto");

                DaoContacts dao = new DaoContacts(MainActivity.this);
                if(dao.insert(new Contact(0,objconta.getNombre(),objconta.getCorreo_electronico(),objconta.getTwitter(),objconta.getTelefono(),objconta.getFecha_nacimiento()))>0) {
                    Toast.makeText(getBaseContext(), "Contacto Insertado", Toast.LENGTH_SHORT).show();
                    cargardatos();
                }else{
                    Toast.makeText(getBaseContext(), "No se pudo Insertar el Contacto", Toast.LENGTH_SHORT).show();
                }



            }catch (Exception err){
                Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.insertar) {
            Intent siguiente = new Intent(getApplication(),Datas.class);
            startActivityForResult(siguiente,1000);
            return true;
        }else if (id == R.id.actualizar) {
            cargardatos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
