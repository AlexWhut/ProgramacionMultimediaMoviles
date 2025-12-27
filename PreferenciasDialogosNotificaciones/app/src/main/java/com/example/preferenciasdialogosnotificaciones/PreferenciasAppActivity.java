package com.example.preferenciasdialogosnotificaciones;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class PreferenciasAppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usa un layout vacío con un FrameLayout como contenedor
        setContentView(R.layout.activity_preferencias);

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.pref_container, new PreferenciasFragment())
            .commit();
    }

    public static class PreferenciasFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferencias_app, rootKey);
        }
    }
}
