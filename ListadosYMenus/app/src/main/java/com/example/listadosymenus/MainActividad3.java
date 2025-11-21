package com.example.listadosymenus;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActividad3 extends AppCompatActivity {

    private TextView textViewSeleccionado;
    private int selectedPosition = -1; // Almacena la posición seleccionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad3_layout);

        // 1. Datos a mostrar: 10 países europeos y el nombre del alumno al final
        String[] paises = {
                "España", "Francia", "Alemania", "Italia", "Portugal",
                "Reino Unido", "Países Bajos", "Bélgica", "Suecia", "Noruega",
                "Alumno: Bryan Morantes"
        };

        List<String> listaPaises = new ArrayList<>(Arrays.asList(paises));

        // 2. Referencias a las vistas
        GridView gridView = findViewById(R.id.gridViewPaises);
        textViewSeleccionado = findViewById(R.id.textViewPaisSeleccionado);

        // 3. Adaptador personalizado para cambiar el color del ítem seleccionado
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_paises,
                R.id.textViewItemPais,
                listaPaises
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                
                // Como el layout list_item_paises es un TextView, el view devuelto es ese TextView
                TextView textView = (TextView) view;

                if (position == selectedPosition) {
                    textView.setBackgroundColor(Color.RED);
                    textView.setTextColor(Color.WHITE);
                } else {
                    // Configuración por defecto (transparente y negro)
                    textView.setBackgroundColor(Color.TRANSPARENT);
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // 4. Asignar el adaptador al GridView
        gridView.setAdapter(adapter);

        // 5. Listener para los clics en los elementos del grid
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                adapter.notifyDataSetChanged(); // Actualizar la vista para reflejar el cambio de color
                
                String paisElegido = parent.getItemAtPosition(position).toString();
                textViewSeleccionado.setText(paisElegido);
            }
        });
    }
}
