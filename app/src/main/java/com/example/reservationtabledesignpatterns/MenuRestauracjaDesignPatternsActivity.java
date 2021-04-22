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

    ImageView logo;
    Button rezerwacje,skaner,stoliki,wyszukiwanie;
    FloatingActionButton wyloguj;
    public static final int CAMERA_PERMISSION_CODE=100;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menurestauracjadesignpatterns);

        logo=findViewById(R.id.logoimageView);
        rezerwacje=findViewById(R.id.rezerwacjebutton);
        skaner=findViewById(R.id.skanerbutton);
        stoliki=findViewById(R.id.stolikibutton);
        wyszukiwanie=findViewById(R.id.wyszukiwaniebutton);

        wyloguj=findViewById(R.id.wylogujbtn);
        firebaseAuth = FirebaseAuth.getInstance();

        rezerwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,HistoriaDesignPatternsActivity.class));
            }
        });

        skaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,SkanerDesignPatternsActivity.class));
            }
        });

        wyszukiwanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,WyszukiwanieRezerwacjiDesignPatternsActivity.class));
            }
        });

        stoliki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaDesignPatternsActivity.this,StolikiOffDesignPatternsActivity.class));
            }
        });
    }

    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenuRestauracjaDesignPatternsActivity.this,LoginDesignPatternsActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
