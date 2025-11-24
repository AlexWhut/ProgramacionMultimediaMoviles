package com.example.preferenciasdialogosnotificaciones;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

// Esta es la actividad que se abrirá desde la notificación
public class Segunda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_segunda);

        // Habilitamos la flecha de "Atrás" en la ActionBar
        // para probar el 'parentActivityName'
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}

