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

public class RejestracjaDesignPatternsActivity extends AppCompatActivity {

    ImageView imageLogo;
    TextView textMail,textHaslo,textDrugieHaslo;
    EditText editEmail,editHaslo,editDrugieHaslo;
    Button buttonRejestruj;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rejestracjadesignpatterns);

        imageLogo=findViewById(R.id.logoimageView);
        textMail=findViewById(R.id.emailtextView);
        textHaslo=findViewById(R.id.haslotextView);
        textDrugieHaslo=findViewById(R.id.powtorzhaslotextView);
        editEmail=findViewById(R.id.emaileditText);
        editHaslo=findViewById(R.id.hasloeditText);
        editDrugieHaslo=findViewById(R.id.powtorzhasloeditText);
        buttonRejestruj=findViewById(R.id.zarejestrujbutton);

        firebaseAuth= FirebaseAuth.getInstance();

        buttonRejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editEmail.getText().toString();
                String haslo=editHaslo.getText().toString();
                String drugieHaslo=editDrugieHaslo.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(haslo) && !TextUtils.isEmpty(drugieHaslo)) {
                    if(haslo.length()<6){
                        Toast.makeText(RejestracjaDesignPatternsActivity.this,"Hasło musi mieć conajmniej 6 znaków!",Toast.LENGTH_LONG).show();
                    }
                    else if(!haslo.equals(drugieHaslo)){
                        Toast.makeText(RejestracjaDesignPatternsActivity.this,"Podane hasła nie są takie same!",Toast.LENGTH_LONG).show();
                    }else {
                        firebaseAuth.createUserWithEmailAndPassword(email, haslo)
                                .addOnCompleteListener((task) -> {
                                    if (task.isSuccessful()) {
                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RejestracjaDesignPatternsActivity.this, "Sukces.Proszę zweryfikować swój adres email",
                                                            Toast.LENGTH_LONG).show();
                                                    editEmail.setText("");
                                                    editHaslo.setText("");
                                                    startActivity(new Intent(RejestracjaDesignPatternsActivity.this, LogowanieDesignPatternsActivity.class));
                                                } else {
                                                    Toast.makeText(RejestracjaDesignPatternsActivity.this, task.getException().getMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(RejestracjaDesignPatternsActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                }
            }
        });
    }
}
