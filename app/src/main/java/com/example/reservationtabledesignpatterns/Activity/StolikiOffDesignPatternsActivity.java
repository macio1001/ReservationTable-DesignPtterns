package com.example.reservationtabledesignpatterns.Activity;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reservationtabledesignpatterns.DesignPatterns.Stolik;
import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.DesignPatterns.StolikWidok;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class StolikiOffDesignPatternsActivity extends AppCompatActivity {

    Button buttonAkceptuj;

    FirebaseFirestore firebaseFirestore;

    private static final String TAG = "StolikiOffActivity";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stolikioffdesignpatterns);

        List<StolikWidok> stolikiWidok=new ArrayList<>();
        stolikiWidok.add(new StolikWidok(new Stolik(1),findViewById(R.id.table1checkBox)));
        stolikiWidok.add(new StolikWidok(new Stolik(2),findViewById(R.id.table2checkBox)));
        stolikiWidok.add(new StolikWidok(new Stolik(3),findViewById(R.id.table3checkbox)));
        stolikiWidok.add(new StolikWidok(new Stolik(4),findViewById(R.id.table4checkBox)));
        stolikiWidok.add(new StolikWidok(new Stolik(5),findViewById(R.id.table5checkBox)));
        stolikiWidok.add(new StolikWidok(new Stolik(6),findViewById(R.id.table6checkBox)));
        buttonAkceptuj = findViewById(R.id.acceotbutton);

        firebaseFirestore = FirebaseFirestore.getInstance();

       stolikiWidok.forEach((stolikWidok -> stolikWidok.setOnChangeListener()));
       stolikiWidok.forEach((stolikWidok -> stolikWidok.sprawdzCzyWlaczony()));

        buttonAkceptuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StolikiOffDesignPatternsActivity.this, MenuRestauracjaDesignPatternsActivity.class));
            }
        });
    }
}
