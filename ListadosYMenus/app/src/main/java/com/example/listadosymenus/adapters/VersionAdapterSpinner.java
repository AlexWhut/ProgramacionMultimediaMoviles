package com.example.listadosymenus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listadosymenus.R;
import com.example.listadosymenus.pojos.Encapsulador;

import java.util.List;

public class VersionAdapterSpinner extends BaseAdapter {

    private Context context;
    private List<Encapsulador> listaVersiones;

    public VersionAdapterSpinner(Context context, List<Encapsulador> listaVersiones) {
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
            // Usamos el layout simple sin RadioButton
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_version_simple, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imageViewVersion);
            holder.nombre = convertView.findViewById(R.id.textViewVersion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Obtener el objeto de datos para la posición actual
        Encapsulador versionActual = listaVersiones.get(position);

        // Asignar los valores a las vistas
        holder.imagen.setImageResource(versionActual.getIdImagen());
        holder.nombre.setText(versionActual.getTitulo());
        
        return convertView;
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombre;
    }
}
