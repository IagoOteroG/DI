package com.example;


public class Entrenamiento {
    private int id;
    private String nombre;
    private String fecha;
    private int horas;
    private int minutos;
    private int segundos;
    private int kilometros;
    private int metros;
    private String tipo;
    private int color_resource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getColorResource() {
        return color_resource;
    }
    public Entrenamiento(){
        super();
    }
    public void setColorResource(int color_resource) {
        this.color_resource = color_resource;
    }

    public Entrenamiento(String nombre, String fecha, int horas, int minutos, int segundos, int kilometros, int metros, String tipo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
        this.kilometros = kilometros;
        this.metros = metros;
        this.tipo = tipo;
    }
    @Override
    public String toString() {

        return nombre ;
    }
}
