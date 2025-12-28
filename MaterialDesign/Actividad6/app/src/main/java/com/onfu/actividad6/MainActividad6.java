package com.onfu.actividad6;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

/**
 * Actividad principal que implementa la interfaz
 * para manejar los clics del Navigation Drawer.
 */
public class MainActividad6 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad6);
        // 1. Encontrar Vistas
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        // 2. Configurar la Toolbar y el "Toggle" (botón hamburguesa)
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // 3. Asignar el listener
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
        // --- NUEVO CÓDIGO PARA GESTIONAR EL BOTÓN "ATRÁS" ---
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                    setEnabled(true);
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String title = menuItem.getTitle().toString();
        menuItem.setChecked(true);
        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        int id = menuItem.getItemId();
        if (id == R.id.nav_biografia) {
            // Acción para Biografía
        } else if (id == R.id.nav_pintura) {
            // Acción para Pintura
        } else if (id == R.id.nav_salida) {
            finishAffinity();
        }
        drawerLayout.closeDrawers();
        return true;
    }
}
