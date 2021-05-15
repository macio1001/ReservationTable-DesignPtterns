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
    CalendarView kalendarz;
    private RecyclerView kalendarzrecyclerview;
    private HistoriaDesignPatternsAdapter historiaDesignPatternsAdapter;
    private List<RezerwacjaDesignPatterns> historiaList;
    private FirebaseFirestore firebaseFirestore;

    String Miesiac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historiadesignpatterns);

        kalendarz=findViewById(R.id.calendarView);
        kalendarzrecyclerview=(RecyclerView) findViewById(R.id.historialist);

        historiaList=new ArrayList<>();
        historiaDesignPatternsAdapter=new HistoriaDesignPatternsAdapter(this,historiaList);
        kalendarzrecyclerview.setHasFixedSize(true);
        kalendarzrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        kalendarzrecyclerview.setAdapter(historiaDesignPatternsAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        kalendarz.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                historiaList.clear();
                kalendarzrecyclerview.getAdapter().notifyDataSetChanged();
                switch (miesiac){
                    case 0: Miesiac="Styczeń";break;
                    case 1: Miesiac="Luty";break;
                    case 2: Miesiac="Marzec";break;
                    case 3: Miesiac="Kwiecień";break;
                    case 4: Miesiac="Maj";break;
                    case 5: Miesiac="Czerwiec";break;
                    case 6: Miesiac="Lipiec";break;
                    case 7: Miesiac="Sierpień";break;
                    case 8: Miesiac="Wrzesień";break;
                    case 9: Miesiac="Październik";break;
                    case 10: Miesiac="Listopad";break;
                    case 11: Miesiac="Grudzień";break;
                }
                String data=dzien+" "+Miesiac+" "+rok;
                firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
        });
    }
}
