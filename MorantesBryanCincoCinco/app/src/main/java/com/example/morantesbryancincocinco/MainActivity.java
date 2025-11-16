package com.example.morantesbryancincocinco;

// No necesitas las importaciones estáticas, las eliminamos para mayor claridad.
// import static com.example.morantesbryancincocuatro.R.id.radio_domingo;
// ...

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
// Las importaciones duplicadas y las de EdgeToEdge que no se usan se han limpiado.

public class MainActivity extends AppCompatActivity {

    private TextView textoResultado;
    private RadioGroup radioGroupDias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asegúrate de que el nombre del layout coincide con tu archivo XML.
        // Si tu archivo se llama actividad_5_5.xml, aquí debe ir R.layout.actividad_5_5
        setContentView(R.layout.activity_main);

        // 1. Obtener las referencias a las vistas
        textoResultado = findViewById(R.id.texto_resultado);
        radioGroupDias = findViewById(R.id.radio_group_dias);

        // 2. Configurar el "escuchador" para el RadioGroup
        radioGroupDias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String diaSeleccionado = "";

                // --- INICIO DE LA CORRECCIÓN ---
                // 3. Usar 'if-else if' en lugar de 'switch'
                if (checkedId == R.id.radio_lunes) {
                    diaSeleccionado = "LUNES";
                } else if (checkedId == R.id.radio_martes) {
                    diaSeleccionado = "MARTES";
                } else if (checkedId == R.id.radio_miercoles) {
                    diaSeleccionado = "MIÉRCOLES";
                } else if (checkedId == R.id.radio_jueves) {
                    diaSeleccionado = "JUEVES";
                } else if (checkedId == R.id.radio_viernes) {
                    diaSeleccionado = "VIERNES";
                } else if (checkedId == R.id.radio_sabado) {
                    diaSeleccionado = "SÁBADO";
                } else if (checkedId == R.id.radio_domingo) {
                    diaSeleccionado = "DOMINGO";
                }

                // 4. Actualizar el TextView con el resultado
                textoResultado.setText(diaSeleccionado);
            }
        });
    }
}
