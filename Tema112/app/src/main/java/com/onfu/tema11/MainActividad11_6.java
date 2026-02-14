package com.onfu.tema11;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.onfu.tema11.util.VistaTactil;

public class MainActividad11_6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_6);
        VistaTactil miVista = new VistaTactil(this);
        FrameLayout contenedor = findViewById(R.id.contenedor_principal);
        contenedor.addView(miVista);
    }
}
