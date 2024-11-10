package com.biomaamazonia.appei;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Obtenha o SupportMapFragment e notifique quando o mapa estiver pronto
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Erro ao carregar o mapa", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Marcador na Floresta Amazônica
        LatLng amazonia = new LatLng(-3.4653, -62.2159);
        googleMap.addMarker(new MarkerOptions().position(amazonia).title("Floresta Amazônica").snippet("Coração da biodiversidade mundial"));

        // Área protegida do Parque Nacional do Jaú
        LatLng parqueJau = new LatLng(-2.4720, -62.6326);
        googleMap.addMarker(new MarkerOptions().position(parqueJau).title("Parque Nacional do Jaú").snippet("Uma das maiores áreas protegidas da Amazônia"));

        // 1. Marcador para a Reserva Indígena Yanomami
        LatLng yanomami = new LatLng(3.0, -61.5);
        googleMap.addMarker(new MarkerOptions().position(yanomami)
                .title("Reserva Indígena Yanomami")
                .snippet("Localizada no norte de Roraima e sul do Amazonas. Maior reserva indígena do Brasil."));

// 2. Marcador para a Reserva Indígena Munduruku
        LatLng munduruku = new LatLng(-2.0, -55.5);
        googleMap.addMarker(new MarkerOptions().position(munduruku)
                .title("Reserva Indígena Munduruku")
                .snippet("Localizada no estado do Pará, habitat do povo Munduruku."));

// 3. Marcador para a Reserva Indígena Kayapo
        LatLng kayapo = new LatLng(-5.5, -52.5);
        googleMap.addMarker(new MarkerOptions().position(kayapo)
                .title("Reserva Indígena Kayapo")
                .snippet("Abriga o povo Kayapo, localizada no Pará e Mato Grosso."));

// 4. Marcador para a Reserva Indígena Xingu
        LatLng xingu = new LatLng(-11.0, -52.5);
        googleMap.addMarker(new MarkerOptions().position(xingu)
                .title("Reserva Indígena Xingu")
                .snippet("Abriga vários povos indígenas, localizada no Mato Grosso."));

// 5. Marcador para a Reserva Indígena Raposa Serra do Sol
        LatLng raposaSerraDoSol = new LatLng(3.5, -60.5);
        googleMap.addMarker(new MarkerOptions().position(raposaSerraDoSol)
                .title("Reserva Indígena Raposa Serra do Sol")
                .snippet("Localizada em Roraima, uma das maiores reservas do Brasil."));

// 6. Marcador para a Reserva Indígena Alto Rio Negro
        LatLng altoRioNegro = new LatLng(-0.5, -64.5);
        googleMap.addMarker(new MarkerOptions().position(altoRioNegro)
                .title("Reserva Indígena Alto Rio Negro")
                .snippet("Abriga vários povos indígenas no Amazonas."));

// 7. Marcador para a Reserva Indígena Karipuna
        LatLng karipuna = new LatLng(-1.0, -60.5);
        googleMap.addMarker(new MarkerOptions().position(karipuna)
                .title("Reserva Indígena Karipuna")
                .snippet("Localizada no Amazonas, lar do povo Karipuna."));

// 8. Marcador para a Reserva Indígena Tupi-Guarani
        LatLng tupiGuarani = new LatLng(-10.5, -56.5);
        googleMap.addMarker(new MarkerOptions().position(tupiGuarani)
                .title("Reserva Indígena Tupi-Guarani")
                .snippet("Localizada no norte de Mato Grosso."));

// 9. Marcador para a Reserva Indígena Baniwa e Corubu
        LatLng baniwaCorubu = new LatLng(-4.5, -66.0);
        googleMap.addMarker(new MarkerOptions().position(baniwaCorubu)
                .title("Reserva Indígena Baniwa e Corubu")
                .snippet("Localizada no Amazonas, lar dos povos Baniwa e Corubu."));

// 10. Marcador para a Reserva Indígena Tikuna
        LatLng tikuna = new LatLng(-3.5, -64.0);
        googleMap.addMarker(new MarkerOptions().position(tikuna)
                .title("Reserva Indígena Tikuna")
                .snippet("Localizada no Amazonas, lar do povo Tikuna."));
// 11. Marcador para a Reserva Indígena Arara
        LatLng arara = new LatLng(-3.0, -51.5);
        googleMap.addMarker(new MarkerOptions().position(arara)
                .title("Reserva Indígena Arara")
                .snippet("Localizada no Pará, lar do povo Arara."));

// 12. Marcador para a Reserva Indígena Araweté
        LatLng arawete = new LatLng(-3.5, -51.0);
        googleMap.addMarker(new MarkerOptions().position(arawete)
                .title("Reserva Indígena Araweté")
                .snippet("Localizada no Pará, habitat do povo Araweté."));

// 13. Marcador para a Reserva Indígena Xixuau-Xiparina
        LatLng xixuauXiparina = new LatLng(-4.5, -66.5);
        googleMap.addMarker(new MarkerOptions().position(xixuauXiparina)
                .title("Reserva Indígena Xixuau-Xiparina")
                .snippet("Localizada no Amazonas, lar dos povos Xixuau e Xiparina."));

// 14. Marcador para a Reserva Indígena Juma
        LatLng juma = new LatLng(-6.5, -62.0);
        googleMap.addMarker(new MarkerOptions().position(juma)
                .title("Reserva Indígena Juma")
                .snippet("Localizada no Amazonas, lar do povo Juma."));

// 15. Marcador para a Reserva Indígena Panará
        LatLng panara = new LatLng(-12.5, -55.5);
        googleMap.addMarker(new MarkerOptions().position(panara)
                .title("Reserva Indígena Panará")
                .snippet("Localizada no Mato Grosso, habitat do povo Panará."));

// 16. Marcador para a Reserva Indígena Uru-eu-wau-wau
        LatLng urueuwauwau = new LatLng(-10.5, -62.5);
        googleMap.addMarker(new MarkerOptions().position(urueuwauwau)
                .title("Reserva Indígena Uru-eu-wau-wau")
                .snippet("Localizada em Rondônia, lar do povo Uru-eu-wau-wau."));

// 17. Marcador para a Reserva Indígena Xavante
        LatLng xavante = new LatLng(-12.0, -53.5);
        googleMap.addMarker(new MarkerOptions().position(xavante)
                .title("Reserva Indígena Xavante")
                .snippet("Localizada no Mato Grosso, lar do povo Xavante."));


// 21. Marcador para a Reserva Indígena Xingu - Terra Indígena
        LatLng xinguTI = new LatLng(-11.0, -52.0);
        googleMap.addMarker(new MarkerOptions().position(xinguTI)
                .title("Reserva Indígena Xingu - Terra Indígena")
                .snippet("Localizada no Mato Grosso, lar de vários povos indígenas."));

// 22. Marcador para a Reserva Indígena Wai Wai
        LatLng waiwai = new LatLng(2.5, -61.5);
        googleMap.addMarker(new MarkerOptions().position(waiwai)
                .title("Reserva Indígena Wai Wai")
                .snippet("Localizada em Roraima, habitat do povo Wai Wai."));

// 23. Marcador para a Reserva Indígena Parakanã
        LatLng parakana = new LatLng(-4.5, -49.0);
        googleMap.addMarker(new MarkerOptions().position(parakana)
                .title("Reserva Indígena Parakanã")
                .snippet("Localizada no Pará, lar do povo Parakanã."));

        //Haziel, Atualização
        // Marcadores para Parques Nacionais

        LatLng parque1 = new LatLng(5.1032, -60.2681);
        googleMap.addMarker(new MarkerOptions().position(parque1).title("Parque Nacional Monte Roraima ").snippet("Descrição do Parque Nacional Monte Roraima, Localizada em Roraima.  "));

        LatLng parque2 = new LatLng(1.2679, -61.1443);
        googleMap.addMarker(new MarkerOptions().position(parque2).title("Parque Nacional Viruá ").snippet("Descrição do Parque Nacional Viruá, Localizada em Roraima."));

        LatLng parque3 = new LatLng(-2.4880, -60.8588);
        googleMap.addMarker(new MarkerOptions().position(parque3).title("Parque Nacional de Anavilhanas").snippet("Descrição do Parque Nacional de Anavilhanas, Localizada no Amazonas."));

        LatLng parque4 = new LatLng(-8.0548, -58.1211);
        googleMap.addMarker(new MarkerOptions().position(parque4).title("Parque Nacional Juruena").snippet("Descrição do Parque Nacional Juruena, localizada no Mato Grosso/Pará."));

        LatLng parque5 = new LatLng(1.8349, -52.9224);
        googleMap.addMarker(new MarkerOptions().position(parque5).title("Parque Nacional Montanha do Tumucumaque").snippet("Descrição do Parque Nacional Montanha do Tumucumaque, localizada no Amapá."));

        LatLng parque6 = new LatLng(2.0347, -50.4413);
        googleMap.addMarker(new MarkerOptions().position(parque6).title("Parque Nacional de Maracá-Jipioca").snippet("Descrição do Parque Nacional de Maracá-Jipioca, localizada no Amapá."));

        LatLng parque7 = new LatLng(-12.0297, -53.4052);
        googleMap.addMarker(new MarkerOptions().position(parque7).title("Parque Nacional de Xingu").snippet("Descrição do Parque Nacional de Xingu, localizada no Mato Grosso."));

        LatLng parque8 = new LatLng(-10.6281, -50.1748);
        googleMap.addMarker(new MarkerOptions().position(parque8).title("Parque Nacional de Araguaia").snippet("Descrição do Parque Nacional de Araguaia, localizada em Tocantins."));

        LatLng parque9 = new LatLng(0.1542, -65.8993);
        googleMap.addMarker(new MarkerOptions().position(parque9).title("Parque Nacional Pico da Neblina").snippet("Descrição do Parque Nacional Pico da Neblina, localizada no Amazonas."));

        LatLng parque10 = new LatLng(-8.1992, -73.5013);
        googleMap.addMarker(new MarkerOptions().position(parque10).title("Parque Nacional da Serra do Divisor").snippet("Descrição do Parque Nacional da Serra do Divisor, localizada no Acre."));

        LatLng parque11 = new LatLng(-5.8259, -62.6168);
        googleMap.addMarker(new MarkerOptions().position(parque11).title("Parque Nacional Nascentes do Lago Jari").snippet("Descrição do Parque Nacional Nascentes do Lago Jari, localizada no Amazonas."));

        LatLng parque12 = new LatLng(-8.5772, -64.4180);
        googleMap.addMarker(new MarkerOptions().position(parque12).title("Parque Nacional Mapinguari").snippet("Descrição do Parque  Nacional Mapinguari, localizada no Amazonas."));

        LatLng parque13 = new LatLng(-7.8656, -56.2088);
        googleMap.addMarker(new MarkerOptions().position(parque13).title("Parque Nacional Rio Novo").snippet("Descrição do Parque Nacional Rio Novo, localizada no Pará."));


        // Área ameaçada pelo desmatamento na Amazônia
        // Atualização de Haziel, pontos de queimadas
        LatLng rondoniaDesmatamento = new LatLng(-11.5057, -63.5806);
        googleMap.addMarker(new MarkerOptions().position(rondoniaDesmatamento).title("Área de Queimadas").snippet("Área crítica de Queimadas."));

        LatLng ponto0 = new LatLng(-7.7233, -72.3256);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto0)
                .title(" Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto1 = new LatLng(-7.6731, -70.6809);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto1)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto2 = new LatLng(-9.0140, -66.6903);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto2)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto3 = new LatLng(-17.6376, -57.2865);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto3)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto4 = new LatLng(-9.4477, -49.2955);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto4)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto5 = new LatLng(-8.7147, -50.7483);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto5)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto6 = new LatLng(-7.3427, -51.2926);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto6)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto7 = new LatLng(-6.0407, -50.2532);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto7)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto8 = new LatLng(-6.5925, -46.9454);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto8)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto9 = new LatLng(-4.9905, -44.9081);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto9)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto10 = new LatLng(-4.1470, -49.0533);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto10)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto11 = new LatLng(-3.0583, -45.1434);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto11)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto12 = new LatLng(-3.2300, -51.0384);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto12)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto13 = new LatLng(-1.6006, -47.5542);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto13)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto14 = new LatLng(-0.3965, -51.6427);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto14)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto15 = new LatLng(-1.6929, -54.9728);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto15)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto16 = new LatLng(-2.5778, -55.8879);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto16)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto17 = new LatLng(1.2984, -55.7894);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto17)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto18 = new LatLng(2.8022, -51.7033);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto18)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto19 = new LatLng(-2.1327, -52.8595);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto19)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));

        LatLng ponto20 = new LatLng(-3.1678, -53.7282);
        googleMap.addMarker(new MarkerOptions()
                .position(ponto20)
                .title("Área de Queimadas")
                .snippet("Área crítica de Queimadas."));


        // Mover a câmera para a Amazônia com um zoom ajustado
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(amazonia, 5));

        googleMap.setOnMarkerClickListener(marker -> {
            // Exibir uma mensagem Toast com o título do marcador e o snippet
            Toast.makeText(MapaActivity.this, marker.getTitle() + ": " + marker.getSnippet(), Toast.LENGTH_LONG).show();
            return false;  // Retornar false permite que o comportamento padrão do clique no marcador também ocorra
        });


        // Adicionar polígono sobre o Parque Nacional do Jaú
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(new LatLng(-2.6, -62.8),    // Ponto superior esquerdo
                new LatLng(-2.6, -62.4),    // Ponto superior direito
                new LatLng(-2.3, -62.4),    // Ponto inferior direito
                new LatLng(-2.3, -62.8))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00);
        googleMap.addPolygon(polygonOptions);

        // Adicionar polígono sobre as Reservas

        // 1. Reserva Indígena Yanomami (Roraima/Amazonas)
        PolygonOptions yanomamiPolygon = new PolygonOptions()
                .add(new LatLng(3.0, -61.5),
                        new LatLng(3.0, -59.5),
                        new LatLng(2.0, -59.5),
                        new LatLng(2.0, -61.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva

        googleMap.addPolygon(yanomamiPolygon);

// 2. Reserva Indígena Munduruku (Pará)
        PolygonOptions mundurukuPolygon = new PolygonOptions()
                .add(new LatLng(-2.0, -55.5),
                        new LatLng(-2.0, -54.5),
                        new LatLng(-3.0, -54.5),
                        new LatLng(-3.0, -55.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva

        googleMap.addPolygon(mundurukuPolygon);

// 3. Reserva Indígena Kayapo (Pará/Mato Grosso)

        PolygonOptions kayapoPolygon = new PolygonOptions()
                .add(new LatLng(-5.5, -52.5),
                        new LatLng(-5.5, -51.5),
                        new LatLng(-6.0, -51.5),
                        new LatLng(-6.0, -52.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(kayapoPolygon);

// 4. Reserva Indígena Xingu (Mato Grosso)

        PolygonOptions xinguPolygon = new PolygonOptions()
                .add(new LatLng(-11.0, -52.5),
                        new LatLng(-11.0, -52.0),
                        new LatLng(-11.5, -52.0),
                        new LatLng(-11.5, -52.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(xinguPolygon);

// 5. Reserva Indígena Raposa Serra do Sol (Roraima)
        PolygonOptions raposaSerraDoSolPolygon = new PolygonOptions()
                .add(new LatLng(3.5, -60.5),
                        new LatLng(3.5, -59.5),
                        new LatLng(4.5, -59.5),
                        new LatLng(4.5, -60.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(raposaSerraDoSolPolygon);

// 6. Reserva Indígena do Alto Rio Negro (Amazonas)

        PolygonOptions altoRioNegroPolygon = new PolygonOptions()
                .add(new LatLng(-0.5, -64.5),
                        new LatLng(-0.5, -64.0),
                        new LatLng(-1.0, -64.0),
                        new LatLng(-1.0, -64.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva

        googleMap.addPolygon(altoRioNegroPolygon);

// 7. Reserva Indígena Karipuna (Amazonas)

        PolygonOptions karipunaPolygon = new PolygonOptions()
                .add(new LatLng(-1.0, -60.5),
                        new LatLng(-1.0, -60.0),
                        new LatLng(-1.5, -60.0),
                        new LatLng(-1.5, -60.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva

        googleMap.addPolygon(karipunaPolygon);

// 8. Reserva Indígena Tupi-Guarani (Mato Grosso)
        PolygonOptions tupiGuaraniPolygon = new PolygonOptions()
                .add(new LatLng(-10.0, -56.5),
                        new LatLng(-10.0, -56.0),
                        new LatLng(-10.5, -56.0),
                        new LatLng(-10.5, -56.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(tupiGuaraniPolygon);

// 9. Reserva Indígena Xixuau-Xiparina (Amazonas)
        PolygonOptions xixuauXiparinaPolygon = new PolygonOptions()
                .add(new LatLng(-4.5, -66.5),
                        new LatLng(-4.5, -66.0),
                        new LatLng(-5.0, -66.0),
                        new LatLng(-5.0, -66.5))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(xixuauXiparinaPolygon);

// 10. Reserva Indígena Apurina (Amazonas)
        PolygonOptions apurinaPolygon = new PolygonOptions()
                .add(new LatLng(-4.0, -64.0),
                        new LatLng(-4.0, -63.5),
                        new LatLng(-4.5, -63.5),
                        new LatLng(-4.5, -64.0))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(apurinaPolygon);

// 11. Reserva Indígena Panará (Mato Grosso)
        PolygonOptions panaraPolygon = new PolygonOptions()
                .add(new LatLng(-12.5, -55.5),
                        new LatLng(-12.5, -55.0),
                        new LatLng(-13.0, -55.0),
                        new LatLng(-13.0, -55.5))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(panaraPolygon);

// 12. Reserva Indígena Uru-eu-wau-wau (Rondônia)
        PolygonOptions urueuwauwauPolygon = new PolygonOptions()
                .add(new LatLng(-10.5, -62.5),
                        new LatLng(-10.5, -62.0),
                        new LatLng(-11.0, -62.0),
                        new LatLng(-11.0, -62.5))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(urueuwauwauPolygon);

// 13. Reserva Indígena Xavante (Mato Grosso)
        PolygonOptions xavantePolygon = new PolygonOptions()
                .add(new LatLng(-12.0, -53.5),
                        new LatLng(-12.0, -53.0),
                        new LatLng(-12.5, -53.0),
                        new LatLng(-12.5, -53.5))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(xavantePolygon);

// 15. Reserva Indígena Juma (Amazonas)
        PolygonOptions jumaPolygon = new PolygonOptions()
                .add(new LatLng(-6.5, -62.0),
                        new LatLng(-6.5, -61.5),
                        new LatLng(-7.0, -61.5),
                        new LatLng(-7.0, -62.0))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva

        googleMap.addPolygon(jumaPolygon);



// 17. Reserva Indígena Baniwa e Corubu (Amazonas)
        PolygonOptions baniwaCorubuPolygon = new PolygonOptions()
                .add(new LatLng(-4.5, -66.0),
                        new LatLng(-4.5, -65.5),
                        new LatLng(-5.0, -65.5),
                        new LatLng(-5.0, -66.0))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(baniwaCorubuPolygon);

// 18. Reserva Indígena Tikuna (Amazonas)
        PolygonOptions tikunaPolygon = new PolygonOptions()
                .add(new LatLng(-3.5, -64.0),
                        new LatLng(-3.5, -63.5),
                        new LatLng(-4.0, -63.5),
                        new LatLng(-4.0, -64.0))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(tikunaPolygon);

// 19. Reserva Indígena Arara (Pará)
        PolygonOptions araraPolygon = new PolygonOptions()
                .add(new LatLng(-3.0, -51.5),
                        new LatLng(-3.0, -51.0),
                        new LatLng(-3.5, -51.0),
                        new LatLng(-3.5, -51.5))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(araraPolygon);

// 20. Reserva Indígena Araweté (Pará)
        PolygonOptions arawetePolygon = new PolygonOptions()
                .add(new LatLng(-3.5, -51.0),
                        new LatLng(-3.5, -50.5),
                        new LatLng(-4.0, -50.5),
                        new LatLng(-4.0, -51.0))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(arawetePolygon);

// 23. Reserva Indígena Xingu - Terra Indígena (Mato Grosso)
        PolygonOptions xinguTIpolygon = new PolygonOptions()
                .add(new LatLng(-11.0, -52.0),
                        new LatLng(-11.0, -51.5),
                        new LatLng(-11.5, -51.5),
                        new LatLng(-11.5, -52.0))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(xinguTIpolygon);

// 24. Reserva Indígena Wai Wai (Roraima)
        PolygonOptions waiwaiPolygon = new PolygonOptions()
                .add(new LatLng(2.5, -61.5),
                        new LatLng(2.5, -61.0),
                        new LatLng(2.0, -61.0),
                        new LatLng(2.0, -61.5))

                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(waiwaiPolygon);

// 25. Reserva Indígena Parakanã (Pará)
        PolygonOptions parakanaPolygon = new PolygonOptions()
                .add(new LatLng(-4.5, -49.0),
                        new LatLng(-4.5, -48.5),
                        new LatLng(-5.0, -48.5),
                        new LatLng(-5.0, -49.0))
                .strokeColor(Color.BLUE)
                .fillColor(0x550000FF); // Azul transparente para indicar a área da reserva
        googleMap.addPolygon(parakanaPolygon);


        // Adicionar polígono sobre uma área de desmatamento na amazônia
        PolygonOptions desmatamentoPolygon = new PolygonOptions()
                .add(new LatLng(-11.00, -63.00),
                        new LatLng(-11.00, -64.00),
                        new LatLng(-12.00, -64.00),
                        new LatLng(-12.00, -63.00))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(desmatamentoPolygon);

        //Ponto 0

        PolygonOptions ponto0Polygon = new PolygonOptions()
                .add(new LatLng(-7.7733, -72.3756),
                        new LatLng(-7.7733, -72.2756),
                        new LatLng(-7.6733, -72.2756),
                        new LatLng(-7.6733, -72.3756))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto0Polygon);

        //Ponto 1

        PolygonOptions ponto1Polygon = new PolygonOptions()
                .add(new LatLng(-7.7231, -70.7309),
                        new LatLng(-7.7231, -70.6309),
                        new LatLng(-7.6231, -70.6309),
                        new LatLng(-7.6231, -70.7309))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto1Polygon);

        // Ponto 2

        PolygonOptions ponto2Polygon = new PolygonOptions()
                .add(new LatLng(-9.0640, -66.7403),
                        new LatLng(-9.0640, -66.6403),
                        new LatLng(-8.9640, -66.6403),
                        new LatLng(-8.9640, -66.7403))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto2Polygon);

// Ponto 3


        PolygonOptions ponto3Polygon = new PolygonOptions()
                .add(new LatLng(-17.6876, -57.3365), new LatLng(-17.6876, -57.2365), new LatLng(-17.5876, -57.2365), new LatLng(-17.5876, -57.3365))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto3Polygon);

// Ponto 4


        PolygonOptions ponto4Polygon = new PolygonOptions()
                .add(new LatLng(-9.4977, -49.3455), new LatLng(-9.4977, -49.2455), new LatLng(-9.3977, -49.2455), new LatLng(-9.3977, -49.3455))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto4Polygon);

// Ponto 5


        PolygonOptions ponto5Polygon = new PolygonOptions()
                .add(new LatLng(-8.7647, -50.7983), new LatLng(-8.7647, -50.6983), new LatLng(-8.6647, -50.6983), new LatLng(-8.6647, -50.7983))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto5Polygon);

// Ponto 6


        PolygonOptions ponto6Polygon = new PolygonOptions()
                .add(new LatLng(-7.3927, -51.3426), new LatLng(-7.3927, -51.2426), new LatLng(-7.2927, -51.2426), new LatLng(-7.2927, -51.3426))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto6Polygon);

// Ponto 7

        PolygonOptions ponto7Polygon = new PolygonOptions()
                .add(new LatLng(-6.0907, -50.3032), new LatLng(-6.0907, -50.2032), new LatLng(-5.9907, -50.2032), new LatLng(-5.9907, -50.3032))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto7Polygon);

// Ponto 8


        PolygonOptions ponto8Polygon = new PolygonOptions()
                .add(new LatLng(-6.6425, -46.9954), new LatLng(-6.6425, -46.8954), new LatLng(-6.5425, -46.8954), new LatLng(-6.5425, -46.9954))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto8Polygon);

// Ponto 9


        PolygonOptions ponto9Polygon = new PolygonOptions()
                .add(new LatLng(-5.0405, -44.9581), new LatLng(-5.0405, -44.8581), new LatLng(-4.9405, -44.8581), new LatLng(-4.9405, -44.9581))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto9Polygon);

// Ponto 10


        PolygonOptions ponto10Polygon = new PolygonOptions()
                .add(new LatLng(-4.1970, -49.1033), new LatLng(-4.1970, -49.0033), new LatLng(-4.0970, -49.0033), new LatLng(-4.0970, -49.1033))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto10Polygon);

// Ponto 11

        PolygonOptions ponto11Polygon = new PolygonOptions()
                .add(new LatLng(-3.1083, -45.1934), new LatLng(-3.1083, -45.0934), new LatLng(-3.0083, -45.0934), new LatLng(-3.0083, -45.1934))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto11Polygon);

        // Ponto 12

        PolygonOptions ponto12Polygon = new PolygonOptions()
                .add(new LatLng(-3.2800, -51.0884), new LatLng(-3.2800, -50.9884), new LatLng(-3.1800, -50.9884), new LatLng(-3.1800, -51.0884))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto12Polygon);

// Ponto 13

        PolygonOptions ponto13Polygon = new PolygonOptions()
                .add(new LatLng(-1.6506, -47.6042), new LatLng(-1.6506, -47.5042), new LatLng(-1.5506, -47.5042), new LatLng(-1.5506, -47.6042))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto13Polygon);

// Ponto 14

        PolygonOptions ponto14Polygon = new PolygonOptions()
                .add(new LatLng(-0.4465, -51.6927), new LatLng(-0.4465, -51.5927), new LatLng(-0.3465, -51.5927), new LatLng(-0.3465, -51.6927))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto14Polygon);

// Ponto 15

        PolygonOptions ponto15Polygon = new PolygonOptions()
                .add(new LatLng(-1.7429, -55.0228), new LatLng(-1.7429, -54.9228), new LatLng(-1.6429, -54.9228), new LatLng(-1.6429, -55.0228))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto15Polygon);

// Ponto 16

        PolygonOptions ponto16Polygon = new PolygonOptions()
                .add(new LatLng(-2.6278, -55.9379), new LatLng(-2.6278, -55.8379), new LatLng(-2.5278, -55.8379), new LatLng(-2.5278, -55.9379))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto16Polygon);

// Ponto 17

        PolygonOptions ponto17Polygon = new PolygonOptions()
                .add(new LatLng(1.2484, -55.8394), new LatLng(1.2484, -55.7394), new LatLng(1.3484, -55.7394), new LatLng(1.3484, -55.8394))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto17Polygon);

// Ponto 18

        PolygonOptions ponto18Polygon = new PolygonOptions()
                .add(new LatLng(2.7522, -51.7533), new LatLng(2.7522, -51.6533), new LatLng(2.8522, -51.6533), new LatLng(2.8522, -51.7533))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto18Polygon);

// Ponto 19

        PolygonOptions ponto19Polygon = new PolygonOptions()
                .add(new LatLng(-2.1827, -52.9095), new LatLng(-2.1827, -52.8095), new LatLng(-2.0827, -52.8095), new LatLng(-2.0827, -52.9095))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto19Polygon);

// Ponto 20

        PolygonOptions ponto20Polygon = new PolygonOptions()
                .add(new LatLng(-3.2178, -53.7782), new LatLng(-3.2178, -53.6782), new LatLng(-3.1178, -53.6782), new LatLng(-3.1178, -53.7782))
                .strokeColor(Color.RED)
                .fillColor(0x55FF0000);
        googleMap.addPolygon(ponto20Polygon);


        // Adicionar polígono sobre os Parques Nacionais

        // Parque 1
        PolygonOptions parque1Polygon = new PolygonOptions()
                .add(new LatLng(5.2, -60.4),   // Ponto superior esquerdo
                        new LatLng(5.2, -60.1),   // Ponto superior direito
                        new LatLng(5.0, -60.1),   // Ponto inferior direito
                        new LatLng(5.0, -60.4))   // Ponto inferior esquerdo
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque1Polygon);

        // Parque 2
        PolygonOptions parque2Polygon = new PolygonOptions()
                .add(new LatLng(1.2679, -61.1443),
                        new LatLng(1.2679, -60.7),
                        new LatLng(0.8, -60.7),
                        new LatLng(0.8, -61.1443))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque2Polygon);

        // Parque 3
        PolygonOptions parque3Polygon = new PolygonOptions()
                .add(new LatLng(-2.4880, -60.8588),
                        new LatLng(-2.4880, -60.4),
                        new LatLng(-3.0, -60.4),
                        new LatLng(-3.0, -60.8588))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque3Polygon);

// Parque 4
        PolygonOptions parque4Polygon = new PolygonOptions()
                .add(new LatLng(-8.0548, -58.1211),
                        new LatLng(-8.0548, -57.6),
                        new LatLng(-8.5, -57.6),
                        new LatLng(-8.5, -58.1211))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque4Polygon);

// Parque 5
        PolygonOptions parque5Polygon = new PolygonOptions()
                .add(new LatLng(1.8349, -52.9224),
                        new LatLng(1.8349, -52.5),
                        new LatLng(1.4, -52.5),
                        new LatLng(1.4, -52.9224))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque5Polygon);

// Parque 6
        PolygonOptions parque6Polygon = new PolygonOptions()
                .add(new LatLng(2.0347, -50.4413),
                        new LatLng(2.0347, -50.0),
                        new LatLng(1.8, -50.0),
                        new LatLng(1.8, -50.4413))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque6Polygon);

// Parque 7
        PolygonOptions parque7Polygon = new PolygonOptions()
                .add(new LatLng(-12.0297, -53.4052),
                        new LatLng(-12.0297, -52.8),
                        new LatLng(-12.5, -52.8),
                        new LatLng(-12.5, -53.4052))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque7Polygon);

// Parque 8
        PolygonOptions parque8Polygon = new PolygonOptions()
                .add(new LatLng(-10.6281, -50.1748),
                        new LatLng(-10.6281, -49.7),
                        new LatLng(-11.0, -49.7),
                        new LatLng(-11.0, -50.1748))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque8Polygon);

// Parque 9
        PolygonOptions parque9Polygon = new PolygonOptions()
                .add(new LatLng(0.1542, -65.8993),
                        new LatLng(0.1542, -65.5),
                        new LatLng(0.0, -65.5),
                        new LatLng(0.0, -65.8993))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque9Polygon);

// Parque 10
        PolygonOptions parque10Polygon = new PolygonOptions()
                .add(new LatLng(-8.1992, -73.5013),
                        new LatLng(-8.1992, -73.0),
                        new LatLng(-8.7, -73.0),
                        new LatLng(-8.7, -73.5013))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque10Polygon);

// Parque 11
        PolygonOptions parque11Polygon = new PolygonOptions()
                .add(new LatLng(-5.8259, -62.6168),
                        new LatLng(-5.8259, -62.2),
                        new LatLng(-6.2, -62.2),
                        new LatLng(-6.2, -62.6168))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque11Polygon);

// Parque 12
        PolygonOptions parque12Polygon = new PolygonOptions()
                .add(new LatLng(-8.5772, -64.4180),
                        new LatLng(-8.5772, -64.0),
                        new LatLng(-8.8, -64.0),
                        new LatLng(-8.8, -64.4180))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque12Polygon);

// Parque 13
        PolygonOptions parque13Polygon = new PolygonOptions()
                .add(new LatLng(-7.8656, -56.2088),
                        new LatLng(-7.8656, -55.8),
                        new LatLng(-8.0, -55.8),
                        new LatLng(-8.0, -56.2088))
                .strokeColor(Color.GREEN)
                .fillColor(0x5500FF00); // Verde transparente
        googleMap.addPolygon(parque13Polygon);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(amazonia);
        builder.include(parqueJau);
        builder.include(rondoniaDesmatamento);

        // Ajusta a câmera para englobar todos os marcadores
        LatLngBounds bounds = builder.build();
        int padding = 100;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }
}
