package android.avanzado.actividad_9_3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtener datos del intent
        String name = getIntent().getStringExtra("name");
        int imageResId = getIntent().getIntExtra("imageResId", 0);
        String description = getIntent().getStringExtra("description");

        // Asignar a vistas
        ((android.widget.TextView)findViewById(R.id.textViewDetailName)).setText(name);
        ((android.widget.ImageView)findViewById(R.id.imageViewDetail)).setImageResource(imageResId);
        ((android.widget.TextView)findViewById(R.id.textViewDetailDescription)).setText(description);
    }
}
