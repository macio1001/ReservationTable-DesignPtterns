package com.example.reservationtabledesignpatterns.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.reservationtabledesignpatterns.DesignPatterns.Singleton;
import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.RezerwacjaDesignPatterns;
import com.example.reservationtabledesignpatterns.RezerwacjaUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

public class RezerwujDesignPatternsActivity extends AppCompatActivity {

    private static final String TAG="RezerwujActivity";
    TextView textWybierzDate,textIloscOsob,textWybranaData,textWybierzGodzine,textWybranaGodzina,textOstrzezenie;
    Button buttonDalej;
    RadioGroup radioGroupIlosOosob;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    String wybranaData,wybranaGodzina,wybrana,minutaString;
    int ilosc=0,minutaInt1,minutaInt;
    FirebaseFirestore firebaseFirestore;
    Boolean zajety1=false,zajety2=false,zajety3=false,zajety4=false,zajety5=false,zajety6=false,wylaczStolikPierwszy=false,wylaczStolikDrugi=false,wylaczStolikCzwarty=false,wylaczStolikPiaty=false;

    Singleton singleton=Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezerwujdesignpatterns);

        textWybierzDate=findViewById(R.id.wybierzdateTextView);
        textWybierzGodzine=findViewById(R.id.wybierzgodzineTextView);
        textIloscOsob=findViewById(R.id.howmanyperson);
        radioGroupIlosOosob=findViewById(R.id.RadioGroup);
        textWybranaData=findViewById(R.id.wybranadataTextView);
        textWybranaGodzina=findViewById(R.id.wybranagodzinaTextView);
        textOstrzezenie=findViewById(R.id.ostrzezenieTextView);
        buttonDalej=findViewById(R.id.dalejBtn);

        firebaseFirestore=FirebaseFirestore.getInstance();

        String iloscosob[]={"Wybierz ilosc osob","1","2","3","4","5","6"};

        textWybierzDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar kalendarz=Calendar.getInstance();

                int rok=kalendarz.get(Calendar.YEAR);
                int miesiac=kalendarz.get(Calendar.MONTH);
                int dzien=kalendarz.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(RezerwujDesignPatternsActivity.this,onDateSetListener,rok,miesiac,dzien);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();
            }
        });

        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int rok, int miesiac, int dzien) {
                String nazwaMesiaca= RezerwacjaUtils.getNazwaMiesiaca(miesiac);
                textWybranaData.setText(dzien+" "+nazwaMesiaca+" "+rok);
                wybranaData=dzien+" "+nazwaMesiaca+" "+rok;
            }
        };



        textWybierzGodzine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar kalendarz1=Calendar.getInstance();
                int godzina=kalendarz1.get(Calendar.HOUR_OF_DAY);
                int minuta=kalendarz1.get(Calendar.MINUTE);
                zajety1=false;
                zajety2=false;
                zajety3=false;
                zajety4=false;
                zajety5=false;
                zajety6=false;
                textOstrzezenie.setText("");
                radioGroupIlosOosob.clearCheck();
                TimePickerDialog timePickerDialog=new TimePickerDialog(RezerwujDesignPatternsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int godzina, int minuta) {
                        buttonDalej.setClickable(true);
                        if (minuta>=0 && minuta<10){
                            minutaString="0"+minuta;
                        }else{
                            minutaString=String.valueOf(minuta);
                        }
                        wybranaGodzina=godzina+":"+minutaString;
                        textWybranaGodzina.setText(godzina+":"+minutaString);
                        wybrana=wybranaGodzina;
                        minutaInt=Integer.valueOf(minutaString);
                        minutaInt1=minutaInt-15;

                        for (int i=0;i<30;i++) {
                            if(minutaInt1 >59){
                                godzina+=1;
                                minutaInt1=0;
                            }else if(minutaInt1<0){
                                godzina-=1;
                                minutaInt1+=60;

                            }
                            if (minutaInt1>=0 && minutaInt1<10){
                                minutaString="0"+minutaInt1;
                            }else{
                                minutaString=String.valueOf(minutaInt1);
                            }
                            wybranaGodzina=godzina+":"+minutaString;
                            Log.d(TAG,"Lala: "+wybranaGodzina);
                            wybranaGodzina=godzina+":"+minutaInt1;


                            firebaseFirestore.collection("Stoliknr"+i).whereEqualTo("Data", wybranaData).whereEqualTo("Godzina", wybranaGodzina).get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                    RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                    rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                    String documentId = rezerwacjaDesignPatterns.getDocumentId();
                                                    if (documentId != null) {
                                                            zajety1=true;

                                                    }
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, e.toString());
                                    }
                                });
                            }
                            firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data", wybranaData).whereEqualTo("Godzina", wybranaGodzina).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                String documentId = rezerwacjaDesignPatterns.getDocumentId();

                                                if (documentId != null) {
                                                    zajety2= true;
                                                }
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, e.toString());
                                }
                            });
                            firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data", wybranaData).whereEqualTo("Godzina", wybranaGodzina).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                String documentId = rezerwacjaDesignPatterns.getDocumentId();

                                                if (documentId != null) {
                                                    zajety3= true;
                                                }
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, e.toString());
                                }
                            });
                            firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data", wybranaData).whereEqualTo("Godzina", wybranaGodzina).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                String documentId = rezerwacjaDesignPatterns.getDocumentId();

                                                if (documentId != null) {
                                                    zajety4= true;
                                                }
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, e.toString());
                                }
                            });
                                    firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data", wybranaData).whereEqualTo("Godzina", wybranaGodzina).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns= documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                String documentId = rezerwacjaDesignPatterns.getDocumentId();

                                                if (documentId != null) {
                                                    zajety5= true;
                                                }
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, e.toString());
                                }
                            });
                            firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data", wybranaData).whereEqualTo("Godzina", wybranaGodzina).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                String documentId = rezerwacjaDesignPatterns.getDocumentId();

                                                if (documentId != null) {
                                                    zajety6= true;
                                                }
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, e.toString());
                                }
                            });
                            minutaInt1+=1;
                        }
                },godzina,minuta,true);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                timePickerDialog.show();
            }
        });

        radioGroupIlosOosob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.pierwszaosobaradioButton:
                        ilosc=1;
                        if(zajety1==true && zajety2==false){
                            wylaczStolikPierwszy=true;
                        }else if(zajety1==false && zajety2==true){
                            wylaczStolikDrugi=true;
                        }else if(zajety1==true && zajety2==true){
                            textOstrzezenie.setText("W tym terminie stoliki dla 1 lub 2 osób są zajęte.Proszę wybrać inną godzinę");
                            buttonDalej.setClickable(false);
                        }
                        break;
                    case R.id.drugaosobaradioButton:
                        ilosc=2;
                        if(zajety1==true && zajety2==false){
                            wylaczStolikPierwszy=true;
                        }else if(zajety1==false && zajety2==true){
                            wylaczStolikDrugi=true;
                        }else if(zajety1==true && zajety2==true){
                            textOstrzezenie.setText("O tej godzinie stoliki dla 2 osób są zajęte!Proszę wybrać inną godzinę!");
                            buttonDalej.setClickable(false);
                        }
                        break;
                    case R.id.trzeciaosobaradioButton:
                        ilosc=3;
                        if(zajety3==true){
                            textOstrzezenie.setText("W tym terminie stoliki dla 3 osób są zajęte!Proszę wybrać inną godzinę!");
                            buttonDalej.setClickable(false);
                        }
                        break;
                    case R.id.czwartaosobaradioButton:
                        ilosc=4;
                        if(zajety4==true && zajety5==false){
                            wylaczStolikCzwarty=true;
                        }else if(zajety4==false && zajety5==true){
                            wylaczStolikPiaty=true;
                        }else if(zajety4==true && zajety5==true){
                            textOstrzezenie.setText("W tym terminie stoliki dla 4 osób są zajęte! Proszę wybrać inną godzinę!");
                            buttonDalej.setClickable(false);
                        }
                        break;
                    case R.id.piataosobaradioButton:
                        ilosc=5;
                        if(zajety5==true){
                            textOstrzezenie.setText("W tym terminie stoliki dla 6 osób są zajęte!Proszę wybrać inną godzinę!");
                            buttonDalej.setClickable(false);
                        }
                        break;
                    case R.id.szostaosobaradioButton:
                        ilosc=6;
                        if(zajety6==true){
                            textOstrzezenie.setText("W tym terminie stoliki dla 6 osób są zajęte!Proszę wybrać inną godzinę!");
                            buttonDalej.setClickable(false);
                        }
                        break;
                }
            }
        });
        buttonDalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wybranaGodzina=wybrana;
                if ((wybranaData == null) && (wybranaGodzina == null) && (ilosc == 0)) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru obowiązkowych opcji!", Toast.LENGTH_SHORT).show();
                } else if (wybranaGodzina == null) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru godziny!", Toast.LENGTH_SHORT).show();
                } else if (wybranaData == null) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru daty!", Toast.LENGTH_SHORT).show();
                } else if (ilosc == 0) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru ilosci osob!!", Toast.LENGTH_SHORT).show();
                }else {
                    singleton.przekazDateRezerwacji(wybranaData);
                    singleton.przekazGodzineRezerwacji(wybranaGodzina);
                    singleton.przekazIlosc(ilosc);
                    singleton.przekazWylaczStolik1(wylaczStolikPierwszy);
                    singleton.przekazWylaczStolik2(wylaczStolikDrugi);
                    singleton.przekazWylaczStolik4(wylaczStolikCzwarty);
                    singleton.przekazWylaczStolik5(wylaczStolikPiaty);
                    Intent intent = new Intent(RezerwujDesignPatternsActivity.this, StolikiDesignPatternsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
