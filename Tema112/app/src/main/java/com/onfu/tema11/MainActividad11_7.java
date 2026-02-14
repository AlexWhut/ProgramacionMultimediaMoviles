package com.onfu.tema11;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_7 extends AppCompatActivity implements SensorEventListener {
    // UI
    private TextView txtValores;
    private Button btnAccel, btnGyro, btnLuz, btnStop;
    // Sensores
    private SensorManager sensorManager;
    private Sensor acelerometro, giroscopio, luz;
    private int sensorActivo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_7);
        inicializarVistas();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        btnAccel.setOnClickListener(v -> activarSensor(Sensor.TYPE_ACCELEROMETER));
        btnGyro.setOnClickListener(v -> activarSensor(Sensor.TYPE_GYROSCOPE));
        btnLuz.setOnClickListener(v -> activarSensor(Sensor.TYPE_LIGHT));
        btnStop.setOnClickListener(v -> detenerMedicion());
    }

    private void inicializarVistas() {
        txtValores = findViewById(R.id.txtValores);
        btnAccel = findViewById(R.id.btnAcelerometro);
        btnGyro = findViewById(R.id.btnGiroscopio);
        btnLuz = findViewById(R.id.btnLuz);
        btnStop = findViewById(R.id.btnDetener);
    }

    private void activarSensor(int tipoSensor) {
        detenerMedicion();
        Sensor sensorSeleccionado = null;
        String nombreSensor = "";
        switch (tipoSensor) {
            case Sensor.TYPE_ACCELEROMETER:
                sensorSeleccionado = acelerometro;
                nombreSensor = "Acelerómetro";
                break;
            case Sensor.TYPE_GYROSCOPE:
                sensorSeleccionado = giroscopio;
                nombreSensor = "Giroscopio";
                break;
            case Sensor.TYPE_LIGHT:
                sensorSeleccionado = luz;
                nombreSensor = "Luz Ambiental";
                break;
        }
        if (sensorSeleccionado == null) {
            Toast.makeText(this, "Tu dispositivo no tiene " + nombreSensor, Toast.LENGTH_SHORT).show();
            return;
        }
        sensorManager.registerListener(this, sensorSeleccionado, SensorManager.SENSOR_DELAY_UI);
        sensorActivo = tipoSensor;
        actualizarBotones(false);
        txtValores.setText("Leyendo " + nombreSensor + "...");
    }

    private void detenerMedicion() {
        if (sensorActivo != 0) {
            sensorManager.unregisterListener(this);
            sensorActivo = 0;
            txtValores.setText("Medición detenida.");
            actualizarBotones(true);
            // Personalización: restaurar fondo y color texto
            txtValores.setBackgroundColor(Color.parseColor("#263445"));
            txtValores.setTextColor(Color.WHITE);
        }
    }

    private void actualizarBotones(boolean habilitarInicio) {
        btnAccel.setEnabled(habilitarInicio);
        btnGyro.setEnabled(habilitarInicio);
        btnLuz.setEnabled(habilitarInicio);
        btnStop.setEnabled(!habilitarInicio);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder resultado = new StringBuilder();
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                resultado.append("ACELERÓMETRO\n\n");
                resultado.append(String.format("X: %.2f m/s²\n", event.values[0]));
                resultado.append(String.format("Y: %.2f m/s²\n", event.values[1]));
                resultado.append(String.format("Z: %.2f m/s²\n", event.values[2]));
                resultado.append("\n(Incluye gravedad)");
                // Personalización: Detección de shake
                double fuerza = Math.sqrt(
                        event.values[0]*event.values[0] +
                        event.values[1]*event.values[1] +
                        event.values[2]*event.values[2]);
                if (fuerza > 14) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (v != null) v.vibrate(100);
                    Toast.makeText(this, "¡No me agites!", Toast.LENGTH_SHORT).show();
                }
                break;
            case Sensor.TYPE_GYROSCOPE:
                resultado.append("GIROSCOPIO\n\n");
                resultado.append(String.format("X: %.2f rad/s\n", event.values[0]));
                resultado.append(String.format("Y: %.2f rad/s\n", event.values[1]));
                resultado.append(String.format("Z: %.2f rad/s\n", event.values[2]));
                resultado.append("\n(Velocidad de rotación)");
                break;
            case Sensor.TYPE_LIGHT:
                resultado.append("SENSOR DE LUZ\n\n");
                resultado.append(String.format("Nivel: %.0f lx\n", event.values[0]));
                if (event.values[0] < 50) {
                    resultado.append("\n¡Está muy oscuro!");
                    txtValores.setBackgroundColor(Color.BLACK);
                    txtValores.setTextColor(Color.WHITE);
                } else if (event.values[0] > 1000) {
                    resultado.append("\n¡Hay mucha luz!");
                    txtValores.setBackgroundColor(Color.WHITE);
                    txtValores.setTextColor(Color.BLACK);
                } else {
                    txtValores.setBackgroundColor(Color.parseColor("#263445"));
                    txtValores.setTextColor(Color.WHITE);
                }
                break;
        }
        txtValores.setText(resultado.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No necesario
    }

    @Override
    protected void onPause() {
        super.onPause();
        detenerMedicion();
    }
}
