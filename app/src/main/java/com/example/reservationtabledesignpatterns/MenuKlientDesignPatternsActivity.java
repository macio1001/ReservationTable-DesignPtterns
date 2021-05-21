package com.example.reservationtabledesignpatterns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MenuKlientDesignPatternsActivity extends AppCompatActivity {

    ImageView imageLogo;
    Button buttonHistoria,buttonRezerwuj;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuklientdesignpatterns);

        imageLogo=findViewById(R.id.logoimageView);
        buttonHistoria=findViewById(R.id.historiabutton);
        buttonRezerwuj=findViewById(R.id.rezerwujbutton);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuKlientDesignPatternsActivity.this,HistoriaUzytkownikDesignPatternsActivity.class);
                startActivity(intent);
            }
        });

        buttonRezerwuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuKlientDesignPatternsActivity.this,RezerwujDesignPatternsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenuKlientDesignPatternsActivity.this,LogowanieDesignPatternsActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
