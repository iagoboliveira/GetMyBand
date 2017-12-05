package com.project.iago.getmyband;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.iago.getmyband.model.Banda;
import com.project.iago.getmyband.view.CadastroActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Banda> bandass =  new ArrayList<Banda>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNovo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_cadastro = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(it_cadastro);
            }
        });


//        /*
//        bandass.add(new Banda("Metalica", "Rock"));
//        bandass.add(new Banda("Ponto de Equilibrio", "Reggae"));
//
//        ListView listViewBandas = (ListView)findViewById(R.id.listaBandas);
//
//        /**ArrayAdapter<Banda> adapterBandas = new ArrayAdapter<Banda>(
//                this, R.layout.item_da_lista, bandas);
//        BandaAdapter adapter = new BandaAdapter(this, bandass);
//        listViewBandas.setAdapter(adapter);
//
//        // Clicando no item da lista deverá executar o comando abaixo
//        listViewBandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Banda banda = bandass.get(i);
//                Toast.makeText(MainActivity.this, banda.toString(), Toast.LENGTH_SHORT).show();
//
//                Intent it = new Intent(MainActivity.this, DetalheBandaActivity.class);
//
//                it.putExtra(DetalheBandaActivity.BANDA_SELECIONADA, banda);
//                startActivity(it);
//            }
//        });
//        */
    }


}
