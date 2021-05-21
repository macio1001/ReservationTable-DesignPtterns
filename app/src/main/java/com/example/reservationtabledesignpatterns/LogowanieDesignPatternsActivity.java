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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogowanieDesignPatternsActivity extends AppCompatActivity {

    EditText editEmail, editHaslo;
    Button buttonLoguj;
    TextView textPrzypomnij, textRejestruj, textMail, textHaslo;
    ImageView imagelogo;

    FirebaseAuth firebaseAuth;

    Singleton singleton = Singleton.getInstance();

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser uzytkownik = firebaseAuth.getCurrentUser();
        if (uzytkownik != null) {
            String email = uzytkownik.getEmail();
            if (email.equals("maciej.oles@gmail.com")) {
                singleton.przekazEmail(email);
                Intent intent = new Intent(getApplicationContext(), MenuKlientDesignPatternsActivity.class);
                startActivity(intent);
            }
            if (email.equals("reservationtable1@gmail.com")) {
                Intent intent = new Intent(getApplicationContext(), MenuRestauracjaDesignPatternsActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logindesignpatterns);

        editEmail = findViewById(R.id.emaileditText);
        textMail = findViewById(R.id.emailtextview);
        editHaslo = findViewById(R.id.hasloeditText);
        textHaslo = findViewById(R.id.haslotextView);
        buttonLoguj = findViewById(R.id.zalogujbutton);
        textPrzypomnij = findViewById(R.id.przypomnijtextview);
        textRejestruj = findViewById(R.id.rejestrujtextView);
        imagelogo = findViewById(R.id.logoimageView);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonLoguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String haslo = editHaslo.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(haslo)) {
                    firebaseAuth.signInWithEmailAndPassword(email, haslo)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                            if (email.equals("maciej.oles@gmail.com")) {
                                                singleton.przekazEmail(email);
                                                Intent intent = new Intent(LogowanieDesignPatternsActivity.this, MenuKlientDesignPatternsActivity.class);
                                                startActivity(intent);
                                            }
                                            if (email.equals("reservationtable1@gmail.com")) {
                                                startActivity(new Intent(LogowanieDesignPatternsActivity.this, MenuRestauracjaDesignPatternsActivity.class));
                                            }
                                        } else {
                                            Toast.makeText(LogowanieDesignPatternsActivity.this, "Proszę, zweryfikuj swój email",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(LogowanieDesignPatternsActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        textPrzypomnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogowanieDesignPatternsActivity.this, PrzypomnienieHaslaDesignPatternsActivty.class));
            }
        });

        textRejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogowanieDesignPatternsActivity.this, RejestracjaDesignPatternsActivity.class));
            }
        });
    }
}

