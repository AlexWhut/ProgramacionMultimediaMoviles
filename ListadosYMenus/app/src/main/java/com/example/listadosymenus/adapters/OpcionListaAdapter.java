package com.example.listadosymenus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listadosymenus.R;
import com.example.listadosymenus.pojos.OpcionLista;

import java.util.ArrayList;
import java.util.List;

public class OpcionListaAdapter extends ArrayAdapter<OpcionLista> {

    private Context context;
    private ArrayList<OpcionLista> opciones;

    public OpcionListaAdapter(Context context, ArrayList<OpcionLista> opciones) {
        super(context, 0, opciones);
        this.context = context;
        this.opciones = opciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item_opcion, parent, false);
        }

        OpcionLista opcionActual = opciones.get(position);

        TextView textViewOpcion = listItem.findViewById(R.id.textViewOpcion);
        textViewOpcion.setText(opcionActual.getTexto());

        // Aplicar los estilos guardados en el modelo de datos
        textViewOpcion.setTextColor(opcionActual.getColorTexto());
        textViewOpcion.setTypeface(null, opcionActual.getEstiloFuente());
        textViewOpcion.setTextSize(opcionActual.getTamanoFuente());

        return listItem;
    }
}

