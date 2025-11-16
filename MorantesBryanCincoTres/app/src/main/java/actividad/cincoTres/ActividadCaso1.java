package actividad.cincoTres;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadCaso1 extends AppCompatActivity {

    private TextView textoPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_5_3_caso1);


        setTitle("Actividad 5.3");
        textoPrincipal = findViewById(R.id.texto_principal);
    }

    public void cuandoPulsaBoton1(View view) {
        textoPrincipal.setText("BOTÓN 1 PULSADO");
        textoPrincipal.setTextColor(Color.RED);
    }

    public void cuandoPulsaBoton2(View view) {
        textoPrincipal.setText("BOTÓN 2 PULSADO");
        textoPrincipal.setTextColor(Color.GREEN);
    }
}
