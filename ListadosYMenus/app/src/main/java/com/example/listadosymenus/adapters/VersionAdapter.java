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

import java.util.List;

public class VersionAdapter extends BaseAdapter {

    private Context context;
    private List<Encapsulador> listaVersiones;

    public VersionAdapter(Context context, List<Encapsulador> listaVersiones) {
        this.context = context;
        this.listaVersiones = listaVersiones;
    }

    @Override
    public int getCount() {
        return listaVersiones.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVersiones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_version, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imageViewVersion);
            holder.radioButton = convertView.findViewById(R.id.radioButtonVersion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Obtener el objeto de datos para la posición actual
        Encapsulador versionActual = listaVersiones.get(position);

        // Asignar los valores a las vistas
        holder.imagen.setImageResource(versionActual.getIdImagen());
        holder.radioButton.setText(versionActual.getTitulo());
        
        // Control del estado del RadioButton
        // IMPORTANTE: Desactivamos el listener o lo hacemos no clickable para que sea la fila quien maneje el evento
        holder.radioButton.setChecked(versionActual.isSeleccionado());
        holder.radioButton.setClickable(false);
        holder.radioButton.setFocusable(false);

        return convertView;
    }

    static class ViewHolder {
        ImageView imagen;
        RadioButton radioButton;
    }
}
