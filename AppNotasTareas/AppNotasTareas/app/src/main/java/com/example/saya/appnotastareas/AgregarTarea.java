package com.example.saya.appnotastareas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.saya.appnotastareas.BD.DAONotas;
import com.example.saya.appnotastareas.BD.DAOTareas;
import com.example.saya.appnotastareas.BD.Notas;
import com.example.saya.appnotastareas.BD.Tareas;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AgregarTarea extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    String nombreT;
    String descripcioT;
    String fechaT;
    private  EditText txtNombreT = null;
    private  EditText txtDescripcionT = null;
    private  EditText txtFechaT = null;
    private Switch switchMultiT;
    private ImageButton capturaFotoT;
    private ImageButton capturarVideoT;
    private ImageButton capturarAudioT;
    private ImageButton capturarAudioreT;
    private TextView fotoT;
    private TextView videoT;
    private TextView audioT;
    private VideoView vvwT;
    public String dirAudioT;
    public String dirVideoT;
    public String dirfOTOT;
    private ImageView imgT;
    CheckBox recor;
    boolean rec=false;

    ImageButton btnGrabarT ,btnReproducirT;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nueva_tarea);


        capturaFotoT = (ImageButton)findViewById(R.id.btnCaptuFotoT);
        capturarVideoT = (ImageButton)findViewById(R.id.btnCapVideoT);
        capturarAudioT = (ImageButton)findViewById(R.id.btnCapAudioT);
        capturarAudioreT = (ImageButton)findViewById(R.id.btnCapAudioreT);
        fotoT = (TextView)findViewById(R.id.tvfotoT);
        videoT = (TextView)findViewById(R.id.tvvideoT);
        audioT = (TextView)findViewById(R.id.tvAudioT);
        vvwT = (VideoView)findViewById(R.id.vvwT);
        imgT = (ImageView)findViewById(R.id.imgT);
        recor = (CheckBox)findViewById(R.id.chkRe);

        fotoT.setVisibility(View.INVISIBLE);
        videoT.setVisibility(View.INVISIBLE);
        audioT.setVisibility(View.INVISIBLE);
        capturaFotoT.setVisibility(View.INVISIBLE);
        capturarVideoT.setVisibility(View.INVISIBLE);
        capturarAudioT.setVisibility(View.INVISIBLE);
        capturarAudioreT.setVisibility(View.INVISIBLE);
        vvwT.setVisibility(View.INVISIBLE);
        imgT.setVisibility(View.INVISIBLE);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        // Now we display formattedDate value in TextView
        TextView fechahoraT = (TextView)findViewById(R.id.txtFechaT);
        fechahoraT.setText(formattedDate);


        btnGrabarT = (ImageButton)findViewById(R.id.btnCapAudioT);
        btnReproducirT = (ImageButton)findViewById(R.id.btnCapAudioreT);

        btnGrabarT.setOnClickListener(this);
        btnReproducirT.setOnClickListener(this);


        vvwT.setMediaController(new MediaController(this));
        //extraer datos de los edittext

        txtNombreT = (EditText)findViewById(R.id.txtNombreT);
        txtDescripcionT = (EditText)findViewById(R.id.txtDescripcionT);
        txtFechaT = (EditText)findViewById(R.id.txtFechaT);

        switchMultiT = (Switch)findViewById(R.id.swMultimediaT);
        switchMultiT.setChecked(false);

        switchMultiT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==false){
                    Toast.makeText(getApplicationContext(), "Multimedia desabilitada", Toast.LENGTH_SHORT).show();
                    fotoT.setVisibility(View.INVISIBLE);
                    videoT.setVisibility(View.INVISIBLE);
                    audioT.setVisibility(View.INVISIBLE);
                    capturaFotoT.setVisibility(View.INVISIBLE);
                    capturarVideoT.setVisibility(View.INVISIBLE);
                    capturarAudioT.setVisibility(View.INVISIBLE);
                    capturarAudioreT.setVisibility(View.INVISIBLE);
                    vvwT.setVisibility(View.INVISIBLE);
                    imgT.setVisibility(View.INVISIBLE);
                }
                if(b==true) {
                    Toast.makeText(getApplicationContext(),"Multimedia habilitada",Toast.LENGTH_SHORT).show();
                    fotoT.setVisibility(View.VISIBLE);
                    videoT.setVisibility(View.VISIBLE);
                    audioT.setVisibility(View.VISIBLE);
                    capturaFotoT.setVisibility(View.VISIBLE);
                    capturarVideoT.setVisibility(View.VISIBLE);
                    capturarAudioT.setVisibility(View.VISIBLE);
                    capturarAudioreT.setVisibility(View.VISIBLE);
                    vvwT.setVisibility(View.VISIBLE);
                    imgT.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    public void save(View button){

        nombreT = txtNombreT.getText().toString();
        descripcioT = txtDescripcionT.getText().toString();
        fechaT = txtFechaT.getText().toString();


        DAOTareas dao = new DAOTareas(this);
        dao.insertTara(new Tareas(0, nombreT, descripcioT, fechaT, null, null, null,"holo"));
        Toast.makeText(this, "Tarea agregada!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View button){
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

    @Override
    public void onClick(View view) {

    }
}
