package com.example.listadosymenus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadosymenus.adapters.PlanetaAdapter;
import com.example.listadosymenus.pojos.Planeta;

import java.util.ArrayList;

public class MainActividad5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad5_layout);

        // 1. Datos de Planetas con sus imágenes correspondientes en R.drawable
        ArrayList<Planeta> planetas = new ArrayList<>();

        planetas.add(new Planeta("Mercurio", "El planeta más pequeño", R.drawable.mercurio, false, "https://es.wikipedia.org/wiki/Mercurio_(planeta)"));
        planetas.add(new Planeta("Venus", "El planeta más caliente", R.drawable.venus, false, "https://es.wikipedia.org/wiki/Venus_(planeta)"));
        planetas.add(new Planeta("Tierra", "Nuestro hogar", R.drawable.tierra, true, "https://es.wikipedia.org/wiki/Tierra"));
        planetas.add(new Planeta("Marte", "El planeta rojo", R.drawable.marte, false, "https://es.wikipedia.org/wiki/Marte_(planeta)"));
        planetas.add(new Planeta("Júpiter", "El gigante gaseoso", R.drawable.jupiter, false, "https://es.wikipedia.org/wiki/J%C3%BApiter_(planeta)"));
        planetas.add(new Planeta("Saturno", "El señor de los anillos", R.drawable.saturno, false, "https://es.wikipedia.org/wiki/Saturno_(planeta)"));
        planetas.add(new Planeta("Urano", "El gigante de hielo", R.drawable.urano, false, "https://es.wikipedia.org/wiki/Urano_(planeta)"));
        planetas.add(new Planeta("Neptuno", "El planeta azul", R.drawable.neptuno, false, "https://es.wikipedia.org/wiki/Neptuno_(planeta)"));
        
        // Añadimos al alumno al final
        // Usamos R.mipmap.ic_launcher como imagen genérica para el alumno
        planetas.add(new Planeta("Alumno: Bryan Morantes", "Desarrollador Android", R.mipmap.ic_launcher, true, "https://github.com/alexwhut"));

        // 2. Adaptador
        PlanetaAdapter adapter = new PlanetaAdapter(this, planetas);

        // 3. ListView
        ListView listView = findViewById(R.id.listViewComplejo);
        listView.setAdapter(adapter);

        // 4. Listener para abrir la URL al hacer clic en el elemento
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Planeta planetaSeleccionado = (Planeta) parent.getItemAtPosition(position);
                String url = planetaSeleccionado.getUrlInfo();

                if (url != null && !url.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActividad5.this, "No hay información disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
