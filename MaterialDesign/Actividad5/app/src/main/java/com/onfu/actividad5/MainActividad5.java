package com.onfu.actividad5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Actividad principal personalizada para mostrar una lista de tarjetas con colores de fondo distintos.
 * Personalización: Cada tarjeta tiene un color de fondo diferente (definido en Encapsulador) y el título principal está personalizado en el layout (layout_actividad5.xml).
 */
public class MainActividad5 extends AppCompatActivity {
    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;
    private List<Encapsulador> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad5);

        datos = new ArrayList<>();
        // Personalización: cada tarjeta tiene un color de fondo diferente (ver campo colorFondo en Encapsulador)
        datos.add(new Encapsulador(R.drawable.donuts, "DONUTS", "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut...", Color.parseColor("#FFCDD2")));
        datos.add(new Encapsulador(R.drawable.froyo, "FROYO", "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo...", Color.parseColor("#F8BBD0")));
        datos.add(new Encapsulador(R.drawable.gingerbread, "GINGERBREAD", "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread...", Color.parseColor("#E1BEE7")));
        datos.add(new Encapsulador(R.drawable.honeycomb, "HONEYCOMB", "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb...", Color.parseColor("#D1C4E9")));
        datos.add(new Encapsulador(R.drawable.icecream, "ICE CREAM", "El SDK para Android 4.0.0 Ice Cream Sandwich...", Color.parseColor("#C5CAE9")));
        datos.add(new Encapsulador(R.drawable.jellybean, "JELLY BEAN", "Google anunció Android 4.1 Jelly Bean...", Color.parseColor("#B3E5FC")));
        datos.add(new Encapsulador(R.drawable.kitkat, "KITKAT", "Su nombre se debe a la chocolatina KitKat...", Color.parseColor("#C8E6C9")));
        datos.add(new Encapsulador(R.drawable.lollipop, "LOLLIPOP", "Incluye Material Design, un diseño intrépido...", Color.parseColor("#FFF9C4")));

        reciclador = findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        gestor = new LinearLayoutManager(this);
        reciclador.setLayoutManager(gestor);
        adaptador = new Adaptador(datos);
        reciclador.setAdapter(adaptador);

        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                if (hijo != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(hijo);
                    Toast.makeText(getApplicationContext(), datos.get(position).get_textotitulo(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {}
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        });
    }
}
