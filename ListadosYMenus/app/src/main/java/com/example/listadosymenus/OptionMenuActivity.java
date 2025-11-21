package com.example.listadosymenus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * No nos olvidemos de definir la Activity en el Manifest.xml
 */
public class OptionMenuActivity extends AppCompatActivity {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_menu_layout);
        // 2. CONFIGURA LA TOOLBAR
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar); // <-- Esta línea le dice a la Activity que use nuestra Toolbar

        textViewResultado = findViewById(R.id.textViewResultado);
    }

    /**
     * Este método se llama una vez para crear el menú de opciones.
     * Aquí es donde "inflamos" nuestro menú desde el archivo XML.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu); // Inflamos el menú que creamos en XML
        return true; // Devolvemos true para que el menú se muestre
    }

    /**
     * Este método se llama cada vez que el usuario selecciona una opción del menú.
     * Aquí manejamos la acción a realizar para cada opción.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Usamos un switch para identificar qué opción se ha pulsado
        // mediante el ID que le asignamos en el XML.
        int id = item.getItemId();

        if (id == R.id.opcion_a) {
            // Acción para la opción A
            textViewResultado.setText("Has seleccionado: Opción A");
            Toast.makeText(this, "Pulsada Opción A", Toast.LENGTH_SHORT).show();
            return true; // Devolvemos true para indicar que hemos manejado el evento
        } else if (id == R.id.opcion_b) {
            // Acción para la opción B
            textViewResultado.setText("Has seleccionado: Opción B");
            Toast.makeText(this, "Pulsada Opción B", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.opcion_c) {
            // Acción para la opción C
            textViewResultado.setText("Has seleccionado: Opción C");
            Toast.makeText(this, "Pulsada Opción C", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Si no hemos manejado el evento, llamamos al método de la superclase
        return super.onOptionsItemSelected(item);
    }
}

