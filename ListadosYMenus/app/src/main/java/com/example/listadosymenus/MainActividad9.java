package com.example.listadosymenus;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActividad9 extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad9_layout);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        
        // Título en la barra superior
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Proyecto 1");
        }

        textViewResultado = findViewById(R.id.textViewResultado);
    }

    /**
     * Creación del menú mediante código Java
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // 1. Menú "Día Semana" (En la mitad / primera posición de acción)
        SubMenu subMenuDias = menu.addSubMenu(Menu.NONE, Menu.NONE, Menu.NONE, "Día Semana");
        // Para que se vea en la barra si hay sitio (aunque los submenús a veces colapsan en icono)
        subMenuDias.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (int i = 0; i < dias.length; i++) {
            MenuItem item = subMenuDias.add(Menu.NONE, 100 + i, Menu.NONE, dias[i]);
            item.setOnMenuItemClickListener(this);
        }

        // 2. Menú "Mes del Año" (A la derecha)
        SubMenu subMenuMeses = menu.addSubMenu(Menu.NONE, Menu.NONE, Menu.NONE, "Mes del Año");
        subMenuMeses.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (int i = 0; i < meses.length; i++) {
            MenuItem item = subMenuMeses.add(Menu.NONE, 200 + i, Menu.NONE, meses[i]);
            item.setOnMenuItemClickListener(this);
        }

        // Añadir nombre del alumno
        menu.add(Menu.NONE, Menu.NONE, Menu.NONE, "Alumno: Bryan Morantes")
            .setEnabled(false); 

        return true;
    }

    /**
     * Implementación de la interfaz MenuItem.OnMenuItemClickListener
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String titulo = item.getTitle().toString();
        textViewResultado.setText("Has seleccionado: " + titulo);
        return true;
    }
}
