package android.avanzado.actividades10;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity105 extends AppCompatActivity {
    private Button btnDescargar;
    private TextView tvRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main105);

        btnDescargar = findViewById(R.id.btnDescargar);
        tvRespuesta = findViewById(R.id.tvRespuesta);

        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRespuesta.setText("Esperando conexión...");
                descargarArchivo();
            }
        });
    }

    private void descargarArchivo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr = "https://www.google.com/humans.txt";
                StringBuilder sb = new StringBuilder();
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    reader.close();
                    conn.disconnect();
                } catch (Exception e) {
                    sb.append("Error de conexión: ").append(e.getMessage());
                }
                final String resultado = sb.toString();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        tvRespuesta.setText(resultado);
                    }
                });
            }
        }).start();
    }
}
