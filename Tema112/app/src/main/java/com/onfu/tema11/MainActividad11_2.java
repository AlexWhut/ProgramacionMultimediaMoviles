package com.onfu.tema11;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActividad11_2 extends AppCompatActivity {
    // URL de la imagen panorámica
    private static final String URL_IMAGEN = "https://upload.wikimedia.org/wikipedia/commons/e/ee/Yamanaka-lake-panoramic.png";
    private ImageView ivImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_2);
        ivImagen = findViewById(R.id.ivPanoramica);
        cargarImagenDesdeUrl();
    }

    /**
     * Personalización: Modo oscuro azul minimalista (ver layout_actividad11_2.xml y colors.xml)
     * Carga eficiente de imagen panorámica usando Glide.
     */
    private void cargarImagenDesdeUrl() {
        Glide.with(this)
                .load(URL_IMAGEN)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .fitCenter()
                .into(ivImagen);
        Toast.makeText(this, "Cargando panorámica...", Toast.LENGTH_SHORT).show();
    }
}
