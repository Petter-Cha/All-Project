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
import android.widget.ArrayAdapter;
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
import com.example.saya.appnotastareas.BD.Notas;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AgregarNota extends AppCompatActivity implements View.OnClickListener{

    String path;
    Intent intent;
    String nombre;
    String descripcio;
    String fecha;
    private  EditText txtNombre = null;
    private  EditText txtDescripcion = null;
    private  EditText txtFecha = null;
    ArrayAdapter adp;
    private Switch switchMulti;
    private ImageButton capturaFoto;
    private ImageButton capturarVideo;
    private ImageButton capturarAudio;
    private ImageButton capturarAudiore;
    private TextView foto;
    private TextView video;
    private TextView audio;
    private VideoView vvw;
    public String dirAudio;
    public String dirVideo;
    public String dirfOTO;
    private ImageView img;
    ImageView getimage;

    private static String APP_DIRECTORY="MyPictureApp/";
    private static String MEDIA_DIRECTORY=APP_DIRECTORY+"PictureApp";
    private final int PHOTO_CODE = 200;

    ImageButton btnGrabar ,btnReproducir;
    boolean flag = true;
    MediaRecorder mr = null;
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nueva_nota);


        capturaFoto = (ImageButton)findViewById(R.id.btnCaptuFoto);
        capturarVideo = (ImageButton)findViewById(R.id.btnCapVideo);
        capturarAudio = (ImageButton)findViewById(R.id.btnCapAudio);
        capturarAudiore = (ImageButton)findViewById(R.id.btnCapAudiore);
        foto = (TextView)findViewById(R.id.tvfoto);
        video = (TextView)findViewById(R.id.tvvideo);
        audio = (TextView)findViewById(R.id.tvAudio);
        vvw = (VideoView)findViewById(R.id.vvw);
        img = (ImageView)findViewById(R.id.img);

        foto.setVisibility(View.INVISIBLE);
        video.setVisibility(View.INVISIBLE);
        audio.setVisibility(View.INVISIBLE);
        capturaFoto.setVisibility(View.INVISIBLE);
        capturarVideo.setVisibility(View.INVISIBLE);
        capturarAudio.setVisibility(View.INVISIBLE);
        capturarAudiore.setVisibility(View.INVISIBLE);
        vvw.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        // Now we display formattedDate value in TextView
        TextView fechahora = (TextView)findViewById(R.id.txtFecha);
        fechahora.setText(formattedDate);


        btnGrabar = (ImageButton)findViewById(R.id.btnCapAudio);
        btnReproducir = (ImageButton)findViewById(R.id.btnCapAudiore);

            btnGrabar.setOnClickListener(this);
            btnReproducir.setOnClickListener(this);


        vvw.setMediaController(new MediaController(this));
        //extraer datos de los edittext

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        txtFecha = (EditText)findViewById(R.id.txtFecha);

        switchMulti = (Switch)findViewById(R.id.swMultimedia);
        switchMulti.setChecked(false);

        switchMulti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==false){
                    Toast.makeText(getApplicationContext(),"Multimedia desabilitada", Toast.LENGTH_SHORT).show();
                    foto.setVisibility(View.INVISIBLE);
                    video.setVisibility(View.INVISIBLE);
                    audio.setVisibility(View.INVISIBLE);
                    capturaFoto.setVisibility(View.INVISIBLE);
                    capturarVideo.setVisibility(View.INVISIBLE);
                    capturarAudio.setVisibility(View.INVISIBLE);
                    capturarAudiore.setVisibility(View.INVISIBLE);
                    vvw.setVisibility(View.INVISIBLE);
                    img.setVisibility(View.INVISIBLE);
                }
                if(b==true) {
                    Toast.makeText(getApplicationContext(),"Multimedia habilitada",Toast.LENGTH_SHORT).show();
                    foto.setVisibility(View.VISIBLE);
                    video.setVisibility(View.VISIBLE);
                    audio.setVisibility(View.VISIBLE);
                    capturaFoto.setVisibility(View.VISIBLE);
                    capturarVideo.setVisibility(View.VISIBLE);
                    capturarAudio.setVisibility(View.VISIBLE);
                    capturarAudiore.setVisibility(View.VISIBLE);
                    vvw.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void btnCaptuFoto_click(View v){
        //startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);


        File file = new File(Environment.getExternalStorageDirectory(),MEDIA_DIRECTORY);
        boolean isDirectoryCreated=file.exists();
        if(!isDirectoryCreated){
            isDirectoryCreated=file.mkdirs();}
        if (isDirectoryCreated) {
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";
            path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;
            File newFile = new File(path);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }


    public void save(View button){

        nombre = txtNombre.getText().toString();
        descripcio = txtDescripcion.getText().toString();
        fecha = txtFecha.getText().toString();


        DAONotas dao = new DAONotas(this);

        if(dirVideo!=null && dirAudio!=null){
            dao.insertNota(new Notas(0, nombre, descripcio, fecha, null, dirVideo, dirAudio));
            Toast.makeText(this, "Nota agregada!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            dao.insertNota(new Notas(0, nombre, descripcio, fecha, null, null, null));
            Toast.makeText(this, "Nota agregada!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void cancel(View button){
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

    @Override
    public void onClick(View view) {

        if (view.equals(btnGrabar)){
            this.onGrabar(flag);
            Toast.makeText(this, "Grabando Audio", Toast.LENGTH_SHORT).show();
        }

        if (view.equals(btnReproducir)){
            this.onReproducir(flag);
        }
    }

    public void grabar_video(View view){
        startActivityForResult(new Intent(MediaStore.ACTION_VIDEO_CAPTURE), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            Uri video = data.getData();
            vvw.setVideoURI(video);
            video.toString();
            dirVideo=video.toString();
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
            btnReproducir.setEnabled(true);
            flag = true;
            this.stopGrabar();
        }
    }

    private void onReproducir(boolean action){
        if (action){
            //btnReproducir.setText("Parar reproducir");
            Toast.makeText(this, "Reproduciondo grabacion!", Toast.LENGTH_SHORT).show();
            flag = false;
            btnGrabar.setEnabled(false);
            this.starReproducir();
        } else {

            //btnReproducir.setText("Reproducir audio");
            flag = true;
            btnGrabar.setEnabled(true);
            this.stopReproducir();
            Toast.makeText(this, "Fin de la grabacion", Toast.LENGTH_SHORT).show();
        }
    }


    private void starGrabar(){
        fecha = txtFecha.getText().toString();

        mr = new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        File dir = Environment.getExternalStorageDirectory();
        File audio = new File(dir,"migrabacion"+fecha+".3gp");
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
        fecha = txtFecha.getText().toString();
        mp = new MediaPlayer();
        try {
            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/migrabacion"+fecha+".3gp");
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
}
