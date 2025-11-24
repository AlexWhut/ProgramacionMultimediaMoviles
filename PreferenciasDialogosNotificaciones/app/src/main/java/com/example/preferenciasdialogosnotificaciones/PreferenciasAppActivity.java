package com.example.preferenciasdialogosnotificaciones;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenciasAppActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AHORA SÍ funcionará porque creamos opciones1.xml en el paso 1
        addPreferencesFromResource(R.xml.opciones1);
    }
}
