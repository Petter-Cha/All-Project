package com.example.saya.agenda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String datas="";
    private Button addContacts;
    private ListView Contacts;
    private ArrayList<String> ContactList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;//intermediario para poder pasarle los datos del arrraylist al listview
    //private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContacts=(Button) findViewById(R.id.btnAdd);
        addContacts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent next = new Intent(getApplication(), Datos.class);//sirve para abrir una nueva actividad en este caso Datos
                startActivityForResult(next,1000);//para visualizar los daot socmo un .show

            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);// metodo que captura los datos del objeto

        if(resultCode==RESULT_OK){
            try {
                //Para obtener el objeto de contact
                Contact contactobj = (Contact) data.getSerializableExtra("My-Contact");//My-contact es el id, por el cual se identifica el objeto
                String ConstraitContact = "Name: "+contactobj.get_Name()+// se concatena una sola cadena con todos los datos del contacto
                        "\nEmail: "+contactobj.get_Email()+
                        "\nTwitter: "+contactobj.get_Twitter()+
                        "\nPhone: "+contactobj.get_Phone()+
                        "\nDate: "+contactobj.get_Birthdate()+
                        "\n";
                ContactList.add(ConstraitContact);// se agrgan los datos al array
                adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ContactList);// se crea una instancia y se le agregan los datos al adapter
                Contacts = (ListView) findViewById(R.id.Lisdatas);//se crea una instancia
                Contacts.setAdapter(adapter);// se le asignan los valore del adapter
                adapter.notifyDataSetChanged();//actualizar el adapter
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();

            }

        }

    }
}
