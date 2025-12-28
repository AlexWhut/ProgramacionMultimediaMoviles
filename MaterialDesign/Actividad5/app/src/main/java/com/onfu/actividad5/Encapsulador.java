package com.onfu.actividad5;

/**
 * POJO (Plain Old Java Object) para encapsular los datos de cada tarjeta.
 * Personalización: Se añade un campo colorFondo para personalizar el color de fondo de cada tarjeta.
 */
public class Encapsulador {
    private int idImagen;
    private String titulo;
    private String textoContenido;
    private int colorFondo; // Personalización: color de fondo para cada tarjeta

    public Encapsulador(int idImagen, String textoTitulo, String textoContenido, int colorFondo) {
        this.idImagen = idImagen;
        this.titulo = textoTitulo;
        this.textoContenido = textoContenido;
        this.colorFondo = colorFondo;
    }

    public int get_idImagen() { return idImagen; }
    public String get_textotitulo() { return titulo; }
    public String get_textoContenido() { return textoContenido; }
    public int getColorFondo() { return colorFondo; } // Personalización
}
