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

import java.util.ArrayList;

/**
 * Diferencia Clave: BaseAdapter tiene un método getDropDownView() que es llamado específicamente
 * para renderizar las vistas en la lista desplegable del Spinner. En este caso, como queremos
 * que la vista cerrada y la del desplegable se vean igual, hemos creado un método auxiliar
 * createItemView que es llamado por ambos, getView() y getDropDownView().
 */
public class VersionAdapterSpinner extends BaseAdapter {

    private Context context;
    private ArrayList<Encapsulador> datos;
    private LayoutInflater inflater;

    public VersionAdapterSpinner(Context context, ArrayList<Encapsulador> datos) {
        this.context = context;
        this.datos = datos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Este método se usa para la vista del Spinner cuando está "cerrado".
     * Podemos usar el mismo layout que el desplegable para simplificar.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    /**
     * Este método se usa para cada elemento de la lista desplegable.
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    /**
     * Método auxiliar para crear las vistas, ya que son idénticas en nuestro caso.
     */
    private View createItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imageViewVersion);
            holder.texto = convertView.findViewById(R.id.textViewVersion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Encapsulador versionActual = datos.get(position);
        holder.imagen.setImageResource(versionActual.getIdImagen());
        holder.texto.setText(versionActual.getTitulo());

        return convertView;
    }

    private static class ViewHolder {
        ImageView imagen;
        TextView texto;
    }
}

