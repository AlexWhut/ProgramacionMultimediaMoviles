package android.avanzado.actividad_9_3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Button;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.os.Looper;

public class MainActivity96 extends AppCompatActivity {
    private ExecutorService executor;
    private List<Future<?>> futures = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private TextView textViewLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_96);

        textViewLog = findViewById(R.id.textViewLog);
        Button buttonStartTasks = findViewById(R.id.buttonStartTasks);
        Button buttonStopAll = findViewById(R.id.buttonStopAll);

        buttonStartTasks.setOnClickListener(v -> startTasks());
        buttonStopAll.setOnClickListener(v -> stopAllTasks());
    }

    private void startTasks() {
        appendLog("Iniciando tareas...\n");
        executor = Executors.newFixedThreadPool(5);
        futures.clear();

        // Tarea rápida
        for (int i = 1; i <= 3; i++) {
            int taskNum = i;
            futures.add(executor.submit(() -> {
                String msg = "Tarea " + taskNum + " (Rápida): Ejecutada en " + Thread.currentThread().getName();
                handler.post(() -> appendLog(msg + "\n"));
            }));
        }
        // Tarea media
        for (int i = 4; i <= 5; i++) {
            int taskNum = i;
            futures.add(executor.submit(() -> {
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                String msg = "Tarea " + taskNum + " (Media): Ejecutada en " + Thread.currentThread().getName();
                handler.post(() -> appendLog(msg + "\n"));
            }));
        }
        // Tarea lenta
        for (int i = 6; i <= 7; i++) {
            int taskNum = i;
            futures.add(executor.submit(() -> {
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
                String msg = "Tarea " + taskNum + " (Lenta): Ejecutada en " + Thread.currentThread().getName();
                handler.post(() -> appendLog(msg + "\n"));
            }));
        }
        // Tarea final (solo una vez)
        futures.add(executor.submit(() -> {
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            String msg = ">> Tarea 8 (FINAL): ¡Solo me ejecuto una vez! <<";
            handler.post(() -> appendLog(msg + "\n"));
        }));
    }

    private void stopAllTasks() {
        appendLog("Deteniendo todas las tareas...\n");
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
        futures.clear();
    }

    private void appendLog(String msg) {
        textViewLog.append(msg);
    }
}
