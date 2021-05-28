package com.example.reservationtabledesignpatterns.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.example.reservationtabledesignpatterns.Adapter.HistoriaUzytkownikDesignPatternsAdapter;
import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.RezerwacjaDesignPatterns;
import com.example.reservationtabledesignpatterns.RezerwacjaUtils;
import com.example.reservationtabledesignpatterns.DesignPatterns.Singleton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoriaUzytkownikDesignPatternsActivity extends AppCompatActivity {

    private static final String TAG="HistoriaUzytkActivity";
    CalendarView calendarKalendarzUzytkownik;
    RecyclerView recyclerviewKalenadarzUzytkownik;

    HistoriaUzytkownikDesignPatternsAdapter historiaUzytkownikDesignPatternsAdapter;
    List<RezerwacjaDesignPatterns> historiaUzytkownikList;
    FirebaseFirestore firebaseFirestore;

    Singleton singleton=Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historiauserdesignpatterns);

        calendarKalendarzUzytkownik=findViewById(R.id.kalendarzUzytkownik);
        recyclerviewKalenadarzUzytkownik=(RecyclerView) findViewById(R.id.historiaUserList);
        historiaUzytkownikList=new ArrayList<>();
        historiaUzytkownikDesignPatternsAdapter=new HistoriaUzytkownikDesignPatternsAdapter(this,historiaUzytkownikList);
        recyclerviewKalenadarzUzytkownik.setHasFixedSize(true);
        recyclerviewKalenadarzUzytkownik.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewKalenadarzUzytkownik.setAdapter(historiaUzytkownikDesignPatternsAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        String email=singleton.pokazEmail();

        calendarKalendarzUzytkownik.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                String nazwaMesiaca= RezerwacjaUtils.getNazwaMiesiaca(miesiac);
                historiaUzytkownikList.clear();
                recyclerviewKalenadarzUzytkownik.getAdapter().notifyDataSetChanged();

                String data=dzien+" "+nazwaMesiaca+" "+rok;
                for(int i=0;i<=6;i++) {
                    firebaseFirestore.collection("Stoliknr"+i).whereEqualTo("Data", data).whereEqualTo("Email", email).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "Error:" + e.getMessage());
                            } else {
                                for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                        RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                        historiaUzytkownikList.add(rezerwacjaDesignPatterns);

                                        historiaUzytkownikDesignPatternsAdapter.notifyDataSetChanged();
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
