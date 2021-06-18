package com.example.alexander.tesis;

import android.content.Intent;

public class Persona {
    private String nombre;
    private String genero;
    private String origen;
    private String z_elegida;
    private Integer idarbol;


    public Persona(String nombre, String genero,String origen, String z_elegida,Integer idarbol) {
        this.nombre=nombre;
        this.genero=genero;
        this.origen=origen;
        this.z_elegida=z_elegida;
        this.idarbol=idarbol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getOrigen() {return origen;}

    public String getZ_elegida() {return z_elegida;}

    public Integer getIdarbol() {return idarbol;}
}