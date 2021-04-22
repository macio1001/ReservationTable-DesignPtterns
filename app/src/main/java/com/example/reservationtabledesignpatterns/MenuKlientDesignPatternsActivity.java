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

    private static final String TAG="MenuKlientaActivity";
    ImageView logo;
    Button historia,rezerwuj;
    FirebaseAuth firebaseAuth;
    FloatingActionButton wyloguj;

    Singleton singleton=Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuklientdesignpatterns);

        logo=findViewById(R.id.logoimageView);
        historia=findViewById(R.id.historiabutton);
        rezerwuj=findViewById(R.id.rezerwujbutton);
        wyloguj=findViewById(R.id.wylogujbtn);
        firebaseAuth = FirebaseAuth.getInstance();

        historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Lala"+singleton.pokazEmail());
                Intent intent=new Intent(MenuKlientDesignPatternsActivity.this,HistoriaUserDesignPatternsActivity.class);
                startActivity(intent);
            }
        });

        rezerwuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Lala"+singleton.pokazEmail());
                Intent intent=new Intent(MenuKlientDesignPatternsActivity.this,RezerwujDesignPatternsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenuKlientDesignPatternsActivity.this,LoginDesignPatternsActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
