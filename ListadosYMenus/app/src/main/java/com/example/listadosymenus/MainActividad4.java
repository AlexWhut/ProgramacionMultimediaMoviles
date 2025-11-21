package com.example.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActividad4 extends AppCompatActivity {

    private TextView textViewSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad4_layout);

        // 1. Datos a mostrar: 10 países europeos y el nombre del alumno al final
        String[] paises = {
                "España", "Francia", "Alemania", "Italia", "Portugal",
                "Reino Unido", "Países Bajos", "Bélgica", "Suecia", "Noruega",
                "Alumno: Bryan Morantes"
        };
        List<String> listaPaises = new ArrayList<>(Arrays.asList(paises));

        // 2. Referencias a las vistas
        Spinner spinner = findViewById(R.id.spinnerPaises);
        textViewSeleccionado = findViewById(R.id.textViewPaisSeleccionado);

        // 3. Adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                listaPaises
        );
        // Layout para cuando se despliega la lista
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 4. Asignar adaptador
        spinner.setAdapter(adapter);

        // 5. Listener para la selección
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = parent.getItemAtPosition(position).toString();
                textViewSeleccionado.setText(seleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se requiere acción
            }
        });
    }
}
