package com.example.reservationtabledesignpatterns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RezerwacjaInfoDesignPatternsActivity extends AppCompatActivity {

    ImageView imageLogoInfo;
    TextView textImieInfo,textNazwiskoInfo,textEmailInfo,textGodzinaInfo,textDataInfo,textTelefonInfo,textStolikInfo,textIloscInfo;

    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    String kod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezerwacjainfodesignpatterns);

        imageLogoInfo=findViewById(R.id.logoinfoimageview);
        textImieInfo=findViewById(R.id.imieInfoTextVIew);
        textNazwiskoInfo=findViewById(R.id.nazwiskoInfoTextView);
        textEmailInfo=findViewById(R.id.emailInfoTextView);
        textGodzinaInfo=findViewById(R.id.godzinaInfoTextView);
        textDataInfo=findViewById(R.id.dataInfoTextView);
        textTelefonInfo=findViewById(R.id.telefonInfoTextView);
        textStolikInfo=findViewById(R.id.stolikInfoTextView);
        textIloscInfo=findViewById(R.id.iloscInfoTextView);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        RezerwacjaDesignPatterns rezerwacja=(RezerwacjaDesignPatterns) getIntent().getSerializableExtra("rezerwacja");

        kod=rezerwacja.getKod();
        downloadImage();

        textImieInfo.setText(rezerwacja.getImie());
        textNazwiskoInfo.setText(rezerwacja.getNazwisko());
        textEmailInfo.setText(rezerwacja.getEmail());
        textGodzinaInfo.setText(rezerwacja.getGodzina());
        textDataInfo.setText(rezerwacja.getData());
        textTelefonInfo.setText(rezerwacja.getTelefon());
        textStolikInfo.setText(rezerwacja.getStolik());
        textIloscInfo.setText(rezerwacja.getIlosc());
    }

    public void downloadImage(){
        StorageReference imageReference=storageReference.child("KodQR/"+kod+".jpg");

        long MAXBYTES=1024*1024;

        imageReference.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imageLogoInfo.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }
}
