package com.example.archer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    Button Yuddho,Prey;
    TextView f_nmber,p_nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        Yuddho=(Button)findViewById(R.id.yuddho);
        Prey=(Button)findViewById(R.id.prey);
        Yuddho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),War.class);
                startActivity(it);
                finish();
            }
        });

        Prey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        f_nmber=(TextView)findViewById(R.id.fitness);
        p_nm=(TextView)findViewById(R.id.prey_number);
        f_nmber.setText("তোমার ফিটনেস খুব একটা ভালো না !");
        p_nm.setText("তোমার পছন্দটি সিলেক্ট করো "+MainActivity.score+" টি ।");
    }
    public void OnBackPressed()
    {
        finish();
    }
}