package com.example.reservationtabledesignpatterns.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reservationtabledesignpatterns.DesignPatterns.Singleton;
import com.example.reservationtabledesignpatterns.DesignPatterns.StolikDekorator;
import com.example.reservationtabledesignpatterns.DesignPatterns.StolikWidok;
import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.RezerwacjaDesignPatterns;
import com.example.reservationtabledesignpatterns.DesignPatterns.Stolik;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.reservationtabledesignpatterns.R.*;

public class StolikiDesignPatternsActivity extends AppCompatActivity {

    private static final String TAG="StolikiActivity";
    Stolik imageStolikPierwszy,imageStolikDrugi,imageStolikTrzeci,imageStolikCzwarty,imageStolikPiaty,imageStolikSzosty;
    Button buttonDalej;

    Stolik stolik;
    Boolean onOffPierwszy=false,onOffDrugi=false,onOffTrzeci=false,onOffCzwarty=false,onOffPiaty=false,onOffSzosty=false;

    FirebaseFirestore firebaseFirestore;
    Singleton singleton=Singleton.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.stolikidesignpatterns);

        Stolik imageStolikPierwszy= new StolikDekorator(new Stolik(1), findViewById(id.stolik1));
        imageStolikDrugi= new StolikDekorator(new Stolik(2), findViewById(id.stolik2));
        imageStolikTrzeci= new StolikDekorator(new Stolik(1), findViewById(id.stolik3));
        imageStolikCzwarty= new StolikDekorator(new Stolik(1), findViewById(id.stolik4));
        imageStolikPiaty= new StolikDekorator(new Stolik(1), findViewById(id.stolik5));
        imageStolikSzosty= new StolikDekorator(new Stolik(1), findViewById(id.stolik6));

        buttonDalej=findViewById(id.dalejbutton);

        this.firebaseFirestore=FirebaseFirestore.getInstance();

        String wybranaData=singleton.pokazdaterezerwacji();
        String wybranaGodzina=singleton.pokazgodzinerezerwacji();
        int ilosc=singleton.pokazilosc();
        Boolean wylaczStolikPierwszy=singleton.pokazwylaczStolik1();
        Boolean wylaczStolikDrugi=singleton.pokazwylaczStolik2();
        Boolean wylaczStolikCzwarty=singleton.pokazwylaczStolik4();
        Boolean wylaczStolikPiaty=singleton.pokazwylaczStolik5();


        imageStolikPierwszy.stanPoczatkowyStolikow();
        imageStolikDrugi.stanPoczatkowyStolikow();
        imageStolikTrzeci.stanPoczatkowyStolikow();
        imageStolikCzwarty.stanPoczatkowyStolikow();
        imageStolikPiaty.stanPoczatkowyStolikow();
        imageStolikSzosty.stanPoczatkowyStolikow();

        wylaczonystolik1();
        wylaczonystolik2();
        wylaczonystolik3();
        wylaczonystolik4();
        wylaczonystolik5();
        wylaczonystolik6();


        if(ilosc==1 || ilosc==2){
            imageStolikPierwszy.wolne();
            imageStolikDrugi.wolne();
            firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacjaDesignPatterns.getDocumentId();

                                if(documentId!=null){
                                    imageStolikPierwszy.zajete();
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
            firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacjaDesignPatterns.getDocumentId();

                                if(documentId!=null){
                                    imageStolikPierwszy.zajete();
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }
        else if(ilosc==3){
            imageStolikTrzeci.wolne();
            firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacjaDesignPatterns.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        imageStolikTrzeci.zajete();
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }
        else if(ilosc==4){
            imageStolikCzwarty.wolne();
            imageStolikPiaty.wolne();
            firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacjaDesignPatterns.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        imageStolikCzwarty.zajete();
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });

            firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacjaDesignPatterns.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        imageStolikPiaty.zajete();
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }
        if(ilosc==5 || ilosc==6){
            imageStolikSzosty.wolne();
            firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacjaDesignPatterns.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        imageStolikSzosty.zajete();
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }

        if(wylaczStolikPierwszy==true){
            imageStolikPierwszy.niedostepne();
        }else if(wylaczStolikDrugi==true){
            imageStolikDrugi.niedostepne();
        }else if(wylaczStolikCzwarty==true){
            imageStolikCzwarty.niedostepne();
        }else if(wylaczStolikPiaty==true){
            imageStolikPiaty.niedostepne();
        }

        imageStolikPierwszy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=new Stolik(1);
                Toast.makeText(StolikiDesignPatternsActivity.this,stolik.getInformacja(),Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikDrugi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=new Stolik(2);
                Toast.makeText(StolikiDesignPatternsActivity.this,stolik.getInformacja(),Toast.LENGTH_SHORT).show();
            }
        });


        imageStolikTrzeci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=new Stolik(3);
                Toast.makeText(StolikiDesignPatternsActivity.this,stolik.getInformacja(),Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikCzwarty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=new Stolik(4);
                Toast.makeText(StolikiDesignPatternsActivity.this,stolik.getInformacja(),Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikPiaty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stolik=new Stolik(5);
                Toast.makeText(StolikiDesignPatternsActivity.this,stolik.getInformacja(),Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikSzosty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=new Stolik(6);
                Toast.makeText(StolikiDesignPatternsActivity.this,stolik.getInformacja(),Toast.LENGTH_SHORT).show();
            }
        });

        buttonDalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stolik==null){
                    Toast.makeText(StolikiDesignPatternsActivity.this,"Nie wybrałes żadnego stolika!",Toast.LENGTH_SHORT).show();
                }else {
                    singleton.przekazsttolik(stolik.getNumer());
                    Intent intent = new Intent(StolikiDesignPatternsActivity.this, DaneOsoboweDesignPatternsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void wylaczonystolik1() {
        firebaseFirestore.collection("Stoliknr1").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffPierwszy = documentSnapshot.getBoolean("OnOff");
                                if (onOffPierwszy == true) {
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }else{
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik2() {
        firebaseFirestore.collection("Stoliknr2").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffDrugi = documentSnapshot.getBoolean("OnOff");
                                if (onOffDrugi == true) {
                                    imageStolikPierwszy.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }else{
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik3() {
        firebaseFirestore.collection("Stoliknr3").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffTrzeci = documentSnapshot.getBoolean("OnOff");
                                if (onOffTrzeci == true) {
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }else{
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik4() {
        firebaseFirestore.collection("Stoliknr4").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffCzwarty = documentSnapshot.getBoolean("OnOff");
                                if (onOffCzwarty == true) {
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }else{
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik5() {
        firebaseFirestore.collection("Stoliknr5").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffPiaty = documentSnapshot.getBoolean("OnOff");
                                if (onOffPiaty == true) {
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }else{
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik6(){
        firebaseFirestore.collection("Stoliknr6").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                onOffSzosty=documentSnapshot.getBoolean("OnOff");
                                if(onOffSzosty==true){
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                }else{
                                    imageStolikPierwszy.widoczny();
                                    imageStolikDrugi.widoczny();
                                    imageStolikTrzeci.widoczny();
                                    imageStolikCzwarty.widoczny();
                                    imageStolikPiaty.widoczny();
                                    imageStolikSzosty.widoczny();
                                }
                                Log.d(TAG,"DocumentSnapshot data: "+documentSnapshot.getData());
                            }else{
                                Log.d(TAG,"No such document");
                            }
                        }else{
                            Log.d(TAG,"Get Failed with "+task.getException());
                        }
                    }
                });
    }
}