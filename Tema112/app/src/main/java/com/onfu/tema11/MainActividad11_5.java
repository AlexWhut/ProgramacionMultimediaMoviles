package com.onfu.tema11;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.IOException;

public class MainActividad11_5 extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String TAG = "AudioApp";

    private String ficheroSalida = null;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private ImageButton btnGrabar, btnParar, btnReproducir;
    private TextView tvEstado;

    private boolean grabando = false;
    private boolean reproduciendo = false;
    private boolean permisoConcedido = false;

    private final String[] permisos = {Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_5);

        ficheroSalida = getExternalFilesDir(null).getAbsolutePath() + "/mi_grabacion_audio.3gp";

        btnGrabar    = findViewById(R.id.btnGrabar);
        btnParar     = findViewById(R.id.btnParar);
        btnReproducir = findViewById(R.id.btnReproducir);
        tvEstado     = findViewById(R.id.tvEstado);

        // Solicitar permiso de micrófono
        ActivityCompat.requestPermissions(this, permisos, REQUEST_RECORD_AUDIO_PERMISSION);

        btnGrabar.setOnClickListener(v -> comenzarGrabacion());
        btnParar.setOnClickListener(v -> detenerGrabacion());
        btnReproducir.setOnClickListener(v -> comenzarReproduccion());
    }

    // ──────────────────────────────────────────────
    // GRABAR
    // ──────────────────────────────────────────────
    private void comenzarGrabacion() {
        // FIX 1: Verificar permiso antes de intentar grabar
        if (!permisoConcedido) {
            Toast.makeText(this, "Permiso de micrófono no concedido", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, permisos, REQUEST_RECORD_AUDIO_PERMISSION);
            return;
        }

        liberarMediaRecorder(); // asegurar limpieza previa

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(ficheroSalida);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            grabando = true;
            actualizarBotones(false, true, false);
            tvEstado.setText("Grabando...");
            Toast.makeText(this, "Grabando audio...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, "Falló la grabación", e);
            Toast.makeText(this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
            liberarMediaRecorder();
        }
    }

    // ──────────────────────────────────────────────
    // DETENER GRABACIÓN
    // ──────────────────────────────────────────────
    private void detenerGrabacion() {
        if (grabando) {
            try {
                mediaRecorder.stop();
            } catch (RuntimeException e) {
                Log.e(TAG, "Error al detener grabación", e);
            }
            liberarMediaRecorder();
            grabando = false;
            actualizarBotones(true, false, true);
            tvEstado.setText("Grabación finalizada. Listo para reproducir.");
        }
    }

    // ──────────────────────────────────────────────
    // REPRODUCIR
    // ──────────────────────────────────────────────
    private void comenzarReproduccion() {
        liberarMediaPlayer(); // asegurar limpieza previa

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(ficheroSalida);
            mediaPlayer.prepare();
            mediaPlayer.start();
            reproduciendo = true;
            actualizarBotones(false, true, false);
            tvEstado.setText("Reproduciendo...");
            Toast.makeText(this, "Reproduciendo audio", Toast.LENGTH_SHORT).show();

            // Al terminar la reproducción, volver al estado inicial
            mediaPlayer.setOnCompletionListener(mp -> {
                liberarMediaPlayer();
                reproduciendo = false;
                actualizarBotones(true, false, true);
                tvEstado.setText("Reproducción finalizada.");
            });

        } catch (IOException e) {
            Log.e(TAG, "Falló la reproducción", e);
            Toast.makeText(this, "Error al reproducir. ¿Has grabado algo?", Toast.LENGTH_SHORT).show();
            liberarMediaPlayer();
        }
    }

    // ──────────────────────────────────────────────
    // HELPERS
    // ──────────────────────────────────────────────

    /** Habilita/deshabilita los tres botones de una sola vez */
    private void actualizarBotones(boolean grabar, boolean parar, boolean reproducir) {
        btnGrabar.setEnabled(grabar);
        btnParar.setEnabled(parar);
        btnReproducir.setEnabled(reproducir);
    }

    private void liberarMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void liberarMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // ──────────────────────────────────────────────
    // PERMISOS
    // ──────────────────────────────────────────────
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permisoConcedido = grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (!permisoConcedido) {
                Toast.makeText(this,
                        "Se necesita permiso de micrófono para usar esta app",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    // ──────────────────────────────────────────────
    // CICLO DE VIDA
    // ──────────────────────────────────────────────
    @Override
    protected void onStop() {
        super.onStop();
        // FIX 3: Resetear flags junto con los recursos
        if (grabando) {
            try { mediaRecorder.stop(); } catch (RuntimeException ignored) {}
            grabando = false;
        }
        liberarMediaRecorder();

        if (reproduciendo) {
            reproduciendo = false;
        }
        liberarMediaPlayer();
    }
}