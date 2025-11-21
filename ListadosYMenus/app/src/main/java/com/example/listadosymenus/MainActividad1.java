package com.example.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1_layout);

        // 1. Datos a mostrar
        String[] planetas = {
            "Mercurio", "Venus", "Tierra", "Marte", 
            "Júpiter", "Saturno", "Urano", "Neptuno"
        };
        
        List<String> listaPlanetas = new ArrayList<>(Arrays.asList(planetas));

        // 2. Adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaPlanetas
        );

        // 3. Instanciamos el ListView
        ListView listView = findViewById(R.id.listViewPlanetas);

        // Añadimos un Header y un Footer para ver el efecto de headerDividersEnabled y footerDividersEnabled
        TextView header = new TextView(this);
        header.setText("Cabecera de la Lista");
        header.setPadding(30, 30, 30, 30);
        header.setTextSize(18);
        
        TextView footer = new TextView(this);
        footer.setText("Pie de la Lista");
        footer.setPadding(30, 30, 30, 30);
        footer.setTextSize(18);

        // Importante: addHeaderView y addFooterView deben llamarse antes de setAdapter
        listView.addHeaderView(header);
        listView.addFooterView(footer);

        // 4. Asignamos el adaptador
        listView.setAdapter(adapter);

        // 6. Añadir Listener para reaccionar a los clics
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos el elemento pulsado
                // Nota: Al tener cabecera, la posición 0 es la cabecera.
                String seleccionado = String.valueOf(parent.getItemAtPosition(position));
                
                if (seleccionado != null && !seleccionado.equals("null")) {
                    Toast.makeText(getApplicationContext(), "Has seleccionado: " + seleccionado, Toast.LENGTH_SHORT).show();
                } else {
                    // Es la cabecera o el pie si no tienen datos asociados en el adapter
                     Toast.makeText(getApplicationContext(), "Has seleccionado cabecera o pie", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
