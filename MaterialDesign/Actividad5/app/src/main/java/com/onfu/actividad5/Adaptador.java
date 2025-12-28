package com.onfu.actividad5;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adaptador para el RecyclerView.
 * Personalización: Se usa el campo colorFondo para cambiar el fondo de cada tarjeta.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.miHolder> {
    private List<Encapsulador> entradas;

    public Adaptador(List<Encapsulador> entradas) {
        this.entradas = entradas;
    }

    public static class miHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView titulo;
        public TextView texto;
        public View layoutFondo; // Personalización

        public miHolder(View vista) {
            super(vista);
            imagen = vista.findViewById(R.id.imagen);
            titulo = vista.findViewById(R.id.titulo);
            texto = vista.findViewById(R.id.texto);
            layoutFondo = vista.findViewById(R.id.layoutFondo); // Personalización
        }
    }

    @NonNull
    @Override
    public miHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carta, viewGroup, false);
        return new miHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull miHolder viewHolder, int i) {
        Encapsulador item = entradas.get(i);
        viewHolder.imagen.setImageResource(item.get_idImagen());
        viewHolder.titulo.setText(item.get_textotitulo());
        viewHolder.texto.setText(item.get_textoContenido());
        // Personalización: cambiar color de fondo de la tarjeta según el campo colorFondo de Encapsulador
        viewHolder.layoutFondo.setBackgroundColor(item.getColorFondo());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }
}
