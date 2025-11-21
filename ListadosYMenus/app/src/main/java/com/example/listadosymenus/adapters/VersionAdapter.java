package com.example.listadosymenus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.listadosymenus.R;
import com.example.listadosymenus.pojos.Encapsulador;

import java.util.ArrayList;

public class VersionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Encapsulador> datos;
    private LayoutInflater inflater;

    public VersionAdapter(Context context, ArrayList<Encapsulador> datos) {
        this.context = context;
        this.datos = datos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datos.size(); // Devuelve el número total de elementos
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position); // Devuelve el objeto en una posición
    }



    @Override
    public long getItemId(int position) {
        return position; // Devuelve un ID único para la fila
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Si la vista es nueva, la "inflamos" (creamos) a partir de nuestro XML
            convertView = inflater.inflate(R.layout.list_item_version, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imageViewVersion);
            holder.radioButton = convertView.findViewById(R.id.radioButtonVersion);
            convertView.setTag(holder); // Guardamos el holder para reutilizarlo
        } else {
            // Si la vista se está reciclando, recuperamos el holder
            holder = (ViewHolder) convertView.getTag();
        }

        // Obtenemos el objeto actual
        Encapsulador versionActual = datos.get(position);

        // Volcamos los datos en las vistas
        holder.imagen.setImageResource(versionActual.getIdImagen());
        holder.radioButton.setText(versionActual.getTitulo());

        // La clave: el estado del RadioButton depende del estado del objeto de datos
        holder.radioButton.setChecked(versionActual.isSeleccionado());

        return convertView;
    }

    // Patrón ViewHolder para un rendimiento óptimo
    private static class ViewHolder {
        ImageView imagen;
        RadioButton radioButton;
    }
}

