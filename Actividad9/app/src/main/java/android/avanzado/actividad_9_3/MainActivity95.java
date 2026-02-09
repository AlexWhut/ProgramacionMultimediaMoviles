package android.avanzado.actividad_9_3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity95 extends AppCompatActivity {
    private android.os.Handler handler = new android.os.Handler();
    private long startTime = 0L;
    private boolean running = false;
    private long elapsedTime = 0L;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime + elapsedTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            String time = String.format("%d:%02d:%02d", hours, minutes, seconds);
            textViewTimer.setText(time);
            if (running) {
                handler.postDelayed(this, 1000);
            }
        }
    };

    private android.widget.TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_95);

        textViewTimer = findViewById(R.id.textViewTimer);
        android.widget.Button buttonStart = findViewById(R.id.buttonStart);
        android.widget.Button buttonPause = findViewById(R.id.buttonPause);
        android.widget.Button buttonReset = findViewById(R.id.buttonReset);

        buttonStart.setOnClickListener(v -> {
            if (!running) {
                running = true;
                startTime = System.currentTimeMillis();
                handler.post(timerRunnable);
            }
        });

        buttonPause.setOnClickListener(v -> {
            if (running) {
                running = false;
                elapsedTime += System.currentTimeMillis() - startTime;
                handler.removeCallbacks(timerRunnable);
            }
        });

        buttonReset.setOnClickListener(v -> {
            running = false;
            startTime = 0L;
            elapsedTime = 0L;
            textViewTimer.setText("0:00:00");
            handler.removeCallbacks(timerRunnable);
        });
    }
}
