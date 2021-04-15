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

public class PrzypomnieniehaslaDesignPatternsActivty extends AppCompatActivity {

    EditText email;
    TextView przypomnienie;
    Button przypomnij;
    ImageView logo;

    String Email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przypomnieniehasladesignpatterns);

        logo=findViewById(R.id.logoimageView);
        email=findViewById(R.id.emaileditText);
        przypomnienie=findViewById(R.id.przypomnijtextview);
        przypomnij=findViewById(R.id.przypomnijbutton);

        firebaseAuth= FirebaseAuth.getInstance();

        przypomnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email=email.getText().toString();
                if(!TextUtils.isEmpty(Email)) {
                    firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PrzypomnieniehaslaDesignPatternsActivty.this, "Hasło wyslane na adres e-mail",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(PrzypomnieniehaslaDesignPatternsActivty.this,LoginDesignPatternsActivity.class));
                            } else {
                                Toast.makeText(PrzypomnieniehaslaDesignPatternsActivty.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
