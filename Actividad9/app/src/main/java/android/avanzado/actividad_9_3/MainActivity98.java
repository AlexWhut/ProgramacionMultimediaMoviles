package android.avanzado.actividad_9_3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import android.os.Handler;
import android.os.Looper;

public class MainActivity98 extends AppCompatActivity {
    private Handler handler = new Handler(Looper.getMainLooper());
    private long startTime = 0L;
    private boolean running = false;
    private long elapsedTime = 0L;
    private TextView textViewTimer;

    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime + elapsedTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            int milliseconds = (int) (millis % 1000);
            seconds = seconds % 60;
            minutes = minutes % 60;
            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds) + ":" + String.format("%03d", milliseconds);
            textViewTimer.setText(time);
            if (running) {
                handler.postDelayed(this, 10);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_98);

        textViewTimer = findViewById(R.id.textViewTimer);
        MaterialButton buttonStart = findViewById(R.id.buttonStart);
        MaterialButton buttonPause = findViewById(R.id.buttonPause);
        MaterialButton buttonReset = findViewById(R.id.buttonReset);

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
            textViewTimer.setText("00:00:00:000");
            handler.removeCallbacks(timerRunnable);
        });

        textViewTimer.setText("00:00:00:000");
    }
}
