package com.example.listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.listadosymenus.adapters.VersionAdapter;
import com.example.listadosymenus.pojos.Encapsulador;
import java.util.ArrayList;
import java.util.List;

public class MainActividad6 extends AppCompatActivity {

    private TextView textViewTitulo;
    private TextView textViewDescripcionCorta;
    private TextView textViewDescripcionLarga;
    private List<Encapsulador> datos;
    private VersionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad6_layout);

        // 1. Inicializar Vistas
        ListView listView = findViewById(R.id.listViewVersiones);
        textViewTitulo = findViewById(R.id.textViewTituloDetalle);
        textViewDescripcionCorta = findViewById(R.id.textViewDescripcionDetalle);
        textViewDescripcionLarga = findViewById(R.id.textViewDescripcionLarga);

        // 2. Datos
        datos = new ArrayList<>();
        datos.add(new Encapsulador(R.drawable.donuts, "DONUTS", "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, basado en el núcleo Linux 2.6.29. En la actualización se incluyen numerosas características nuevas.", true));
        datos.add(new Encapsulador(R.drawable.froyo, "FROYO", "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, basado en el núcleo Linux 2.6.32.", false));
        datos.add(new Encapsulador(R.drawable.gingerbread, "GINGERBREAD", "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread (Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35.", false));
        datos.add(new Encapsulador(R.drawable.honeycomb, "HONEYCOMB", "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb (Panal de Miel). Fue la primera actualización exclusiva para TV y tableta.", false));
        datos.add(new Encapsulador(R.drawable.icecream, "ICE CREAM", "El SDK para Android 4.0.0 Ice Cream Sandwich (Sándwich de Helado), basado en el núcleo de Linux 3.0.1, fue lanzado públicamente el 12 de octubre de 2011.", false));
        datos.add(new Encapsulador(R.drawable.jellybean, "JELLY BEAN", "Google anunció Android 4.1 Jelly Bean (Gomita Confitada o Gominola) en la conferencia del 30 de junio de 2012. Basado en el núcleo de linux 3.0.31.", false));
        datos.add(new Encapsulador(R.drawable.kitkat, "KITKAT", "Su nombre se debe a la chocolatina KitKat, de la empresa internacional Nestlé. Posibilidad de impresión mediante WIFI. WebViews basadas en el motor de Chromium.", false));
        datos.add(new Encapsulador(R.drawable.lollipop, "LOLLIPOP", "Incluye Material Design, un diseño intrépido, colorido, y sensible interfaz de usuario para las experiencias coherentes e intuitivos en todos los dispositivos.", false));
        
        // Añadir al alumno
        datos.add(new Encapsulador(R.mipmap.ic_launcher, "Alumno: Bryan Morantes", "Desarrollador de la aplicación", false));

        // 3. Adaptador
        adapter = new VersionAdapter(this, datos);
        listView.setAdapter(adapter);

        // 4. Cargar estado inicial (primera posición seleccionada por defecto)
        actualizarPaneles(0);

        // 5. Listener de la Lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Desmarcar todos
                for (Encapsulador en : datos) {
                    en.setSeleccionado(false);
                }
                
                // Marcar el seleccionado
                Encapsulador seleccionado = datos.get(position);
                seleccionado.setSeleccionado(true);
                
                // Notificar al adaptador para refrescar los RadioButtons
                adapter.notifyDataSetChanged();
                
                // Actualizar paneles de detalle
                actualizarPaneles(position);
            }
        });
    }

    private void actualizarPaneles(int position) {
        if (position >= 0 && position < datos.size()) {
            Encapsulador item = datos.get(position);
            textViewTitulo.setText(item.getTitulo());
            
            // En este ejemplo, usamos la misma descripción para ambos campos, 
            // pero podrías tener 'descripcionCorta' y 'descripcionLarga' en tu POJO si quisieras.
            // Aquí simularemos una descripción corta cogiendo el principio del texto.
            String desc = item.getDescripcion();
            String corta = desc.length() > 50 ? desc.substring(0, 50) + "..." : desc;
            
            textViewDescripcionCorta.setText(corta);
            textViewDescripcionLarga.setText(desc);
        }
    }
}
