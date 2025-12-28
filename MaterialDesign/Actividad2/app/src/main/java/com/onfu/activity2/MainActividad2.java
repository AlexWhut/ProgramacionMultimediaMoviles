package com.onfu.activity2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActividad2 extends AppCompatActivity {
    // Declaramos las vistas y el adaptador
    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    // Personalización: Cambié los títulos e iconos de las pestañas
    private final String[] titulosTabs = new String[]{"PRIMAVERA", "FAUNA", "PAISAJE"};
    private final int[] iconosTabs = new int[]{
            R.drawable.ic_tab_flower,
            R.drawable.ic_tab_animal,
            R.drawable.ic_tab_landscape
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Cargar el layout
        setContentView(R.layout.layout_actividad2);
        // 2. Encontrar las vistas por su ID
        toolbar = findViewById(R.id.mainToolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        // 3. Configurar la Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_flower);
        // 4. Inicializar y configurar el Adaptador
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        // 5. Conectar el TabLayout con el ViewPager2 usando TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Personalización: Título e icono de cada pestaña
                    tab.setText(titulosTabs[position]);
                    tab.setIcon(iconosTabs[position]);
                }
        ).attach();
    }
}
