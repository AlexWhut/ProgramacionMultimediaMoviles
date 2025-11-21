package com.example.listadosymenus.pojos;

import android.graphics.Color;
import android.graphics.Typeface;

public class OpcionLista {
    private String texto;
    private int colorTexto;
    private int estiloFuente; // Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC
    private float tamanoFuente; // En SP

    public OpcionLista(String texto) {
        this.texto = texto;
        this.colorTexto = Color.BLACK; // Color por defecto
        this.estiloFuente = Typeface.NORMAL; // Estilo por defecto
        this.tamanoFuente = 18; // Tamaño por defecto en SP
    }

    // --- Getters y Setters ---

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(int colorTexto) {
        this.colorTexto = colorTexto;
    }

    public int getEstiloFuente() {
        return estiloFuente;
    }

    public void setEstiloFuente(int estiloFuente) {
        this.estiloFuente = estiloFuente;
    }

    public float getTamanoFuente() {
        return tamanoFuente;
    }

    public void setTamanoFuente(float tamanoFuente) {
        this.tamanoFuente = tamanoFuente;
    }
}

