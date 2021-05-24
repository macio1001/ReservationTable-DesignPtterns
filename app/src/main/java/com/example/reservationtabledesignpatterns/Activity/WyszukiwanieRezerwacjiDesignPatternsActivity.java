package com.example.reservationtabledesignpatterns.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reservationtabledesignpatterns.Adapter.RezerwacjaDesignPatternsAdapter;
import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.RezerwacjaDesignPatterns;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WyszukiwanieRezerwacjiDesignPatternsActivity extends AppCompatActivity {

    private static final String TAG="WyszukiwanieActivity";
    EditText editKod;
    Button buttonSzukaj;
    RecyclerView recyclerviewRezerwacja;

    List<RezerwacjaDesignPatterns> rezerwacjaList;
    RezerwacjaDesignPatternsAdapter rezerwacjaDesignPatternsAdapter;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wyszukiwanierezerwacjidesignpatterns);

        editKod=findViewById(R.id.kodeditText);
        buttonSzukaj=findViewById(R.id.szukajbutton);

        recyclerviewRezerwacja=(RecyclerView) findViewById(R.id.rezerwujlist);
        rezerwacjaList=new ArrayList<>();
        rezerwacjaDesignPatternsAdapter=new RezerwacjaDesignPatternsAdapter(this,rezerwacjaList);
        recyclerviewRezerwacja.setHasFixedSize(true);
        recyclerviewRezerwacja.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewRezerwacja.setAdapter(rezerwacjaDesignPatternsAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();

        buttonSzukaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wyszukanie=editKod.getText().toString();

                for(int i=0;i<=6;i++) {
                    firebaseFirestore.collection("Stoliknr" + i).whereEqualTo("Nazwisko", wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "Error:" + e.getMessage());
                            } else {
                                for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                        RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                        rezerwacjaList.add(rezerwacjaDesignPatterns);

                                        rezerwacjaDesignPatternsAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
