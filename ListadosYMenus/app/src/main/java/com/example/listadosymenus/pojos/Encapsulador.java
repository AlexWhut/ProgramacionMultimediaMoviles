package com.example.listadosymenus.pojos;

public class Encapsulador {
    private int idImagen;
    private String titulo;
    private String descripcion;
    private boolean seleccionado;

    public Encapsulador(int idImagen, String titulo, String descripcion, boolean seleccionado) {
        this.idImagen = idImagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.seleccionado = seleccionado;
    }

    // --- Getters y Setters ---

    public int getIdImagen() {
        return idImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * Clave: El campo boolean seleccionado es fundamental.
     * Almacenará en el propio dato si su RadioButton correspondiente
     * debe estar marcado o no.
     * @param seleccionado
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}

