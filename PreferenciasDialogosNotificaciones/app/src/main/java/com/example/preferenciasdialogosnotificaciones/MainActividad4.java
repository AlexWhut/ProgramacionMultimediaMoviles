package com.example.preferenciasdialogosnotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/**
 * Actividad principal que implementa View.OnClickListener
 * para manejar los clics de los botones fuera del onCreate.
 */
public class MainActividad4 extends AppCompatActivity implements View.OnClickListener {

    // Declaramos los botones como variables de clase
    Button btnFecha;
    Button btnHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Establecemos el layout principal
        setContentView(R.layout.layout_actividad4);

        // 2. Referenciamos los botones del layout
        btnFecha = findViewById(R.id.btnMostrarFecha);
        btnHora = findViewById(R.id.btnMostrarHora);

        // 3. Asignamos el listener (esta misma clase) a los botones
        // Cuando se haga clic, se llamará al método onClick() de abajo
        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);
    }

    /**
     * -----------------------------------------------------------
     * GESTOR DE CLICS (Implementado desde View.OnClickListener)
     * -----------------------------------------------------------
     * Este método se ejecuta cuando se pulsa cualquier View
     * que tenga esta clase como su OnClickListener.
     */
    @Override
    public void onClick(View v) {
        // Usamos un switch (o if/else) para identificar qué botón se pulsó
        if (v.getId() == R.id.btnMostrarFecha) {
            // Si fue el botón de fecha, lanzamos el método del DatePicker
            mostrarDatePickerDialog();
        } else if (v.getId() == R.id.btnMostrarHora) {
            // Si fue el botón de hora, lanzamos el método del TimePicker
            mostrarTimePickerDialog();
        }
    }

    /**
     * -----------------------------------------------------------
     * MÉTODO PARA MOSTRAR EL DATEPICKERDIALOG
     * -----------------------------------------------------------
     */
    private void mostrarDatePickerDialog() {
        // 1. Obtener la fecha actual para preseleccionar en el diálogo
        // Usamos Calendar para obtener el año, mes y día actuales
        final Calendar c = Calendar.getInstance();
        int anioActual = c.get(Calendar.YEAR);
        int mesActual = c.get(Calendar.MONTH); // OJO: Enero es 0
        int diaActual = c.get(Calendar.DAY_OF_MONTH);

        // 2. Crear el listener para cuando el usuario "ACEPTA" (OnDateSet)
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Esta lógica se ejecuta SÓLO cuando el usuario pulsa "ACEPTAR"

                // Formateamos la fecha (sumamos 1 al mes porque empieza en 0)
                String fechaSeleccionada = "Fecha: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                // Mostramos el Toast personalizado
                mostrarToastPersonalizado(fechaSeleccionada);
            }
        };

        // 3. Crear y mostrar el DatePickerDialog
        // Pasamos el contexto, el listener, y la fecha actual
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                anioActual,
                mesActual,
                diaActual
        );

        // 4. Mostrar el diálogo
        datePickerDialog.show();
    }

    /**
     * -----------------------------------------------------------
     * MÉTODO PARA MOSTRAR EL TIMEPICKERDIALOG
     * -----------------------------------------------------------
     */
    private void mostrarTimePickerDialog() {
        // 1. Obtener la hora actual
        final Calendar c = Calendar.getInstance();
        int horaActual = c.get(Calendar.HOUR_OF_DAY);
        int minutoActual = c.get(Calendar.MINUTE);

        // 2. Crear el listener para cuando el usuario "ACEPTA" (OnTimeSet)
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Esta lógica se ejecuta SÓLO cuando el usuario pulsa "ACEPTAR"

                // Formateamos la hora para que tenga 2 dígitos (ej: "08:05")
                String horaFormateada = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                String horaSeleccionada = "Hora: " + horaFormateada;

                // Mostramos el Toast personalizado
                mostrarToastPersonalizado(horaSeleccionada);
            }
        };

        // 3. Crear y mostrar el TimePickerDialog
        // Pasamos el contexto, el listener, la hora, los minutos y si es formato 24h (true)
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                timeSetListener,
                horaActual,
                minutoActual,
                true // true para formato 24h, false para AM/PM
        );

        // 4. Mostrar el diálogo
        timePickerDialog.show();
    }


    /**
     * -----------------------------------------------------------
     * MÉTODO PARA MOSTRAR EL TOAST PERSONALIZADO
     * -----------------------------------------------------------
     * @param mensaje El texto que se mostrará en el Toast
     */
    private void mostrarToastPersonalizado(String mensaje) {
        // 1. Obtener el LayoutInflater
        LayoutInflater inflater = getLayoutInflater();

        // 2. Inflar el layout (layout_toast.xml) que creamos en el Paso 2
        // R.id.toast_root es el ID del LinearLayout raíz en layout_toast.xml
        View layout = inflater.inflate(R.layout.layout_toast,
                findViewById(R.id.toast_root));

        // 3. Encontrar el TextView dentro de nuestro layout inflado
        TextView tvMensaje = layout.findViewById(R.id.tvToastMensaje);

        // 4. Establecer el mensaje
        tvMensaje.setText(mensaje);

        // 5. Crear y configurar el Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0); // Posición (opcional)
        toast.setDuration(Toast.LENGTH_LONG); // Duración
        toast.setView(layout); // ¡Asignamos nuestro layout!

        // 6. Mostrar el Toast
        toast.show();
    }
}

