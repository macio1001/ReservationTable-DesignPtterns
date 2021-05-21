package com.example.reservationtabledesignpatterns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PrzypomnienieHaslaDesignPatternsActivty extends AppCompatActivity {

    EditText editEmail;
    TextView textPrzypomnienie;
    Button buttonPrzypomnij;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przypomnieniehasladesignpatterns);

        editEmail=findViewById(R.id.emaileditText);
        textPrzypomnienie=findViewById(R.id.przypomnijtextview);
        buttonPrzypomnij=findViewById(R.id.przypomnijbutton);

        firebaseAuth= FirebaseAuth.getInstance();

        buttonPrzypomnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editEmail.getText().toString();
                if(!TextUtils.isEmpty(email)) {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PrzypomnienieHaslaDesignPatternsActivty.this, "Hasło wyslane na adres e-mail",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(PrzypomnienieHaslaDesignPatternsActivty.this,LogowanieDesignPatternsActivity.class));
                            } else {
                                Toast.makeText(PrzypomnienieHaslaDesignPatternsActivty.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
