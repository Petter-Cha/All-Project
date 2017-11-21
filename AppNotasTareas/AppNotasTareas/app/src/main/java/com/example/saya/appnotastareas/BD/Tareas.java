package com.example.saya.appnotastareas.BD;


public class Tareas {
    int idTareas;
    String nombre;
    String descripcion;
    String fecha;
    String foto;
    String video;
    String audio;
    String recordatorio;

    public Tareas(int idTareas, String nombre, String descripcion, String fecha, String foto, String video, String audio, String recordatorio) {
        this.idTareas = idTareas;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.foto = foto;
        this.video = video;
        this.audio = audio;
        this.recordatorio = recordatorio;
    }

    //lo mas basico, Setters y Getters de la clase Tareas
    public int getIdTareas() {
        return idTareas;
    }

    public void setIdTareas(int idTareas) {
        this.idTareas = idTareas;
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

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    @Override
    public String toString() {
        return String.valueOf(  this.idTareas) + "  " + this.nombre + " "+ this.descripcion;
        //+ " "+ this.audio + " "+this.video;
    }
}
