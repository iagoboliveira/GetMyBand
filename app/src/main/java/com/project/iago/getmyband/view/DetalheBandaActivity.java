package com.project.iago.getmyband.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.project.iago.getmyband.R;
import com.project.iago.getmyband.model.Banda;

public class DetalheBandaActivity extends AppCompatActivity {

    public  static final String BANDA_SELECIONADA = "bandaSelecionada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_banda);

        Banda banda = (Banda)getIntent().getSerializableExtra(BANDA_SELECIONADA);

        TextView txt = (TextView)findViewById(R.id.textView);
        txt.setText(banda.getNome() +">>"+banda.getGenero());
    }
}
