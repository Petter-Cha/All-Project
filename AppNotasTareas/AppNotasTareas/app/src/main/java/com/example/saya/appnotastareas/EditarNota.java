package com.example.saya.appnotastareas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.saya.appnotastareas.BD.DAONotas;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditarNota extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    String ide;
    String nombree;
    String descripe;
    String fechae;
    String fotoe;
    String videoe;
    String audioe;
    private EditText txtIdE=null;
    private EditText txtNombreE=null;
    private EditText txtDescripE=null;
    private EditText txtFechaE=null;
    private VideoView vvwE;

    public String dirAudio;
    public String dirVideo;
    public String dirfOTO;
    private ImageView img;


    ImageButton btnGrabart ,btnReproducirt;
    boolean flag = true;
    MediaRecorder mr = null;
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editar_nota);
        txtIdE = (EditText) findViewById(R.id.txtId);
        txtNombreE = (EditText) findViewById(R.id.txtNombreE);
        txtDescripE = (EditText) findViewById(R.id.txtDescripcionE);
        txtFechaE = (EditText) findViewById(R.id.txtFechaE);
        vvwE = (VideoView)findViewById(R.id.vvwE);
        btnGrabart = (ImageButton)findViewById(R.id.btnCapAudioE);
        btnReproducirt = (ImageButton)findViewById(R.id.btnCapAudioreE);

        intent = getIntent();
        ide = intent.getIntExtra("ID", 0)+"";
        nombree = intent.getStringExtra("NOMBRE");
        descripe = intent.getStringExtra("DESCRIPCION");
        fechae = intent.getStringExtra("FECHA");
        videoe = intent.getStringExtra("VIDEO");
        audioe = intent.getStringExtra("AUDIO");


        txtIdE.setText(ide);
        txtIdE.setEnabled(false);
        txtNombreE.setText(nombree);
        txtDescripE.setText(descripe);
        //vvwE.setVideoURI(Uri.parse(videoe));

        Toast.makeText(this, "Tarea video direccion "+videoe, Toast.LENGTH_LONG).show();


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        // Now we display formattedDate value in TextView
        TextView fechahoraE = (TextView)findViewById(R.id.txtFechaE);
        fechahoraE.setText(formattedDate);
        fechahoraE.setEnabled(false);

        btnGrabart.setOnClickListener(this);
        btnReproducirt.setOnClickListener(this);
    }

    public void save(View button){
        String textID = txtIdE.getText().toString();
        String textNOMBRE = txtNombreE.getText().toString();
        String textDESCRIPCION = txtDescripE.getText().toString();
        String textFECHA = txtFechaE.getText().toString();

        Intent intent = getIntent();
        intent.putExtra("Id", textID);
        intent.putExtra("Nombre", textNOMBRE);
        intent.putExtra("Descripcion", textDESCRIPCION);
        intent.putExtra("Fecha", textFECHA);
        intent.putExtra("Foto", "");
        intent.putExtra("Video", dirVideo);
        intent.putExtra("Audio", dirAudio);

        setResult(RESULT_OK, intent);
        finish();

    }

    public void cancel(View button){
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

    public void grabar_video(View view){
        startActivityForResult(new Intent(MediaStore.ACTION_VIDEO_CAPTURE), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri video = data.getData();
        vvwE.setVideoURI(video);
        video.toString();
        //dirVideo=video.toString();
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void onGrabar(boolean action){
        if(action){
            //btnGrabar.setImageResource();
            Toast.makeText(this, "On Grabar", Toast.LENGTH_SHORT).show();
            flag = false;
            this.starGrabar();
        }else {
            //btnGrabar.setText("Grabar audio");
            Toast.makeText(this, "Aduio grabado", Toast.LENGTH_SHORT).show();
            btnReproducirt.setEnabled(true);
            flag = true;
            this.stopGrabar();
        }
    }

    private void onReproducir(boolean action){
        if (action){
            //btnReproducir.setText("Parar reproducir");
            Toast.makeText(this, "Reproduciondo grabacion!", Toast.LENGTH_SHORT).show();
            flag = false;
            btnGrabart.setEnabled(false);
            this.starReproducir();
        } else {

            //btnReproducir.setText("Reproducir audio");
            flag = true;
            btnGrabart.setEnabled(true);
            this.stopReproducir();
            Toast.makeText(this, "Fin de la grabacion", Toast.LENGTH_SHORT).show();
        }
    }


    private void starGrabar(){
        mr = new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        File dir = Environment.getExternalStorageDirectory();
        File audio = new File(dir,audioe);
        mr.setOutputFile(audio.getAbsolutePath());
        dirAudio=audio.getAbsolutePath();
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mr.prepare();
        } catch (IOException e){
            e.printStackTrace();
        }
        mr.start();
    }

    private void stopGrabar(){
        mr.stop();
        mr.release();
        mr = null;
    }

    private void starReproducir(){
        mp = new MediaPlayer();
        try {
            mp.setDataSource(audioe);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void stopReproducir(){
        mp.stop();
        mp.release();
        mp = null;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnGrabart)){
            this.onGrabar(flag);
            Toast.makeText(this, "Grabando Audio", Toast.LENGTH_SHORT).show();
        }

        if (view.equals(btnReproducirt)){
            this.onReproducir(flag);
        }
    }
}
