package android.avanzado.actividad_9_3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AndroidVersion> versionList = new ArrayList<>();
        versionList.add(new AndroidVersion("DONUTS", R.drawable.donuts, "Descripción de Donuts"));
        versionList.add(new AndroidVersion("FROYO", R.drawable.froyo, "Descripción de Froyo"));
        versionList.add(new AndroidVersion("GINGERBREAD", R.drawable.gingerbread, "Descripción de Gingerbread"));
        versionList.add(new AndroidVersion("HONEYCOMB", R.drawable.honeycomb, "Descripción de Honeycomb"));
        versionList.add(new AndroidVersion("ICE CREAM", R.drawable.icecream, "Descripción de Ice Cream"));
        versionList.add(new AndroidVersion("JELLY BEAN", R.drawable.jellybean, "Descripción de Jelly Bean"));
        versionList.add(new AndroidVersion("KITKAT", R.drawable.kitkat, "Su nombre se debe a la chocolatina KitKat, de la empresa internacional Nestlé. Posibilidad de impresión mediante WIFI. WebViews basadas en el motor de Chromium."));
        versionList.add(new AndroidVersion("LOLLIPOP", R.drawable.lollipop, "Descripción de Lollipop"));

        AndroidVersionAdapter adapter = new AndroidVersionAdapter(versionList, version -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("name", version.getName());
            intent.putExtra("imageResId", version.getImageResId());
            intent.putExtra("description", version.getDescription());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}