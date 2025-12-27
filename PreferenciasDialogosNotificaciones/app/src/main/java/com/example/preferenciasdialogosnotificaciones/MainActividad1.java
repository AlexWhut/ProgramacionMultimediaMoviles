package com.example.preferenciasdialogosnotificaciones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad1);

        Button btnDefinir = findViewById(R.id.btnDefinirPreferencias);
        Button btnObtener = findViewById(R.id.btnObtenerPreferencias);

        // Botón 1: Abre la actividad de preferencias
        btnDefinir.setOnClickListener(v -> {
            Intent intent = new Intent(MainActividad1.this, PreferenciasAppActivity.class);
            startActivity(intent);
        });

        // Botón 2: Lee las preferencias y las muestra en el Log
        btnObtener.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActividad1.this);

            boolean op1 = prefs.getBoolean("opcion1", false);
            String sistemaOperativo = prefs.getString("sistema_operativo", "android"); // valor por defecto
            boolean op3 = prefs.getBoolean("opcion3", false);
            String version = prefs.getString("version_android", "No definida");

            // Mostrar el nombre legible del sistema operativo
            String sistemaOperativoTexto;
            switch (sistemaOperativo) {
                case "ios":
                    sistemaOperativoTexto = "iOS";
                    break;
                case "windows_phone":
                    sistemaOperativoTexto = "Windows Phone";
                    break;
                default:
                    sistemaOperativoTexto = "Android";
            }

            Log.d("MIS_PREFERENCIAS", "---- Valores ----");
            Log.d("MIS_PREFERENCIAS", "Opción 1: " + op1);
            Log.d("MIS_PREFERENCIAS", "Sistema Operativo: " + sistemaOperativoTexto);
            Log.d("MIS_PREFERENCIAS", "Opción 3: " + op3);
            Log.d("MIS_PREFERENCIAS", "Versión: " + version);

            Toast.makeText(MainActividad1.this, "Revisa el Logcat", Toast.LENGTH_SHORT).show();
        });
    }
}
