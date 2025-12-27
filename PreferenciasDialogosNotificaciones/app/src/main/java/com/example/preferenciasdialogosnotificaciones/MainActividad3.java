package com.example.preferenciasdialogosnotificaciones;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActividad3 extends AppCompatActivity {
    private MaterialButton btnMostrarDialogo, btnResultado;
    private boolean mostrarDialogoConBotones = true;
    private final String[] dias = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SÁBADO", "DOMINGO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad3);

        btnMostrarDialogo = findViewById(R.id.btnMostrarDialogo);
        btnResultado = findViewById(R.id.btnResultado);

        btnMostrarDialogo.setOnClickListener(v -> {
            if (mostrarDialogoConBotones) {
                mostrarDialogoBotones();
            } else {
                mostrarDialogoListado();
            }
            mostrarDialogoConBotones = !mostrarDialogoConBotones;
        });
    }

    private void mostrarDialogoBotones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ACTIVIDAD DE DIÁLOGO");
        builder.setMessage("Toca uno de los botones");
        builder.setIcon(R.drawable.ic_dialog_info);
        builder.setNegativeButton("NEGATIVO", (dialog, which) -> mostrarRespuesta("NEGATIVO"));
        builder.setNeutralButton("NEUTRO", (dialog, which) -> mostrarRespuesta("NEUTRO"));
        builder.setPositiveButton("POSITIVO", (dialog, which) -> mostrarRespuesta("POSITIVO"));
        builder.show();
    }

    private void mostrarDialogoListado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ACTIVIDAD DE DIÁLOGO");
        builder.setItems(dias, (dialog, which) -> mostrarRespuesta("TOCADO EL " + dias[which]));
        builder.setIcon(R.drawable.ic_dialog_info);
        builder.show();
    }

    private void mostrarRespuesta(String texto) {
        btnResultado.setText(texto);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado_layout,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView textoToast = layout.findViewById(R.id.textViewToast);
        textoToast.setText(texto);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);
        toast.show();
    }
}
