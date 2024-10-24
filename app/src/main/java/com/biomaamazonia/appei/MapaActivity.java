package com.biomaamazonia.appei;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Obtenha o SupportMapFragment e notifique quando o mapa estiver pronto
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Marcador na Floresta Amazônica
        LatLng amazonia = new LatLng(-3.4653, -62.2159);
        mMap.addMarker(new MarkerOptions().position(amazonia).title("Floresta Amazônica").snippet("Coração da biodiversidade mundial"));

        // Área protegida do Parque Nacional do Jaú
        LatLng parqueJau = new LatLng(-1.8371, -61.6136);
        mMap.addMarker(new MarkerOptions().position(parqueJau).title("Parque Nacional do Jaú").snippet("Uma das maiores áreas protegidas da Amazônia"));

        // Área ameaçada pelo desmatamento em Rondônia
        LatLng rondoniaDesmatamento = new LatLng(-11.5057, -63.5806);
        mMap.addMarker(new MarkerOptions().position(rondoniaDesmatamento).title("Área de desmatamento em Rondônia").snippet("Área crítica de desmatamento"));

        // Mover a câmera para a Amazônia com um zoom ajustado
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(amazonia, 5));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Exibir uma mensagem Toast com o título do marcador e o snippet
                Toast.makeText(MapaActivity.this, marker.getTitle() + ": " + marker.getSnippet(), Toast.LENGTH_LONG).show();
                return false;  // Retornar false permite que o comportamento padrão do clique no marcador também ocorra
            }
        });

        // Adicionar polígono sobre o Parque Nacional do Jaú
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(new LatLng(-1.50, -61.00),
                        new LatLng(-1.50, -62.00),
                        new LatLng(-2.00, -62.00),
                        new LatLng(-2.00, -61.00))
                .strokeColor(Color.GREEN)  // Cor da borda do polígono
                .fillColor(0x5500FF00);  // Cor de preenchimento com transparência

        mMap.addPolygon(polygonOptions);

        // Adicionar polígono sobre uma área de desmatamento em Rondônia
        PolygonOptions desmatamentoPolygon = new PolygonOptions()
                .add(new LatLng(-11.00, -63.00),
                        new LatLng(-11.00, -64.00),
                        new LatLng(-12.00, -64.00),
                        new LatLng(-12.00, -63.00))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);  // Vermelho transparente para indicar área de desmatamento

        mMap.addPolygon(desmatamentoPolygon);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(amazonia);
        builder.include(parqueJau);
        builder.include(rondoniaDesmatamento);

// Ajusta a câmera para englobar todos os marcadores
        LatLngBounds bounds = builder.build();
        int padding = 100;  // Espaço extra ao redor dos pontos
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));




    }

}
