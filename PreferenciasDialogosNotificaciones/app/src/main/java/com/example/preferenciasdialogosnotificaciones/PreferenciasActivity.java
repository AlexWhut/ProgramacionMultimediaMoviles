package com.example.preferenciasdialogosnotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View; // <-- Importamos la interfaz
import android.widget.Button;
import android.widget.TextView;

/**
 * Ahora, la clase implementa la interfaz View.OnClickListener.
 * Esto nos obliga a añadir el método onClick(View v).
 */
public class PreferenciasActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAbrir, btnMostrar;
    private TextView tvResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias_layout);

        // Enlazar los componentes del layout (sin cambios)
        btnAbrir = findViewById(R.id.btnAbrirPreferencias);
        btnMostrar = findViewById(R.id.btnMostrarPreferencias);
        tvResultados = findViewById(R.id.textViewResultados);

        // --- ¡CAMBIO CLAVE! ---
        // En lugar de crear un listener aquí, le decimos a cada botón
        // que ESTA CLASE (this) será su escuchador de clics.
        btnAbrir.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
    }

    /**
     * --- MÉTODO DE LA INTERFAZ ---
     * Este es el método que nos obliga a implementar View.OnClickListener.
     * Se llamará automáticamente cuando CUALQUIER vista que tenga
     * registrado "this" como listener sea pulsada.
     *
     * @param v La vista que ha sido pulsada (el botón).
     */
    @Override
    public void onClick(View v) {
        // Usamos un switch (o if/else) para ver qué botón ha disparado el evento,
        // comparando el ID de la vista (v.getId()) con los IDs de nuestros botones.

        int id = v.getId();
        if (id == R.id.btnAbrirPreferencias) {
            // --- Lógica para ABRIR preferencias ---
            Intent intent = new Intent(PreferenciasActivity.this, OpcionesPreferencias.class);
            startActivity(intent);
        } else if (id == R.id.btnMostrarPreferencias) {
            // --- Lógica para MOSTRAR preferencias ---
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(PreferenciasActivity.this);

            boolean opcion1 = pref.getBoolean("clave1", false);
            String opcion2 = pref.getString("clave2", "No asignada");
            String opcion3 = pref.getString("clave3", "No asignada");

            // Mostramos los resultados en el TextView
            String resultados = "CheckBox: " + opcion1 + "\n" +
                    "Texto: " + opcion2 + "\n" +
                    "Lista: " + opcion3;

            tvResultados.setText(resultados);
        }
    }
}

