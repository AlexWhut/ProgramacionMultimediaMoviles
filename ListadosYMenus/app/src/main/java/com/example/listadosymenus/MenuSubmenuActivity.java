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

public class MenuSubmenuActivity extends AppCompatActivity {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar el nuevo layout
        setContentView(R.layout.main_menu_submenu_layout);

        // 1. Configurar la Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // 2. Enlazar el TextView
        textViewResultado = findViewById(R.id.textViewResultadoSubmenu);
    }

    /**
     * Inflamos el nuevo menú que contiene los submenús
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_with_submenus, menu); // Usamos el XML
        return true;
    }

    /**
     * Manejamos los clics.
     * Importante: El evento se recibe para los items FINALES (los del submenú),
     * no para los items que contienen el submenú (como "Archivo").
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Submenú de "Archivo"
        if (id == R.id.submenu_nuevo) {
            mostrarSeleccion("Archivo > Nuevo");
            return true;
        } else if (id == R.id.submenu_abrir) {
            mostrarSeleccion("Archivo > Abrir");
            return true;
        } else if (id == R.id.submenu_guardar) {
            mostrarSeleccion("Archivo > Guardar");
            return true;
        }
        // Submenú de "Editar"
        else if (id == R.id.submenu_copiar) {
            mostrarSeleccion("Editar > Copiar");
            return true;
        } else if (id == R.id.submenu_pegar) {
            mostrarSeleccion("Editar > Pegar");
            return true;
        }
        // Opción "Ayuda" (no tiene submenú)
        else if (id == R.id.menu_ayuda) {
            mostrarSeleccion("Ayuda");
            return true;
        }

        // Dejar que el sistema maneje otros clics
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método auxiliar para mostrar el resultado en el TextView y en un Toast.
     */
    private void mostrarSeleccion(String mensaje) {
        textViewResultado.setText("Has seleccionado: " + mensaje);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

