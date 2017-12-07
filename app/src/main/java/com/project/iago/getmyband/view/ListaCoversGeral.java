package com.project.iago.getmyband.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.project.iago.getmyband.R;

import java.util.ArrayList;
import java.util.List;

public class ListaCoversGeral extends AppCompatActivity  {
    private static final String TAG = ListaCoversGeral.class.getSimpleName();
    private String email_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_covers_geral);
        //email_user = "";
      //  email_user = getIntent().getStringExtra("EMAIL");
       // Log.i("MyBand", TAG+".onCreate() - INICIO email: "+email_user);
        TextView textViewBanda;
        textViewBanda = findViewById(R.id.nomeDasBandas);
        List<String> bandas = new ArrayList<String>();
        bandas.add("Red hot");
        bandas.add("Red Aqua");
        bandas.add("Red Amarelo");

        ListView listView = findViewById(R.id.listGeralCover);

        ArrayAdapter<String> adapterBandas = new ArrayAdapter<String>(
                getApplicationContext(), R.layout.item_list_cover_geral, bandas);

        listView.setAdapter(adapterBandas);

    }
}
