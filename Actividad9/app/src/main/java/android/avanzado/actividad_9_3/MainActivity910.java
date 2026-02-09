package android.avanzado.actividad_9_3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.widget.TextView;
import android.widget.EditText;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity910 extends AppCompatActivity {
    private static final String ACTION_CUSTOM_BROADCAST = "android.avanzado.actividad_9_3.CUSTOM_BROADCAST";
    private BroadcastReceiver batteryReceiver;
    private BroadcastReceiver customReceiver;
    private ImageView imageFeedback;
    private MaterialCardView cardFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_910);

        EditText editTextUrl = findViewById(R.id.editTextUrl);
        MaterialButton buttonSendBroadcast = findViewById(R.id.buttonSendBroadcast);
        imageFeedback = findViewById(R.id.imageFeedback);
        cardFeedback = findViewById(R.id.cardFeedback);

        // Mostrar estado de batería al iniciar
        batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryPct = (int) ((level / (float) scale) * 100);
                String status = "Batería: " + batteryPct + "%";
                Toast.makeText(context, status, Toast.LENGTH_LONG).show();
                imageFeedback.setImageResource(android.R.drawable.ic_dialog_info);
                imageFeedback.setColorFilter(0xFFFF7043); // naranja cálido
                cardFeedback.setVisibility(MaterialCardView.VISIBLE);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED), RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        }

        // Broadcast personalizado
        customReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (ACTION_CUSTOM_BROADCAST.equals(intent.getAction())) {
                    String url = intent.getStringExtra("url");
                    String msg = (url != null && !url.isEmpty()) ? "Broadcast recibido: " + url : "Broadcast recibido";
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    imageFeedback.setImageResource(android.R.drawable.ic_menu_send);
                    imageFeedback.setColorFilter(0xFFFF7043); // naranja cálido
                    cardFeedback.setVisibility(MaterialCardView.VISIBLE);
                }
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST), RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
        }

        buttonSendBroadcast.setOnClickListener(v -> {
            String url = editTextUrl.getText().toString().trim();
            if (url.isEmpty()) {
                editTextUrl.setError("Introduce una URL");
                return;
            }
            Intent intent = new Intent(ACTION_CUSTOM_BROADCAST);
            intent.putExtra("url", url);
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (batteryReceiver != null) unregisterReceiver(batteryReceiver);
        if (customReceiver != null) unregisterReceiver(customReceiver);
    }
}
