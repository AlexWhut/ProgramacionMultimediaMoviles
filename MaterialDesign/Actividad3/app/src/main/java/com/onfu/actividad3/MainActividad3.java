package com.onfu.actividad3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

public class MainActividad3 extends AppCompatActivity {
    // Personalización: Mensajes de error con emoji 🚫
    private TextInputLayout textInputLayoutNombre;
    private TextInputEditText editTextNombre;
    private TextInputLayout textInputLayoutCorreo;
    private TextInputEditText editTextCorreo;
    private TextInputLayout textInputLayoutTelefono;
    private TextInputEditText editTextTelefono;
    private MaterialButton buttonCancelar;
    private MaterialButton buttonAceptar;

    // Patrones de validación
    private static final Pattern PATTERN_NOMBRE = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]{3,30}$");
    private static final Pattern PATTERN_CORREO = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PATTERN_TELEFONO = Pattern.compile("^[+]?[0-9]{9,15}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad3); // Personalización: Usar layout personalizado

        textInputLayoutNombre = findViewById(R.id.textInputLayoutNombre);
        editTextNombre = findViewById(R.id.editTextNombre);
        textInputLayoutCorreo = findViewById(R.id.textInputLayoutCorreo);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        textInputLayoutTelefono = findViewById(R.id.textInputLayoutTelefono);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        buttonCancelar = findViewById(R.id.buttonCancelar);
        buttonAceptar = findViewById(R.id.buttonAceptar);

        setupTextWatchers();
        setupButtonListeners();
    }

    private void setupTextWatchers() {
        editTextNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayoutNombre.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {
                validarNombre();
            }
        });
        editTextCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayoutCorreo.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {
                validarCorreo();
            }
        });
        editTextTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayoutTelefono.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {
                validarTelefono();
            }
        });
    }

    private void setupButtonListeners() {
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActividad3.this, "Operación cancelada", Toast.LENGTH_SHORT).show();
                editTextNombre.setText("");
                editTextCorreo.setText("");
                editTextTelefono.setText("");
                textInputLayoutNombre.setError(null);
                textInputLayoutCorreo.setError(null);
                textInputLayoutTelefono.setError(null);
            }
        });
        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarTodosLosCampos()) {
                    Toast.makeText(MainActividad3.this, "REGISTRO CORRECTO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActividad3.this, "🚫 Corrige los errores del formulario", Toast.LENGTH_SHORT).show(); // Personalización
                }
            }
        });
    }

    private boolean validarNombre() {
        String nombre = editTextNombre.getText().toString().trim();
        if (nombre.isEmpty()) {
            textInputLayoutNombre.setError("🚫 El nombre no puede estar vacío"); // Personalización
            return false;
        } else if (!PATTERN_NOMBRE.matcher(nombre).matches()) {
            textInputLayoutNombre.setError("🚫 Nombre inválido (3-30 letras)"); // Personalización
            return false;
        } else {
            textInputLayoutNombre.setError(null);
            return true;
        }
    }

    private boolean validarCorreo() {
        String correo = editTextCorreo.getText().toString().trim();
        if (correo.isEmpty()) {
            textInputLayoutCorreo.setError("🚫 El correo no puede estar vacío"); // Personalización
            return false;
        } else if (!PATTERN_CORREO.matcher(correo).matches()) {
            textInputLayoutCorreo.setError("🚫 Formato de correo inválido"); // Personalización
            return false;
        } else {
            textInputLayoutCorreo.setError(null);
            return true;
        }
    }

    private boolean validarTelefono() {
        String telefono = editTextTelefono.getText().toString().trim();
        if (telefono.isEmpty()) {
            textInputLayoutTelefono.setError("🚫 El teléfono no puede estar vacío"); // Personalización
            return false;
        } else if (!PATTERN_TELEFONO.matcher(telefono).matches()) {
            textInputLayoutTelefono.setError("🚫 Número de teléfono inválido (9-15 dígitos)"); // Personalización
            return false;
        } else {
            textInputLayoutTelefono.setError(null);
            return true;
        }
    }

    private boolean validarTodosLosCampos() {
        boolean esNombreValido = validarNombre();
        boolean esCorreoValido = validarCorreo();
        boolean esTelefonoValido = validarTelefono();
        return esNombreValido && esCorreoValido && esTelefonoValido;
    }
}
// Comentario: Personalización de mensajes de error con emoji 🚫 y uso de layout personalizado. Archivo: MainActividad3.java