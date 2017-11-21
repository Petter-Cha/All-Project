package com.example.saya.appnotastareas.BD;

import java.text.DateFormat;

public class Notas {
    int idNota;
    String nombre;
    String descripcion;
    String fecha;
    String foto;
    String video;
    String audio;


    public Notas(int idNota, String nombre, String descripcion, String fecha, String foto, String video, String audio) {
        this.idNota = idNota;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.foto = foto;
        this.video = video;
        this.audio = audio;
    }

    //lo mas basico, Setters y Getters de la clase Notas
    public int getIdNota() {
        return idNota;
    }

    public void setId(int idNota) {
        this.idNota = idNota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return String.valueOf(  this.idNota) + "  " + this.nombre + " "+ this.descripcion;
        //+ " "+ this.audio + " "+this.video;
    }
}
