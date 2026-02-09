package android.avanzado.actividades10;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity102 extends AppCompatActivity {

    private EditText etContenido;
    private Button btnGuardar, btnLeer;
    private TextView tvContenidoLeido;
    private static final String NOMBRE_ARCHIVO = "archivo_dam.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContenido = findViewById(R.id.etContenido);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLeer = findViewById(R.id.btnLeer);
        tvContenidoLeido = findViewById(R.id.tvContenidoLeido);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarArchivo();
            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerArchivo();
            }
        });
    }

    private void guardarArchivo() {
        String texto = etContenido.getText().toString();
        java.io.FileOutputStream fos = null;
        try {
            fos = openFileOutput(NOMBRE_ARCHIVO, MODE_PRIVATE);
            fos.write(texto.getBytes());
            fos.close();
            Toast.makeText(this, "Archivo guardado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try { fos.close(); } catch (Exception ignored) {}
            }
        }
    }

    private void leerArchivo() {
        try {
            java.io.FileInputStream fis = openFileInput(NOMBRE_ARCHIVO);
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            fis.close();
            tvContenidoLeido.setText(sb.toString().trim());
        } catch (Exception e) {
            tvContenidoLeido.setText("");
            Toast.makeText(this, "No hay archivo guardado", Toast.LENGTH_SHORT).show();
        }
    }
}