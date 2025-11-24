package com.example.preferenciasdialogosnotificaciones;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Esta clase hereda de PreferenceActivity.
 * Como esta clase está obsoleta (deprecated), añadimos la anotación
 * para suprimir las advertencias, tal como indica el documento de teoría.
 */
@SuppressWarnings("deprecation")
public class PreferenciasAppActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Este es el único comando necesario.
        // Carga el layout de preferencias desde res/xml/opciones.xml
        // y construye la pantalla de ajustes automáticamente.
        addPreferencesFromResource(R.xml.opciones1);
    }
}


