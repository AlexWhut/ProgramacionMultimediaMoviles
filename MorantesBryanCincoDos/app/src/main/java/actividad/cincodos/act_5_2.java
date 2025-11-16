package actividad.cincodos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class act_5_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar el layout
        setContentView(R.layout.act_5_2);

        // 1. Obtener referencias a las vistas
        final TextView textoAnimar = findViewById(R.id.text_to_animate);
        final Button botonAnimar = findViewById(R.id.button_start_animation);

        // 2. Cargar la animación
        final Animation animacion = AnimationUtils.loadAnimation(this, R.anim.varios1);

        // 3. Listener del botón
        botonAnimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoAnimar.startAnimation(animacion);
            }
        });
    }
}
