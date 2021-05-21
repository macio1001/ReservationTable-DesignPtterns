package com.example.reservationtabledesignpatterns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MenuRestauracjaDesignPatternsActivity extends AppCompatActivity {

    ImageView imageLogo;
    Button buttonRezerwacje,buttonSkaner,buttonStoliki,buttonWuszykiwanie;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menurestauracjadesignpatterns);

        imageLogo=findViewById(R.id.logoimageView);
        buttonRezerwacje=findViewById(R.id.rezerwacjebutton);
        buttonSkaner=findViewById(R.id.skanerbutton);
        buttonStoliki=findViewById(R.id.stolikibutton);
        buttonWuszykiwanie=findViewById(R.id.wyszukiwaniebutton);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonRezerwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,HistoriaDesignPatternsActivity.class));
            }
        });

        buttonSkaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,SkanerDesignPatternsActivity.class));
            }
        });

        buttonWuszykiwanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,WyszukiwanieRezerwacjiDesignPatternsActivity.class));
            }
        });

        buttonStoliki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,StolikiOffDesignPatternsActivity.class));
            }
        });
    }

    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenuRestauracjaDesignPatternsActivity.this,LogowanieDesignPatternsActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
