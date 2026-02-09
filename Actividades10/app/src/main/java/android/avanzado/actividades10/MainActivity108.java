package android.avanzado.actividades10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity108 extends AppCompatActivity {
    private static final int PERMISO_CONTACTOS = 100;
    private Button btnCargar;
    private TextView tvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main108);

        btnCargar = findViewById(R.id.btnCargar);
        tvLista = findViewById(R.id.tvLista);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarContactos();
            }
        });
    }

    private void cargarContactos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISO_CONTACTOS);
        } else {
            mostrarContactos();
        }
    }

    private void mostrarContactos() {
        StringBuilder sb = new StringBuilder();
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        int idCount = 1;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                sb.append("ID: ").append(idCount).append("\nNombre: ").append(nombre);
                int tieneTelefono = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (tieneTelefono > 0) {
                    sb.append("\n[Tiene teléfono]");
                }
                sb.append("\n----------------\n");
                idCount++;
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            sb.append("No hay contactos disponibles.");
        }
        tvLista.setText(sb.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_CONTACTOS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mostrarContactos();
            } else {
                Toast.makeText(this, "Permiso denegado para leer contactos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
