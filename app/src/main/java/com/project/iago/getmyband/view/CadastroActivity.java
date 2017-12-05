package com.project.iago.getmyband.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.iago.getmyband.model.Musico;
import com.project.iago.getmyband.R;
import com.project.iago.getmyband.dao.DaoArtist;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    Musico m = new Musico();
    DaoArtist dao = new DaoArtist(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_musico);


        final Button btnSalvar     = findViewById(R.id.btnSalvarMusico);
        final EditText txtNome     = findViewById(R.id.musico_nome);
        final EditText txtIdade    = findViewById(R.id.musico_idade);
        final EditText txtTelefone = findViewById(R.id.musico_telefone);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.setNome(txtNome.getText().toString());
                m.setIdade(Integer.parseInt(txtIdade.getText().toString()));
                m.setTelefone(Integer.parseInt(txtTelefone.getText().toString()));

             //   long result = dao.inserirArtist(m);

             //   if(result != -1){
            //        Toast.makeText(getApplicationContext(),"Musico Inserido com sucesso.", Toast.LENGTH_LONG).show();
           //         finish();
           //     }

            }
        });

        DaoArtist DAO = new DaoArtist(this);
      //  List<Musico> lista = DAO.ListarMusico();
     //   for(Musico m: lista){
    //        Log.d("IAGO", "Nome aqui ->>"+m.getNome().toString());
   //     }
    }
}
