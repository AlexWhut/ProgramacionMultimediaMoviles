package com.onfu.tema11;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActividad11_9 extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap miMapa;
    private LatLng domicilio = new LatLng(40.4153, -3.6844); // Ejemplo: Retiro
    private LatLng centroEstudios = new LatLng(40.4169, -3.7035); // Ejemplo: Puerta del Sol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad11_9);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        Button btnSat = findViewById(R.id.btnSat);
        Button btnNorm = findViewById(R.id.btnNorm);
        Button btnHyb = findViewById(R.id.btnHyb);
        btnSat.setOnClickListener(v -> {
            if (miMapa != null) miMapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        });
        btnNorm.setOnClickListener(v -> {
            if (miMapa != null) miMapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        });
        btnHyb.setOnClickListener(v -> {
            if (miMapa != null) miMapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        miMapa = googleMap;
        miMapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Marcador del Centro de Estudios (rojo)
        miMapa.addMarker(new MarkerOptions()
                .position(centroEstudios)
                .title("Mi Centro de Estudios")
                .snippet("Aprender Android es genial"));
        // Marcador del Domicilio (azul)
        miMapa.addMarker(new MarkerOptions()
                .position(domicilio)
                .title("Mi Casa")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        // Polyline roja entre ambos
        miMapa.addPolyline(new PolylineOptions().add(domicilio, centroEstudios).color(Color.RED).width(8f));
        // Vista general de la ciudad
        LatLng madrid = new LatLng(40.4168, -3.7038);
        miMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 12f));
        // Animar cámara al domicilio tras 2 segundos
        new Handler().postDelayed(() ->
                miMapa.animateCamera(CameraUpdateFactory.newLatLngZoom(domicilio, 15f), 2000, null), 2000);
        // UI extra
        miMapa.getUiSettings().setZoomControlsEnabled(true);
    }
}
