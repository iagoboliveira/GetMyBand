package com.project.iago.getmyband.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.project.iago.getmyband.R;

public class MainUsersActivity extends AppCompatActivity {
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_users);



        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_cadastro = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(it_cadastro);
            }
        });
    }
}
