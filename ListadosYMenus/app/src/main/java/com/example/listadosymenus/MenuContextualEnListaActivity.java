package com.example.listadosymenus;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listadosymenus.adapters.OpcionListaAdapter;
import com.example.listadosymenus.pojos.OpcionLista;

import java.util.ArrayList;

public class MenuContextualEnListaActivity extends AppCompatActivity {

    private ListView listViewOpciones;
    private ArrayList<OpcionLista> opciones;
    private OpcionListaAdapter adapter;

    // Esta variable guardará la posición del elemento que disparó el menú contextual
    private int posicionContextualSeleccionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_contextual_lista_layout);

        listViewOpciones = findViewById(R.id.listViewOpciones);

        // 1. Cargar datos
        opciones = new ArrayList<>();
        opciones.add(new OpcionLista("OPCIÓN DE MENÚ A"));
        opciones.add(new OpcionLista("OPCIÓN DE MENÚ B"));
        opciones.add(new OpcionLista("OPCIÓN DE MENÚ C"));
        opciones.add(new OpcionLista("OPCIÓN DE MENÚ D"));
        opciones.add(new OpcionLista("OPCIÓN DE MENÚ E"));

        // 2. Configurar adaptador
        adapter = new OpcionListaAdapter(this, opciones);
        listViewOpciones.setAdapter(adapter);

        // 3. ¡LA CLAVE! Registrar el ListView para el menú contextual.
        // Esto significa que cualquier elemento dentro del ListView puede disparar el menú.
        registerForContextMenu(listViewOpciones);

        // 4. (Opcional) Listener para clicks normales en la lista
        listViewOpciones.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Click corto en: " + opciones.get(position).getTexto(), Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Este método se llama cuando el usuario hace una pulsación larga
     * sobre un elemento del ListView.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Obtenemos información del elemento que se pulsó
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        posicionContextualSeleccionada = info.position; // Guardamos la posición

        // Inflamos el menú contextual que creamos en XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);

        // Opcional: Título del menú basado en el elemento seleccionado
        menu.setHeaderTitle(opciones.get(posicionContextualSeleccionada).getTexto());
    }

    /**
     * Este método se llama cuando el usuario selecciona una opción
     * del menú contextual.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // Asegurarse de que tenemos una posición válida
        if (posicionContextualSeleccionada == -1) {
            return super.onContextItemSelected(item);
        }

        // Obtenemos el objeto OpcionLista que queremos modificar
        OpcionLista opcionModificar = opciones.get(posicionContextualSeleccionada);
        int id = item.getItemId();

        // --- Lógica para modificar la opción seleccionada ---
        if (id == R.id.fuente_normal) {
            opcionModificar.setEstiloFuente(Typeface.NORMAL);
            mostrarToast("Fuente Normal para " + opcionModificar.getTexto());
        } else if (id == R.id.fuente_negrita) {
            opcionModificar.setEstiloFuente(Typeface.BOLD);
            mostrarToast("Fuente Negrita para " + opcionModificar.getTexto());
        } else if (id == R.id.fuente_cursiva) {
            opcionModificar.setEstiloFuente(Typeface.ITALIC);
            mostrarToast("Fuente Cursiva para " + opcionModificar.getTexto());
        } else if (id == R.id.tamano_pequeno) {
            opcionModificar.setTamanoFuente(14);
            mostrarToast("Tamaño Pequeño para " + opcionModificar.getTexto());
        } else if (id == R.id.tamano_normal) {
            opcionModificar.setTamanoFuente(18);
            mostrarToast("Tamaño Normal para " + opcionModificar.getTexto());
        } else if (id == R.id.tamano_grande) {
            opcionModificar.setTamanoFuente(24);
            mostrarToast("Tamaño Grande para " + opcionModificar.getTexto());
        } else if (id == R.id.color_rojo) {
            opcionModificar.setColorTexto(Color.RED);
            mostrarToast("Color Rojo para " + opcionModificar.getTexto());
        } else if (id == R.id.color_azul) {
            opcionModificar.setColorTexto(Color.BLUE);
            mostrarToast("Color Azul para " + opcionModificar.getTexto());
        } else if (id == R.id.color_negro) {
            opcionModificar.setColorTexto(Color.BLACK);
            mostrarToast("Color Negro para " + opcionModificar.getTexto());
        } else {
            return super.onContextItemSelected(item); // Si no es una opción de nuestro menú, que lo maneje el sistema
        }

        // ¡IMPORTANTE! Notificar al adaptador que los datos han cambiado
        // para que la lista se redibuje y muestre los nuevos estilos.
        adapter.notifyDataSetChanged();

        // Resetear la posición contextual
        posicionContextualSeleccionada = -1;

        return true;
    }

    // Método auxiliar para mostrar un Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

