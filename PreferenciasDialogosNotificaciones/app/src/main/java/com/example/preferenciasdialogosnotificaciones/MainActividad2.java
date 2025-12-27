
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

public class MainActividad2 extends AppCompatActivity implements
        View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    private EditText editTextMensaje;
    private Button btnToastSeekBar;
    private SeekBar seekBarPosicion;
    private int yOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad2);

        editTextMensaje = findViewById(R.id.editTextMensaje);
        btnToastSeekBar = findViewById(R.id.btnToastSeekBar);
        seekBarPosicion = findViewById(R.id.seekBarPosicion);

        btnToastSeekBar.setOnClickListener(this);
        seekBarPosicion.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnToastSeekBar) {
            String mensaje = editTextMensaje.getText().toString();
            if (mensaje.isEmpty()) {
                mensaje = "¡Escribe algo primero!";
            }
            // Limitar el offset máximo para que el Toast no suba más allá de la mitad de la pantalla
            int screenHeight = getResources().getDisplayMetrics().heightPixels;
            int minOffset = screenHeight / 2; // mitad de la pantalla (lo más alto)
            int maxOffset = screenHeight - 200; // 200px de margen inferior
            int mappedOffset = minOffset + (int)((maxOffset - minOffset) * (yOffset / 1000f));
            mostrarToastPersonalizado(mensaje, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, mappedOffset);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekBarPosicion) {
            this.yOffset = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No es necesario implementar nada aquí
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // No es necesario implementar nada aquí
    }

    private void mostrarToastPersonalizado(String mensaje, int gravity, int xOffset, int yOffset) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado_layout,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView textoToast = layout.findViewById(R.id.textViewToast);
        textoToast.setText(mensaje);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setView(layout);
        toast.show();
    }
}

