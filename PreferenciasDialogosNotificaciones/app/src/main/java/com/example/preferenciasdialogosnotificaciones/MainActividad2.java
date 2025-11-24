package com.example.preferenciasdialogosnotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * La Actividad implementa las interfaces para los listeners
 * que necesitamos: OnClickListener (para los botones) y
 * OnSeekBarChangeListener (para el SeekBar).
 */
public class MainActividad2 extends AppCompatActivity implements
        View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    // Declaramos los componentes de la UI
    private EditText editTextMensaje;
    private Button btnToastEditText, btnToastSeekBar;
    private SeekBar seekBarPosicion;

    // Variable para guardar la posición del SeekBar
    private int yOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad2);

        // 1. Enlazar las vistas del layout
        editTextMensaje = findViewById(R.id.editTextMensaje);
        btnToastEditText = findViewById(R.id.btnToastEditText);
        btnToastSeekBar = findViewById(R.id.btnToastSeekBar);
        seekBarPosicion = findViewById(R.id.seekBarPosicion);

        // 2. Registrar los listeners
        // Le decimos a cada control que ESTA CLASE (this)
        // se encargará de gestionar sus eventos.
        btnToastEditText.setOnClickListener(this);
        btnToastSeekBar.setOnClickListener(this);
        seekBarPosicion.setOnSeekBarChangeListener(this);
    }

    /**
     * MÉTODO ESCUCHADOR para View.OnClickListener (Botones)
     */
    @Override
    public void onClick(View v) {
        // Comprobamos qué botón se ha pulsado
        int id = v.getId();
        if (id == R.id.btnToastEditText) {

            // --- Lógica del primer botón ---
            String mensaje = editTextMensaje.getText().toString();
            if (mensaje.isEmpty()) {
                mensaje = "¡Escribe algo primero!";
            }
            // Mostramos el toast personalizado en la parte inferior
            mostrarToastPersonalizado(mensaje, Gravity.BOTTOM, 0, 150);

        } else if (id == R.id.btnToastSeekBar) {

            // --- Lógica del segundo botón ---
            // Mostramos el toast en el centro, con el desplazamiento vertical
            // que hemos guardado del SeekBar (yOffset).
            mostrarToastPersonalizado("EJEMPLO POSICIÓN", Gravity.CENTER, 0, yOffset);
        }
    }

    /**
     * MÉTODO ESCUCHADOR para OnSeekBarChangeListener (SeekBar)
     * Se llama cuando el valor del SeekBar cambia.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Actualizamos nuestra variable 'yOffset' con el progreso del SeekBar.
        // 'progress' irá de 0 a 500 (el 'max' que definimos en el XML).
        if (seekBar.getId() == R.id.seekBarPosicion) {
            this.yOffset = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No necesitamos hacer nada aquí
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // No necesitamos hacer nada aquí
    }

    /**
     * --- MÉTODO AUXILIAR PARA EL TOAST PERSONALIZADO ---
     * Este método encapsula toda la lógica para crear y mostrar
     * nuestro Toast personalizado.
     *
     * @param mensaje El texto a mostrar.
     * @param gravity Dónde debe aparecer (ej. Gravity.CENTER).
     * @param xOffset Desplazamiento horizontal.
     * @param yOffset Desplazamiento vertical.
     */
    private void mostrarToastPersonalizado(String mensaje, int gravity, int xOffset, int yOffset) {
        // 1. Obtener el servicio LayoutInflater
        LayoutInflater inflater = getLayoutInflater();

        // 2. "Inflar" el layout del toast personalizado
        View layout = inflater.inflate(R.layout.toast_personalizado_layout,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        // 3. Encontrar el TextView dentro de nuestro layout inflado
        TextView textoToast = layout.findViewById(R.id.textViewToast);

        // 4. Establecer el mensaje
        textoToast.setText(mensaje);

        // 5. Crear el objeto Toast
        Toast toast = new Toast(getApplicationContext());

        // 6. Configurar la duración y la posición
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(gravity, xOffset, yOffset);

        // 7. ¡LA CLAVE! Asignar nuestra vista personalizada al Toast
        toast.setView(layout);

        // 8. Mostrar el Toast
        toast.show();
    }
}

