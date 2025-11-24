package com.example.preferenciasdialogosnotificaciones;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificacionesActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CANAL_ID = "canal_ID";
    private static final int NOTIFICACION_ID = 1;

    private NotificationManager notificador;
    private Button btnPublica, btnFullScreen, btnPrivada, btnSecreta, btnCancelarUna, btnCancelarTodas, btnPruebaSimple;

    // --- ¡NUEVO! ---
    // Launcher para solicitar el permiso de notificación
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // El usuario concedió el permiso
                    Toast.makeText(this, "Permiso concedido. ¡Pulsa el botón de nuevo!", Toast.LENGTH_LONG).show();
                } else {
                    // El usuario denegó el permiso
                    Toast.makeText(this, "Permiso de notificación denegado", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificaciones_layout);

        notificador = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // ¡Importante! Creamos el canal de notificación
        crearCanalNotificacion();

        // Referenciar Vistas
        btnPublica = findViewById(R.id.btnNotificacionPublica);
        btnFullScreen = findViewById(R.id.btnNotificacionFullScreen);
        btnPrivada = findViewById(R.id.btnNotificacionPrivada);
        btnSecreta = findViewById(R.id.btnNotificacionSecreta);
        btnCancelarUna = findViewById(R.id.btnCancelarUna);
        btnCancelarTodas = findViewById(R.id.btnCancelarTodas);
        btnPruebaSimple = findViewById(R.id.btnPruebaSimple);

        // Asignar Listeners
        btnPublica.setOnClickListener(this);
        btnFullScreen.setOnClickListener(this);
        btnPrivada.setOnClickListener(this);
        btnSecreta.setOnClickListener(this);
        btnCancelarUna.setOnClickListener(this);
        btnCancelarTodas.setOnClickListener(this);
        btnPruebaSimple.setOnClickListener(this);
    }
    // --- ¡NUEVO MÉTODO DE PRUEBA! ---
    /**
     * Esta es una notificación de prueba MÍNIMA y A PRUEBA DE FALLOS.
     * Si esto no funciona, el problema está en la configuración del dispositivo.
     */
    private void lanzarNotificacionPrueba() {

        // 1. Usamos el icono de la app (R.mipmap.ic_launcher)
        // Este es el cambio MÁS IMPORTANTE. Este icono DEBE existir.
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CANAL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher) // <-- ¡CAMBIO CRÍTICO!
                        .setContentTitle("Prueba Simple")
                        .setContentText("Si ves esto, el canal y el permiso funcionan.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

        // 2. Sin PendingIntent ni BackStack, para simplificar.
        // Al pulsarla, solo se cerrará (por setAutoCancel).

        // 3. Lanzamos con un ID DIFERENTE (ej. 2)
        notificador.notify(2, builder.build());

        Toast.makeText(this, "Lanzando notificación de PRUEBA (ID 2)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnPruebaSimple) {
            // Comprobamos permiso ANTES de lanzar
            if (!tenemosPermisoNotificacion()) {
                solicitarPermisoNotificacion();
                return;
            }
            // Lanzamos la función de prueba
            lanzarNotificacionPrueba();
            return; // Salimos del onClick
        }
        // Lógica de cancelación (no requiere permisos)
        if (id == R.id.btnCancelarUna) {
            notificador.cancel(NOTIFICACION_ID);
            return;
        } else if (id == R.id.btnCancelarTodas) {
            notificador.cancelAll();
            return;
        }

        // --- ¡NUEVO! ---
        // Verificación de permiso antes de lanzar cualquier notificación
        if (!tenemosPermisoNotificacion()) {
            // Si no tenemos permiso, lo solicitamos y salimos
            solicitarPermisoNotificacion();
            return; // El usuario tendrá que volver a pulsar el botón
        }

        // Si SÍ tenemos permiso (o la API es < 33), continuamos...
        if (id == R.id.btnNotificacionPublica) {
            lanzarNotificacion(NotificationCompat.VISIBILITY_PUBLIC, false);
        } else if (id == R.id.btnNotificacionFullScreen) {
            lanzarNotificacion(NotificationCompat.VISIBILITY_PUBLIC, true);
        } else if (id == R.id.btnNotificacionPrivada) {
            lanzarNotificacion(NotificationCompat.VISIBILITY_PRIVATE, false);
        } else if (id == R.id.btnNotificacionSecreta) {
            lanzarNotificacion(NotificationCompat.VISIBILITY_SECRET, false);
        }
    }

    // --- ¡NUEVO! ---
    // Método para comprobar si ya tenemos el permiso
    private boolean tenemosPermisoNotificacion() {
        // Si la versión es < API 33 (Tiramisu), el permiso se concede automáticamente
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            return true;
        }
        // Si es API 33+, comprobamos si el permiso está concedido
        return ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED;
    }

    // --- ¡NUEVO! ---
    // Método para lanzar el diálogo de solicitud de permiso
    private void solicitarPermisoNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Lanzamos el diálogo de permiso
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }

    /**
     * Crea el Canal de Notificación.
     * (¡MODIFICADO! Se cambia a IMPORTANCE_HIGH para asegurar visibilidad)
     */
    private void crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nombre = "Canal Ejemplo";
            String descripcion = "Canal para notificaciones de ejemplo";

            // --- CAMBIO AQUÍ ---
            // Usamos HIGH para que las notificaciones "Heads-Up" (FullScreen) funcionen
            int importancia = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CANAL_ID, nombre, importancia);
            channel.setDescription(descripcion);

            if (notificador != null) {
                notificador.createNotificationChannel(channel);
            }
        }
    }

    /**
     * MÉTODO PRINCIPAL CORREGIDO (basado en el DOCX pero con el icono válido)
     *
     * Este método SÍ implementa todas las opciones del documento:
     * - Visibilidad (Pública, Privada, Secreta) [cite: 67, 68, 69]
     * - FullScreenIntent (Heads-Up) [cite: 64]
     * - Pila de Retroceso (BackStack) [cite: 46, 48]
     *
     * @param visibilidad (VISIBILITY_PUBLIC, VISIBILITY_PRIVATE, VISIBILITY_SECRET)
     * @param usarFullScreen (true si debe usar setFullScreenIntent)
     */
    private void lanzarNotificacion(int visibilidad, boolean usarFullScreen) {

        // --- 1. Constructor del NotificationCompat ---
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CANAL_ID);

        // --- ¡¡LA CORRECCIÓN ESTÁ AQUÍ!! ---
        // Se usa un icono VÁLIDO de la app (ic_launcher) y no
        // el icono del sistema (android.R.drawable.stat_sys_warning)
        builder.setSmallIcon(R.mipmap.ic_launcher);


        // --- 2. Icono Grande (Código del documento, mejorado) ---
        // El código del documento [cite: 19] es complejo; esta forma es más segura
        // para obtener el Bitmap de ic_launcher.
        android.graphics.Bitmap largeIcon = android.graphics.BitmapFactory.decodeResource(
                getResources(),
                R.mipmap.ic_launcher
        );
        builder.setLargeIcon(largeIcon); // [cite: 19, 27]

        // --- 3. Propiedades del Documento ---
        builder.setContentTitle("MENSAJE DE ALERTA"); // [cite: 21, 28]
        builder.setContentText("Ejemplo de notificación."); // [cite: 22, 29]
        builder.setTicker("AVISO DE NOTIFICACIÓN"); // [cite: 23, 26]
        builder.setVisibility(visibilidad); // [cite: 67, 68, 69]
        builder.setPriority(NotificationCompat.PRIORITY_HIGH); // (Necesario para FullScreen)

        // --- 4. Establecer la actividad de destino (BackStack) ---
        // (Código tal cual el documento, adaptado a nuestras clases)
        Intent intent = new Intent(this, Segunda.class); // [cite: 34]
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this); // [cite: 46]

        // El documento usa "Notificacion.class"[cite: 47], nosotros usamos la nuestra
        stackBuilder.addParentStack(NotificacionesActivity.class);

        stackBuilder.addNextIntent(intent); // [cite: 48]

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        ); // [cite: 51, 52]

        builder.setContentIntent(resultPendingIntent); // [cite: 53]

        // --- 5. Construir y lanzar la notificación ---
        builder.setAutoCancel(true); // [cite: 57]

        // Opción de FullScreen (Heads-Up)
        if (usarFullScreen) {
            builder.setFullScreenIntent(resultPendingIntent, true); // [cite: 64]
        }

        // Lanzar la notificación (requiere permiso)
        notificador.notify(NOTIFICACION_ID, builder.build()); // [cite: 58]
    }
}

