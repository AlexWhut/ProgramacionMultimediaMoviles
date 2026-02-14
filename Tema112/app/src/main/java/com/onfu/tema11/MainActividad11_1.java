package com.onfu.tema11;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_1 extends AppCompatActivity {
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_1);
        tvInfo = findViewById(R.id.tvInfoResolucion);
        mostrarResolucionPantalla();
    }

    /**
     * Muestra la resolución y densidad de pantalla usando DisplayMetrics.
     * Personalización: Modo oscuro azul minimalista (ver layout_actividad11_1.xml y colors.xml)
     */
    private void mostrarResolucionPantalla() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int anchoPixeles = metrics.widthPixels;
        int altoPixeles = metrics.heightPixels;
        float densidadLogica = metrics.density;
        int densidadDpi = metrics.densityDpi;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("RESOLUCIÓN DETECTADA:\n");
        mensaje.append("---------------------\n");
        mensaje.append("Ancho: ").append(anchoPixeles).append(" px\n");
        mensaje.append("Alto: ").append(altoPixeles).append(" px\n\n");
        mensaje.append("DATOS DE DENSIDAD:\n");
        mensaje.append("Densidad (DPI): ").append(densidadDpi).append(" dpi\n");
        mensaje.append("Factor de escala: ").append(densidadLogica).append("x");
        tvInfo.setText(mensaje.toString());
    }
}
