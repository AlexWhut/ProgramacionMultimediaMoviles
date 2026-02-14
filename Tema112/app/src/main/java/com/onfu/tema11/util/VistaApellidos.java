package com.onfu.tema11.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class VistaApellidos extends View {
    private Paint pincel;
    private Typeface fuenteGodOfWar;
    private Typeface fuenteJmh;
    private Typeface fuenteVogue;

    public VistaApellidos(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public VistaApellidos(Context context) {
        super(context);
        init(context);
    }
    private void init(Context context) {
        pincel = new Paint();
        pincel.setAntiAlias(true);
        try {
            fuenteGodOfWar = Typeface.createFromAsset(context.getAssets(), "fonts/godofwar.ttf");
        } catch (Exception e) {
            fuenteGodOfWar = Typeface.DEFAULT;
        }
        try {
            fuenteJmh = Typeface.createFromAsset(context.getAssets(), "fonts/jmh.ttf");
        } catch (Exception e) {
            fuenteJmh = Typeface.DEFAULT;
        }
        try {
            fuenteVogue = Typeface.createFromAsset(context.getAssets(), "fonts/vogue.ttf");
        } catch (Exception e) {
            fuenteVogue = Typeface.DEFAULT;
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String texto = "GARCÍA LÓPEZ";
        canvas.drawColor(Color.parseColor("#121C2B")); // Fondo azul oscuro
        // 1. Normal (God of War)
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(55);
        pincel.setTextAlign(Paint.Align.LEFT);
        pincel.setTextSkewX(0);
        pincel.setTextScaleX(1);
        pincel.setTypeface(fuenteGodOfWar);
        canvas.drawText("1. Normal: " + texto, 40, 100, pincel);
        // 2. Grande y rojo (JMH)
        pincel.setColor(Color.RED);
        pincel.setTextSize(50);
        pincel.setTypeface(fuenteJmh);
        canvas.drawText("2. Grande: " + texto, 40, 220, pincel);
        // 3. Inclinada y azul (Vogue)
        pincel.setColor(Color.BLUE);
        pincel.setTextSize(40);
        pincel.setTextSkewX(-0.5f);
        pincel.setTypeface(fuenteVogue);
        canvas.drawText("3. Inclinado: " + texto, 40, 320, pincel);
        pincel.setTextSkewX(0);
        // 4. Estirado y verde (God of War)
        pincel.setColor(Color.parseColor("#00C853"));
        pincel.setTextSize(25);
        pincel.setTextScaleX(2.0f);
        pincel.setTypeface(fuenteGodOfWar);
        canvas.drawText("4. Estirado: " + texto, 40, 420, pincel);
        pincel.setTextScaleX(1.0f);
        // 5. Centrado, magenta y fuente JMH
        pincel.setColor(Color.MAGENTA);
        pincel.setTextSize(25);
        pincel.setTextAlign(Paint.Align.CENTER);
        pincel.setTypeface(fuenteJmh);
        float centroX = canvas.getWidth() / 2f;
        canvas.drawText("5. " + texto + " (Centrado)", centroX, 540, pincel);
        pincel.setTextAlign(Paint.Align.LEFT);
        pincel.setTypeface(Typeface.DEFAULT);
    }
}
