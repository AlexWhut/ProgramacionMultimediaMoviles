package android.avanzado.actividades10;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity109 extends AppCompatActivity {
    private EditText etNombre, etAnio;
    private Button btnAnadir, btnRefrescar, btnEliminar;
    private RecyclerView rvVersiones;
    private VersionAdapter adapter;
    private List<VersionItem> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main109);

        etNombre = findViewById(R.id.etNombre);
        etAnio = findViewById(R.id.etAnio);
        btnAnadir = findViewById(R.id.btnAnadir);
        btnRefrescar = findViewById(R.id.btnRefrescar);
        btnEliminar = findViewById(R.id.btnEliminar);
        rvVersiones = findViewById(R.id.rvVersiones);

        lista = new ArrayList<>();
        cargarEjemplo();
        adapter = new VersionAdapter(lista, position -> {
            // Selección visual ya gestionada en el adapter
        });
        rvVersiones.setLayoutManager(new GridLayoutManager(this, 2));
        rvVersiones.setAdapter(adapter);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String anio = etAnio.getText().toString().trim();
                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(anio)) {
                    Toast.makeText(MainActivity109.this, "Completa ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                lista.add(new VersionItem(nombre, anio));
                adapter.notifyItemInserted(lista.size() - 1);
                etNombre.setText("");
                etAnio.setText("");
            }
        });

        btnRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                adapter.clearSelection();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = adapter.getSelectedPosition();
                if (pos != RecyclerView.NO_POSITION && pos < lista.size()) {
                    lista.remove(pos);
                    adapter.notifyItemRemoved(pos);
                    adapter.clearSelection();
                } else {
                    Toast.makeText(MainActivity109.this, "Selecciona un elemento", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cargarEjemplo() {
        lista.add(new VersionItem("Oreo", "2017"));
        lista.add(new VersionItem("Apple Pie", "2008"));
        lista.add(new VersionItem("Cupcake 1.5", "2009"));
        lista.add(new VersionItem("Donut 1.6", "2009"));
        lista.add(new VersionItem("Eclair 2.0-2.1", "2009"));
        lista.add(new VersionItem("Froyo 2.2", "2010"));
    }
}
