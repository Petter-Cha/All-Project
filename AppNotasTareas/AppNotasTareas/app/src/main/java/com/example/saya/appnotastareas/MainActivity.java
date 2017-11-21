package com.example.saya.appnotastareas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saya.appnotastareas.BD.DAONotas;
import com.example.saya.appnotastareas.BD.DAOTareas;
import com.example.saya.appnotastareas.BD.Notas;
import com.example.saya.appnotastareas.BD.Tareas;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lsv;
    ListView lsv2;
    ListView lsvt;
    ListView lsvt2;
    ArrayAdapter adp;
    ArrayAdapter adpT;
    private EditText bucar;
    String buscarcadena;
    Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarUI();
        inicializaTarea();
    }

    //actualizo la lista de notas
    //Y muestro todas las notas que están en la base de datos
    private void inicializarUI() {
        lsv = (ListView)findViewById(R.id.lsv);

        DAONotas dao = new DAONotas(this);

        List<Notas> lst = dao.getAll();

        adp = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
                lst);

        lsv.setAdapter(adp);

        lsv.setOnCreateContextMenuListener(this);
    }


    //actualizo la lista de tareas
    //Y muestro todas las tareas que están en la base de datos
    private void inicializaTarea() {
        lsvt = (ListView)findViewById(R.id.lsvT);

        DAOTareas dao = new DAOTareas(this);

        List<Tareas> lstg = dao.getAllTarea();

        adpT = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
                lstg);

        lsvt.setAdapter(adpT);

        lsvt.setOnCreateContextMenuListener(this);
    }

    //Es para cuando selecciones una nota te aparezca el menú para editar o eliminar
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "editar");
        menu.add(0, 1, 1, "eliminar");
    }

    //es para realizar la acción o las operaciones según de lo que hayamos
    // elegido del menú, es decir, para realizar la acción de editar o eliminar
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemlsv =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo() ;

        if (item.getItemId()== 0){
            Toast.makeText(this, "Actualizar", Toast.LENGTH_SHORT).show();

            Notas u = (Notas) adp.getItem(itemlsv.position);



            Intent intent = new Intent(this, EditarNota.class);
            intent.putExtra("ID", u.getIdNota());
            intent.putExtra("NOMBRE", u.getNombre());
            intent.putExtra("DESCRIPCION", u.getDescripcion());
            intent.putExtra("FECHA", u.getFecha());
            intent.putExtra("FOTO", u.getFoto());
            intent.putExtra("VIDEO", u.getVideo());
            intent.putExtra("AUDIO", u.getAudio());
            startActivityForResult(intent, 2);
        }

        if (item.getItemId()== 1) {
            Notas u = (Notas) adp.getItem(itemlsv.position);

            Toast.makeText(this,
                    "Elemento a eliminar: " + String.valueOf( u.getIdNota()) ,
                    Toast.LENGTH_LONG).show();

            DAONotas dao = new DAONotas(this);

            if( dao.delete(u.getIdNota()) != 0) {
                adp.remove(u);
                adp.notifyDataSetChanged();
            }
        }

        return super.onContextItemSelected(item);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            Notas notas;
            notas = new Notas(Integer.parseInt(data.getStringExtra("Id")), data.getStringExtra("Nombre"),
                    data.getStringExtra("Descripcion"), data.getStringExtra("Fecha"), data.getStringExtra("Foto"), data.getStringExtra("Video"), data.getStringExtra("Audio"));
            DAONotas dao = new DAONotas(this);
            dao.updateOneByID(notas);
            inicializarUI();

        } else {
            Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show();
            inicializarUI();
            inicializaTarea();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //Manejar los clics del elemento de la barra de acción aquí.
        // La barra de acciones gestionará automáticamente los clics en
        // el botón Inicio / Arriba, siempre que especifique una actividad
        // principal en AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.menu_actualizar:
                inicializarUI();
                inicializaTarea();
                break;
            case R.id.menu_buscr:

                break;
            case R.id.menu_acerca:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Message from About ");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.create().show();
                break;
        }

        return false;
    }

    public void clickbtn_agregar(View v){
        notaOtarea();
    }


    public void clickbtn_actualizar(View v){
        inicializarUI();
        inicializaTarea();
    }

    public void clickbtn_buscar(View v){
        lsv2 = (ListView)findViewById(R.id.lsv);
        lsvt2 = (ListView)findViewById(R.id.lsvT);

        DAONotas dao = new DAONotas(this);
        DAOTareas daot = new DAOTareas(this);

        EditText bucar = (EditText)findViewById(R.id.editBuscar);
        buscarcadena = bucar.getText().toString();

        //se manda llamar los metodos coreespondientes para la busqueda de las Notas y Tareas
        List<Notas> lst = dao.getAllNotasByName(buscarcadena);
        List<Tareas> lstTa = daot.getAllNotasByNameN(buscarcadena);

        adp = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
                lst);

        adpT = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,
                lstTa);

        lsv2.setAdapter(adp);
        lsvt2.setAdapter(adpT);

        lsv2.setOnCreateContextMenuListener(this);
        lsvt2.setOnCreateContextMenuListener(this);


    }

    //Metodo para crear o mandar llamar la actividad de Notas
    public void xmlNota(){
        Intent agregarNota = new Intent(this,AgregarNota.class);
        startActivity(agregarNota);
    }
    //Metodo para crear o mandar llamar la actividad de Tareas
    public void xmlTarea(){
        Intent agregarTarea = new Intent(this,AgregarTarea.class);
        startActivity(agregarTarea);
    }

    //muestra un mensaje, tipo JOption para verificar que se desea agregar, una nota u una tarea....
    public  AlertDialog notaOtarea(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence [] items = new CharSequence[2];

        items [0] = "Note";
        items [1] = "Task";

        final AlertDialog.Builder builder1 = builder;
        builder1.setTitle("What do you want to add?");
        builder1.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplication(), "you selected: " + items[i], Toast.LENGTH_SHORT).show();
                if (items[i] == "Note") {
                    xmlNota();
                } else {
                    Toast.makeText(getApplication(), "you selected: " + items[i], Toast.LENGTH_SHORT).show();
                    xmlTarea();
                }

            }
        });
        builder1.setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = builder.create();

        return
                builder.show();
    }


}
