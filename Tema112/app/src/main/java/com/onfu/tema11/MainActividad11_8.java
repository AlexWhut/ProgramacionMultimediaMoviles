package com.onfu.tema11;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActividad11_8 extends AppCompatActivity implements LocationListener {
    private TextView lblLatitud, lblLongitud, lblPrecision;
    private Switch switchProveedor;
    private LocationManager locationManager;
    private static final int CODIGO_PETICION_PERMISOS = 101;
    private String proveedorActual = LocationManager.NETWORK_PROVIDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_8);
        lblLatitud = findViewById(R.id.lblLatitud);
        lblLongitud = findViewById(R.id.lblLongitud);
        lblPrecision = findViewById(R.id.lblPrecision);
        switchProveedor = findViewById(R.id.switchProveedor);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        switchProveedor.setOnCheckedChangeListener((buttonView, isChecked) -> {
            proveedorActual = isChecked ? LocationManager.GPS_PROVIDER : LocationManager.NETWORK_PROVIDER;
            detenerRastreo();
            verificarPermisosYComenzar();
        });
        verificarPermisosYComenzar();
    }

    private void verificarPermisosYComenzar() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    CODIGO_PETICION_PERMISOS);
        } else {
            iniciarRastreo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PETICION_PERMISOS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarRastreo();
            } else {
                Toast.makeText(this, "Se necesitan permisos para usar el GPS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void iniciarRastreo() {
        try {
            locationManager.requestLocationUpdates(
                    proveedorActual,
                    5000,
                    5,
                    this
            );
            String texto = proveedorActual.equals(LocationManager.GPS_PROVIDER) ? "GPS" : "Red";
            Toast.makeText(this, "Rastreo iniciado con " + texto, Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void detenerRastreo() {
        try {
            locationManager.removeUpdates(this);
        } catch (SecurityException ignored) {}
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lblLatitud.setText("Latitud: " + location.getLatitude());
        lblLongitud.setText("Longitud: " + location.getLongitude());
        lblPrecision.setText("Precisión: +/- " + location.getAccuracy() + " metros");
        // Personalización: Geocodificación inversa
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> direcciones = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (direcciones != null && !direcciones.isEmpty()) {
                Address dir = direcciones.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i <= dir.getMaxAddressLineIndex(); i++) {
                    sb.append(dir.getAddressLine(i)).append("\n");
                }
                lblPrecision.setText(lblPrecision.getText() + "\n" + sb.toString());
            }
        } catch (IOException ignored) {}
        // Personalización: Velocímetro
        if (location.hasSpeed()) {
            float velocidad = location.getSpeed() * 3.6f;
            lblPrecision.setText(lblPrecision.getText() + String.format("\nVelocidad: %.1f km/h", velocidad));
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(this, "Proveedor habilitado: " + provider, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, "Por favor, activa la ubicación", Toast.LENGTH_LONG).show();
    }
}
