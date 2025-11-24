package com.example.preferenciasdialogosnotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

/**
 * La Actividad implementa las interfaces de los listeners que necesitamos.
 * Esto nos obliga a sobrescribir sus métodos (onClick, onCheckedChanged).
 */
public class ToastActivity extends AppCompatActivity implements
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener {

    // Declaramos los controles que vamos a usar
    private Button btnToastSimple;
    private Switch switchToastLargo;
    private RadioGroup radioGroupToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_layout);

        // 1. Enlazar las vistas del layout
        btnToastSimple = findViewById(R.id.btnToastSimple);
        switchToastLargo = findViewById(R.id.switchToastLargo);
        radioGroupToast = findViewById(R.id.radioGroupToast);

        // 2. Registrar los listeners
        // Le decimos a cada control que ESTA CLASE (this)
        // se encargará de gestionar sus eventos.
        btnToastSimple.setOnClickListener(this);
        switchToastLargo.setOnCheckedChangeListener(this);
        radioGroupToast.setOnCheckedChangeListener(this);
    }

    /**
     * MÉTODO ESCUCHADOR para View.OnClickListener (del Botón)
     * Este método se dispara cuando se pulsa btnToastSimple.
     */
    @Override
    public void onClick(View v) {
        // Comprobamos que el clic viene del botón que esperamos
        if (v.getId() == R.id.btnToastSimple) {
            // Creamos y mostramos el Toast
            // Parámetros: Contexto, Texto a mostrar, Duración
            Toast.makeText(this, "¡Esto es un Toast corto!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * MÉTODO ESCUCHADOR para CompoundButton.OnCheckedChangeListener (del Switch)
     * Este método se dispara cuando el estado del Switch cambia.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // Comprobamos que el evento viene del Switch que esperamos
        if (buttonView.getId() == R.id.switchToastLargo) {
            String mensaje;
            if (isChecked) {
                mensaje = "Toast Largo Activado";
            } else {
                mensaje = "Toast Largo Desactivado";
            }

            // Usamos Toast.LENGTH_LONG para que dure más tiempo
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * MÉTODO ESCUCHADOR para RadioGroup.OnCheckedChangeListener (del RadioGroup)
     * Este método se dispara cuando se selecciona un RadioButton diferente
     * dentro del grupo.
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // Comprobamos qué RadioButton ha sido seleccionado
        if (checkedId == R.id.rbOpcion1) {
            Toast.makeText(this, "Toast de la Opción 1", Toast.LENGTH_SHORT).show();
        } else if (checkedId == R.id.rbOpcion2) {
            Toast.makeText(this, "Toast de la Opción 2", Toast.LENGTH_SHORT).show();
        }
    }
}

