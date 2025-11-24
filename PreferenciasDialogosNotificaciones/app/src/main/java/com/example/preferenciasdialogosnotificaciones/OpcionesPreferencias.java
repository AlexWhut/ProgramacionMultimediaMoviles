package com.example.preferenciasdialogosnotificaciones;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Esta clase extiende de PreferenceActivity (clase clásica).
 * [cite: 76]
 *
 * @SuppressWarnings("deprecation") es necesario porque PreferenceActivity
 * está obsoleta (deprecated), como se menciona en el documento.
 */
@SuppressWarnings("deprecation")
public class OpcionesPreferencias extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // [cite: 80] Este método carga el archivo XML de preferencias
        // y construye la interfaz de usuario automáticamente.
        addPreferencesFromResource(R.xml.opciones);
    }
}

