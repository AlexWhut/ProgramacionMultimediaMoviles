package com.example.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActividad2 extends AppCompatActivity {

    private TextView textViewSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2_layout);

        // 1. Datos a mostrar: 10 países europeos y el nombre del alumno al final
        String[] paises = {
                "España", "Francia", "Alemania", "Italia", "Portugal",
                "Reino Unido", "Países Bajos", "Bélgica", "Suecia", "Noruega",
                "Alumno: Bryan Morantes"
        };

        List<String> listaPaises = new ArrayList<>(Arrays.asList(paises));

        // 2. Referencias a las vistas
        ListView listView = findViewById(R.id.listViewPaises);
        textViewSeleccionado = findViewById(R.id.textViewPaisSeleccionado);

        // 3. Adaptador utilizando el layout personalizado list_item_paises.xml
        // Se especifica el layout del item y el ID del TextView dentro de ese layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item_paises,
                R.id.textViewItemPais,
                listaPaises
        );

        // 4. Asignar el adaptador al ListView
        listView.setAdapter(adapter);

        // 5. Listener para los clics en los elementos de la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String paisElegido = parent.getItemAtPosition(position).toString();
                textViewSeleccionado.setText(paisElegido);
            }
        });
    }
}
