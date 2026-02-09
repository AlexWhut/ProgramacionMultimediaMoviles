package android.avanzado.actividades10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity104 extends AppCompatActivity {
    private EditText etId, etNombre, etApellido;
    private Button btnInsertar, btnListar, btnModificar, btnBorrar;
    private TextView tvListado;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main104);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnListar = findViewById(R.id.btnListar);
        btnModificar = findViewById(R.id.btnModificar);
        btnBorrar = findViewById(R.id.btnBorrar);
        tvListado = findViewById(R.id.tvListado);
        dbHelper = new DBHelper(this);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });
    }

    private void insertar() {
        String id = etId.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_ID, Integer.parseInt(id));
        values.put(DBHelper.COL_NOMBRE, nombre);
        values.put(DBHelper.COL_APELLIDO, apellido);
        long res = db.insert(DBHelper.TABLE_NAME, null, values);
        if (res == -1) {
            Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Insertado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
        db.close();
    }

    private void listar() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, DBHelper.COL_ID);
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COL_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_NOMBRE));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_APELLIDO));
            sb.append(i).append(" - ").append(nombre).append(" ").append(apellido).append("\n");
            i++;
        }
        cursor.close();
        db.close();
        tvListado.setText(sb.toString());
    }

    private void modificar() {
        String id = etId.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_NOMBRE, nombre);
        values.put(DBHelper.COL_APELLIDO, apellido);
        int res = db.update(DBHelper.TABLE_NAME, values, DBHelper.COL_ID + "=?", new String[]{id});
        if (res == 0) {
            Toast.makeText(this, "No existe ese ID", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Modificado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
        db.close();
    }

    private void borrar() {
        String id = etId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(this, "Introduce el ID", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int res = db.delete(DBHelper.TABLE_NAME, DBHelper.COL_ID + "=?", new String[]{id});
        if (res == 0) {
            Toast.makeText(this, "No existe ese ID", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
        db.close();
    }

    private void limpiarCampos() {
        etId.setText("");
        etNombre.setText("");
        etApellido.setText("");
    }
}
