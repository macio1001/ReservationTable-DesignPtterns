package com.example.reservationtabledesignpatterns;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WyszukiwanieRezerwacjiDesignPatternsActivity extends AppCompatActivity {

    private static final String TAG="WyszukiwanieActivity";
    TextView wyszukiwanie;
    EditText kod;
    Button szukaj;
    String Wyszukanie;

    private RecyclerView recyclerView;
    private List<RezerwacjaDesignPatterns> rezerwacjaList;
    private FirebaseFirestore firebaseFirestore;
    private RezerwacjaDesignPatternsAdapter rezerwacjaDesignPatternsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wyszukiwanierezerwacjidesignpatterns);

        wyszukiwanie=findViewById(R.id.wyszukiwanietextView);
        kod=findViewById(R.id.kodeditText);
        szukaj=findViewById(R.id.szukajbutton);

        recyclerView=(RecyclerView) findViewById(R.id.rezerwujlist);
        rezerwacjaList=new ArrayList<>();
        rezerwacjaDesignPatternsAdapter=new RezerwacjaDesignPatternsAdapter(this,rezerwacjaList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rezerwacjaDesignPatternsAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();

        szukaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Wyszukanie=kod.getText().toString();

                firebaseFirestore.collection("Stoliknr1").whereEqualTo("Nazwisko",Wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                    rezerwacjaList.add(rezerwacjaDesignPatterns);

                                    rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr2").whereEqualTo("Nazwisko",Wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                    rezerwacjaList.add(rezerwacjaDesignPatterns);

                                    rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr3").whereEqualTo("Nazwisko",Wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                    rezerwacjaList.add(rezerwacjaDesignPatterns);

                                    rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr4").whereEqualTo("Nazwisko",Wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                    rezerwacjaList.add(rezerwacjaDesignPatterns);

                                    rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr5").whereEqualTo("Nazwisko",Wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                    rezerwacjaList.add(rezerwacjaDesignPatterns);

                                    rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr6").whereEqualTo("Nazwisko",Wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                    rezerwacjaList.add(rezerwacjaDesignPatterns);

                                    rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}
