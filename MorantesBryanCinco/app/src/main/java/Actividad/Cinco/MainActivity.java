package Actividad.Cinco;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Color;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carga el layout con el texto rojo inicial
        setContentView(R.layout.act_5_1_4);
        /*
        // --- INICIO DE LA MODIFICACIÓN PARA LA ACTIVIDAD 5.1.3 ---

        // 1. Obtener la referencia al TextView que ya tiene el texto rojo
        TextView textViewInferior = findViewById(R.id.hello_text);

        // 2. Preparar el nuevo texto que vamos a añadir
        String textoAzul = "Texto añadido con Append desde Java en azul";

        // 3. Crear un SpannableString para poder aplicarle un color
        // Un SpannableString es un texto que puede tener "spans" (estilos) aplicados a porciones de él.
        SpannableString spannable = new SpannableString(textoAzul);

        // 4. Crear el "span" de color azul y aplicarlo al SpannableString
        // ForegroundColorSpan es el objeto que define el color del texto.
        // Se aplica desde el inicio (índice 0) hasta el final (spannable.length()).
        spannable.setSpan(
                new ForegroundColorSpan(Color.BLUE), // El estilo a aplicar (color azul)
                0,                                   // Inicio de la aplicación del estilo
                spannable.length(),                  // Fin de la aplicación del estilo
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE   // Modo de aplicación
        );

        // 5. Usar el método append() para añadir el texto con estilo al final del TextView
        textViewInferior.append(spannable);
        */
    }
}