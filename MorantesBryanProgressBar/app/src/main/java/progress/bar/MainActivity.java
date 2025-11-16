package progress.bar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView txtPercent;
    Button btnStart;
    Handler handler = new Handler();
    int progreso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        txtPercent = findViewById(R.id.txtPercent);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> iniciarProgreso());
    }

    private void iniciarProgreso() {
        progreso = 0;

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (progreso <= 100) {
                    progressBar.setProgress(progreso);
                    txtPercent.setText(progreso + "%");
                    progreso++;

                    handler.postDelayed(this, 50); // velocidad
                }
            }
        });
    }
}
