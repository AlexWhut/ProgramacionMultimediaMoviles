package com.example.listadosymenus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuContextualActivity extends AppCompatActivity {

    private TextView textView;
    private boolean esTamanoGrande = false; // Para alternar el tamaño

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_contextual_layout);

        // 1. Enlazar el TextView
        textView = findViewById(R.id.textViewContextual);

        // 2. ¡LA CLAVE! Registrar el TextView para el menú contextual.
        // Esto le dice a Android que "escuche" las pulsaciones largas en esta vista.
        registerForContextMenu(textView);
    }

    /**
     * Este método se llama cuando el usuario hace una pulsación larga
     * sobre la vista que hemos registrado (nuestro TextView).
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Inflamos el menú que creamos en el archivo XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        // Opcional: Añadimos un título al menú
        menu.setHeaderTitle("Modificar Texto");
    }

    /**
     * Este método se llama cuando el usuario selecciona una opción
     * del menú contextual que acaba de aparecer.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Usamos un if/else en lugar de switch para los IDs de Android
        if (id == R.id.fuente_normal) {
            textView.setTypeface(Typeface.DEFAULT);
            mostrarToast("Fuente: Normal");
            return true;
        } else if (id == R.id.fuente_negrita) {
            textView.setTypeface(null, Typeface.BOLD);
            mostrarToast("Fuente: Negrita");
            return true;
        } else if (id == R.id.fuente_cursiva) {
            textView.setTypeface(null, Typeface.ITALIC);
            mostrarToast("Fuente: Cursiva");
            return true;
        } else if (id == R.id.cambiar_tamano) {
            if (esTamanoGrande) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22); // Volver a 22sp
                mostrarToast("Tamaño: Normal");
            } else {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34); // Aumentar a 34sp
                mostrarToast("Tamaño: Grande");
            }
            esTamanoGrande = !esTamanoGrande; // Invertimos el estado
            return true;
        } else if (id == R.id.color_rojo) {
            textView.setTextColor(Color.RED);
            mostrarToast("Color: Rojo");
            return true;
        } else if (id == R.id.color_azul) {
            textView.setTextColor(Color.BLUE);
            mostrarToast("Color: Azul");
            return true;
        } else if (id == R.id.color_negro) {
            textView.setTextColor(Color.BLACK);
            mostrarToast("Color: Negro");
            return true;
        }

        // Si no hemos manejado la opción, dejamos que el sistema lo haga
        return super.onContextItemSelected(item);
    }

    // Método auxiliar para mostrar un Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

