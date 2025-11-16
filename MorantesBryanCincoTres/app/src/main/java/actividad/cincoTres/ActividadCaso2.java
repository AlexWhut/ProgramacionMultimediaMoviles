package actividad.cincoTres;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadCaso2 extends AppCompatActivity {

    private TextView textoPrincipal;
    private Button boton1;
    private Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_5_3_caso2);

        // 1. Obtener referencias a todas las vistas
        textoPrincipal = findViewById(R.id.texto_principal);
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);

        // 2. Asignar un OnClickListener al Botón 1
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica a ejecutar cuando se pulsa el botón 1
                textoPrincipal.setText("BOTÓN 1 PULSADO");
                textoPrincipal.setTextColor(Color.RED);
            }
        });

        // 3. Asignar un OnClickListener al Botón 2
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica a ejecutar cuando se pulsa el botón 2
                textoPrincipal.setText("BOTÓN 2 PULSADO");
                textoPrincipal.setTextColor(Color.GREEN);
            }
        });
    }
}
