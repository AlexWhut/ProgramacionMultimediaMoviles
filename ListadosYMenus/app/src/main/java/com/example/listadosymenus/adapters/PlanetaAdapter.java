package com.example.listadosymenus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listadosymenus.R;
import com.example.listadosymenus.pojos.Planeta;

import java.util.List;

public class PlanetaAdapter extends ArrayAdapter<Planeta> {

    public PlanetaAdapter(@NonNull Context context, @NonNull List<Planeta> planetas) {
        super(context, 0, planetas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // 1. Obtener el objeto Planeta para esta posición
        Planeta planetaActual = getItem(position);

        // 2. Patrón ViewHolder para optimizar el rendimiento
        ViewHolder holder;

        // Si la vista no está creada (es la primera vez), inflarla y crear el ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_planeta_complejo, parent, false);
            holder = new ViewHolder();
            holder.nombre = convertView.findViewById(R.id.textViewNombre);
            holder.descripcion = convertView.findViewById(R.id.textViewDescripcion);
            holder.imagen = convertView.findViewById(R.id.imageViewPlaneta);
            holder.favorito = convertView.findViewById(R.id.checkBoxFavorito);
            convertView.setTag(holder); // Guardamos el holder en la vista
        } else {
            // Si la vista ya existe (se está reciclando), recuperamos el holder
            holder = (ViewHolder) convertView.getTag();
        }

        // 3. Volcar los datos del objeto Planeta en las vistas
        holder.nombre.setText(planetaActual.getNombre());
        holder.descripcion.setText(planetaActual.getDescripcion());
        holder.imagen.setImageResource(planetaActual.getIdImagen());

        // Gestionar el estado del CheckBox
        holder.favorito.setOnCheckedChangeListener(null); // Limpiar listener anterior para evitar problemas de reciclaje
        holder.favorito.setChecked(planetaActual.isEsFavorito());

        holder.favorito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Actualizar el estado en nuestro objeto de datos
            planetaActual.setEsFavorito(isChecked);
            String estado = isChecked ? "añadido a favoritos" : "quitado de favoritos";
            Toast.makeText(getContext(), planetaActual.getNombre() + " " + estado, Toast.LENGTH_SHORT).show();
        });

        // 4. Devolver la vista completa para ser mostrada
        return convertView;
    }

    /**
     * El patrón ViewHolder evita las llamadas repetitivas a findViewById(),
     * que consumen muchos recursos. Almacena las referencias a las vistas
     * para un acceso rápido.
     */
    private static class ViewHolder {
        TextView nombre;
        TextView descripcion;
        ImageView imagen;
        CheckBox favorito;
    }
}
