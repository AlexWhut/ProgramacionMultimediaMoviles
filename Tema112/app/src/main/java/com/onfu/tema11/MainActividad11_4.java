package com.onfu.tema11;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActividad11_4 extends AppCompatActivity {
    private SoundPool soundPool;
    private int idSonido1, idSonido2;
    private float volumenActual = 1.0f;
    private float rateActual = 1.0f;
    private TextView tvValVolumen, tvValRate;
    private SeekBar sbVolumen, sbRate;
    private Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_4);
        inicializarVistas();
        crearSoundPool();
        configurarListeners();
    }

    private void inicializarVistas() {
        tvValVolumen = findViewById(R.id.tvLabelVolumen);
        tvValRate = findViewById(R.id.tvLabelRate);
        sbVolumen = findViewById(R.id.sbVolumen);
        sbRate = findViewById(R.id.sbRate);
        btn1 = findViewById(R.id.btnSonido1);
        btn2 = findViewById(R.id.btnSonido2);
    }

    private void crearSoundPool() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();
        idSonido1 = soundPool.load(this, R.raw.goldensonido1, 1);
        idSonido2 = soundPool.load(this, R.raw.vanceripsonido2, 1);
    }

    private void configurarListeners() {
        sbVolumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumenActual = progress / 100.0f;
                tvValVolumen.setText("Volumen: " + volumenActual);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sbRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rateActual = progress / 100.0f;
                if (rateActual < 0.1f) rateActual = 0.1f;
                tvValRate.setText("Velocidad (Rate): " + rateActual + "x");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirSonido(idSonido1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirSonido(idSonido2);
            }
        });
    }

    private void reproducirSonido(int soundId) {
        if (soundId != 0) {
            soundPool.play(soundId, volumenActual, volumenActual, 1, 0, rateActual);
        } else {
            Toast.makeText(this, "El sonido no ha cargado aún", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
