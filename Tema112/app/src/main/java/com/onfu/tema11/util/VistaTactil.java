package com.onfu.tema11.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;

public class VistaTactil extends View {
    private Paint pincelTexto;
    private Paint pincelCirculo;
    private Paint[] pincelesColores;
    private String infoEventos = "Toca la pantalla con varios dedos";
    private MotionEvent eventoActual;
    private HashMap<Integer, Path> trayectorias = new HashMap<>();
    private int[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.LTGRAY};
    private Context ctx;

    public VistaTactil(Context context) {
        super(context);
        this.ctx = context;
        configurarPinceles();
    }

    private void configurarPinceles() {
        pincelTexto = new Paint();
        pincelTexto.setColor(Color.WHITE);
        pincelTexto.setTextSize(40f);
        pincelTexto.setAntiAlias(true);
        pincelCirculo = new Paint();
        pincelCirculo.setColor(Color.CYAN);
        pincelCirculo.setStyle(Paint.Style.STROKE);
        pincelCirculo.setStrokeWidth(5f);
        pincelesColores = new Paint[colores.length];
        for (int i = 0; i < colores.length; i++) {
            pincelesColores[i] = new Paint();
            pincelesColores[i].setColor(colores[i]);
            pincelesColores[i].setStyle(Paint.Style.STROKE);
            pincelesColores[i].setStrokeWidth(7f);
            pincelesColores[i].setAntiAlias(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventoActual = event;
        int accion = event.getActionMasked();
        int idx = event.getActionIndex();
        int id = event.getPointerId(idx);

        switch (accion) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                infoEventos = "Dedo detectado";
                vibrar();
                Path path = new Path();
                path.moveTo(event.getX(idx), event.getY(idx));
                trayectorias.put(id, path);
                break;
            case MotionEvent.ACTION_MOVE:
                infoEventos = "Desplazando...";
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pid = event.getPointerId(i);
                    Path p = trayectorias.get(pid);
                    if (p != null) {
                        p.lineTo(event.getX(i), event.getY(i));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                infoEventos = "Dedo levantado";
                trayectorias.remove(id);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (eventoActual == null) {
            canvas.drawText(infoEventos, 50, 100, pincelTexto);
            return;
        }
        int punteros = eventoActual.getPointerCount();
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        if (punteros == 2) {
            float x0 = eventoActual.getX(0);
            float y0 = eventoActual.getY(0);
            float x1 = eventoActual.getX(1);
            float y1 = eventoActual.getY(1);
            float distancia = (float) Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
            canvas.drawText("Distancia: " + (int) distancia + " px", cx - 100, cy, pincelTexto);
            // Línea entre los dos dedos
            Paint linea = new Paint();
            linea.setColor(Color.CYAN);
            linea.setStrokeWidth(4f);
            canvas.drawLine(x0, y0, x1, y1, linea);
        }
        for (int i = 0; i < punteros; i++) {
            float x = eventoActual.getX(i);
            float y = eventoActual.getY(i);
            int id = eventoActual.getPointerId(i);
            int colorIdx = id % colores.length;
            // Trayectoria
            Path path = trayectorias.get(id);
            if (path != null) {
                canvas.drawPath(path, pincelesColores[colorIdx]);
            }
            // Círculo
            canvas.drawCircle(x, y, 100, pincelesColores[colorIdx]);
            // Info
            canvas.drawText("ID: " + id, x + 120, y - 40, pincelTexto);
            canvas.drawText("X: " + (int) x + " Y: " + (int) y, x + 120, y, pincelTexto);
            canvas.drawText("Acción: " + infoEventos, x + 120, y + 40, pincelTexto);
        }
    }

    private void vibrar() {
        try {
            Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
            }
        } catch (Exception ignored) {}
    }
}
