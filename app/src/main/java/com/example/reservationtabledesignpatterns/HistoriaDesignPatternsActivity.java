package com.example.reservationtabledesignpatterns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoriaDesignPatternsActivity extends AppCompatActivity {

    private static final String TAG="HistoriaActivity";
    CalendarView calendarKalendarz;
    RecyclerView recyclerviewKalendarz;

    HistoriaDesignPatternsAdapter historiaDesignPatternsAdapter;
    List<RezerwacjaDesignPatterns> historiaList;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historiadesignpatterns);

        calendarKalendarz=findViewById(R.id.calendarView);
        recyclerviewKalendarz=(RecyclerView) findViewById(R.id.historialist);

        historiaList=new ArrayList<>();
        historiaDesignPatternsAdapter=new HistoriaDesignPatternsAdapter(this,historiaList);
        recyclerviewKalendarz.setHasFixedSize(true);
        recyclerviewKalendarz.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewKalendarz.setAdapter(historiaDesignPatternsAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        calendarKalendarz.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                String nazwaMesiaca=RezerwacjaUtils.getNazwaMiesiaca(miesiac);
                historiaList.clear();
                recyclerviewKalendarz.getAdapter().notifyDataSetChanged();
                String data=dzien+" "+nazwaMesiaca+" "+rok;

                for(int i=0;i<=6;i++){
                    firebaseFirestore.collection("Stoliknr"+i).whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if(e!=null){
                                Log.d(TAG,"Error:"+e.getMessage());
                            }else{
                                for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                    if(documentChange.getType()==DocumentChange.Type.ADDED){
                                        RezerwacjaDesignPatterns rezerwacjaDesignPatterns=documentChange.getDocument().toObject(RezerwacjaDesignPatterns.class);
                                        historiaList.add(rezerwacjaDesignPatterns);

                                        historiaDesignPatternsAdapter.notifyDataSetChanged();
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
