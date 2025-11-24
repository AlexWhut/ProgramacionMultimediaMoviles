package com.example.preferenciasdialogosnotificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * La Actividad implementa la interfaz View.OnClickListener
 * para gestionar los clics de los botones.
 */
public class DialogoActivity extends AppCompatActivity implements View.OnClickListener {

    // Array de elementos para los listados
    final CharSequence[] elementos = {"CASO 0", "CASO 1", "CASO 2", "CASO 3", "CASO 4", "CASO 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo_layout);

        // 1. Enlazar los botones
        Button btnSimple = findViewById(R.id.btnSimple);
        Button btnBotones = findViewById(R.id.btnBotones);
        Button btnListado = findViewById(R.id.btnListado);
        Button btnSeleccionUnica = findViewById(R.id.btnSeleccionUnica);
        Button btnSeleccionMultiple = findViewById(R.id.btnSeleccionMultiple);

        // 2. Registrar los listeners
        // "this" se refiere a la propia Activity, que implementa OnClickListener
        btnSimple.setOnClickListener(this);
        btnBotones.setOnClickListener(this);
        btnListado.setOnClickListener(this);
        btnSeleccionUnica.setOnClickListener(this);
        btnSeleccionMultiple.setOnClickListener(this);
    }

    /**
     * Método central que gestiona todos los clics de los botones.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSimple) {
            mostrarDialogoSimple();
        } else if (id == R.id.btnBotones) {
            mostrarDialogoConBotones();
        } else if (id == R.id.btnListado) {
            mostrarDialogoListado();
        } else if (id == R.id.btnSeleccionUnica) {
            mostrarDialogoSeleccionUnica();
        } else if (id == R.id.btnSeleccionMultiple) {
            mostrarDialogoSeleccionMultiple();
        }
    }

    // --- MÉTODOS PARA CADA TIPO DE DIÁLOGO ---

    /**
     * Muestra el diálogo más básico, con título, icono y mensaje.
     */
    private void mostrarDialogoSimple() {
        // 1. Instanciar el constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Setear características
        builder.setMessage("EJEMPLO DE DIÁLOGO")
                .setTitle("TÍTULO DEL DIÁLOGO")
                .setIcon(R.mipmap.ic_launcher);

        // 3. Crear y mostrar el diálogo
        AlertDialog dialogo = builder.create();
        dialogo.show();
    }

    /**
     * Muestra un diálogo con 3 botones (Positivo, Negativo, Neutral).
     */
    private void mostrarDialogoConBotones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DIÁLOGO CON BOTONES")
                .setMessage("Elige una opción.")
                // Botón Positivo
                .setPositiveButton("PRIMERO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mostrarToast("Pulsado PRIMERO");
                    }
                })
                // Botón Negativo
                .setNegativeButton("SEGUNDO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mostrarToast("Pulsado SEGUNDO");
                    }
                })
                // Botón Neutral
                .setNeutralButton("TERCERO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mostrarToast("Pulsado TERCERO");
                    }
                });

        builder.create().show();
    }

    /**
     * Muestra un diálogo con un listado simple.
     * El mensaje (setMessage) no se puede usar aquí.
     */
    private void mostrarDialogoListado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DIÁLOGO DE LISTADO")
                // Usamos setItems con el array y un listener
                .setItems(elementos, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 'id' es la posición del elemento pulsado (0, 1, 2...)
                        mostrarToast("Has elegido: " + elementos[id]);
                    }
                });

        builder.create().show();
    }

    /**
     * Muestra un diálogo con un listado de selección única (RadioButtons).
     */
    private void mostrarDialogoSeleccionUnica() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SELECCIÓN ÚNICA");

        // Usamos setSingleChoiceItems
        // El '0' indica que el "CASO 0" estará marcado por defecto.
        builder.setSingleChoiceItems(elementos, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // El 'id' es la posición del elemento seleccionado
                mostrarToast("Seleccionado (temporal): " + elementos[id]);
                // En una app real, guardaríamos esta selección
            }
        });

        // Añadimos un botón para cerrar el diálogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss(); // Cierra el diálogo
            }
        });

        builder.create().show();
    }

    /**
     * Muestra un diálogo con un listado de selección múltiple (CheckBoxes).
     */
    private void mostrarDialogoSeleccionMultiple() {
        // Para la selección múltiple, necesitamos un array de booleanos
        // que rastree qué elementos están marcados.
        final boolean[] seleccionados = new boolean[elementos.length]; // Todos false por defecto

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SELECCIÓN MÚLTIPLE");

        // Usamos setMultiChoiceItems
        builder.setMultiChoiceItems(elementos, null, // 'null' o el array 'seleccionados'
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // 'which' es la posición del elemento
                        // 'isChecked' es el nuevo estado (marcado o desmarcado)
                        if (isChecked) {
                            seleccionados[which] = true;
                            Log.d("DialogoMultiple", "Marcado: " + elementos[which]);
                        } else {
                            seleccionados[which] = false;
                            Log.d("DialogoMultiple", "Desmarcado: " + elementos[which]);
                        }
                    }
                });

        // Añadimos un botón "Aceptar" para procesar la selección final
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                StringBuilder seleccionFinal = new StringBuilder("Seleccionado: ");
                for (int i = 0; i < seleccionados.length; i++) {
                    if (seleccionados[i]) {
                        seleccionFinal.append(elementos[i]).append(" ");
                    }
                }
                mostrarToast(seleccionFinal.toString());
            }
        });

        builder.create().show();
    }

    /**
     * Método auxiliar para mostrar un Toast
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
