package com.example.reservationtabledesignpatterns;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

public class RezerwujDesignPatternsActivity extends AppCompatActivity {

    TextView textWybierzDate,textIloscOsob,textWybranaData,textWybierzGodzine,textWybranaGodzina,textOstrzezenie;
    Button buttonDalej;
    RadioGroup radioGroupIlosOosob;
    RadioButton radiobtnPierwszaOsoba,radiobtnDrugaOsoba,radiobtnTrzeciaOsoba,radiobtnCzwartaOsoba,radiobtnPiataOsoba,radiobtnSzostaOsoba;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    String Minuta;
    String wybranadata,wybranagodzina,Wybrana;
    int ilosc=0,minute,Minute,Godzina;
    FirebaseFirestore firebaseFirestore;
    Boolean zajety1=false,zajety2=false,zajety3=false,zajety4=false,zajety5=false,zajety6=false,WylaczStolik1=false,WylaczStolik2=false,WylaczStolik4=false,WylaczStolik5=false;

    private static final String TAG="RezerwujActivity";


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
        radiobtnPierwszaOsoba=findViewById(R.id.pierwszaosobaradioButton);
        radiobtnDrugaOsoba=findViewById(R.id.drugaosobaradioButton);
        radiobtnTrzeciaOsoba=findViewById(R.id.trzeciaosobaradioButton);
        radiobtnCzwartaOsoba=findViewById(R.id.czwartaosobaradioButton);
        radiobtnPiataOsoba=findViewById(R.id.piataosobaradioButton);
        radiobtnSzostaOsoba=findViewById(R.id.szostaosobaradioButton);
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
                String nazwaMesiaca=RezerwacjaUtils.getNazwaMiesiaca(miesiac);
                textWybranaData.setText(dzien+" "+nazwaMesiaca+" "+rok);
                wybranadata=dzien+" "+nazwaMesiaca+" "+rok;
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
                            Minuta="0"+minuta;
                        }else{
                            Minuta=String.valueOf(minuta);
                        }
                        wybranagodzina=godzina+":"+Minuta;
                        textWybranaGodzina.setText(godzina+":"+Minuta);
                        Wybrana=wybranagodzina;
                        Minute=Integer.valueOf(Minuta);
                        minute=Minute-15;

                        for (int i=0;i<30;i++) {
                            if(minute >59){
                                godzina+=1;
                                minute=0;
                            }else if(minute<0){
                                godzina-=1;
                                minute+=60;

                            }
                            if (minute>=0 && minute<10){
                                Minuta="0"+minute;
                            }else{
                                Minuta=String.valueOf(minute);
                            }
                            wybranagodzina=godzina+":"+Minuta;
                            Log.d(TAG,"Lala: "+wybranagodzina);
                            wybranagodzina=godzina+":"+minute;
                            firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data", wybranadata).whereEqualTo("Godzina", wybranagodzina).get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                RezerwacjaDesignPatterns rezerwacjaDesignPatterns = documentSnapshot.toObject(RezerwacjaDesignPatterns.class);
                                                rezerwacjaDesignPatterns.setDocumentId(documentSnapshot.getId());
                                                String documentId = rezerwacjaDesignPatterns.getDocumentId();

                                                if (documentId != null) {
                                                    zajety1 = true;
                                                }
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, e.toString());
                                }
                            });
                            firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data", wybranadata).whereEqualTo("Godzina", wybranagodzina).get()
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
                            firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data", wybranadata).whereEqualTo("Godzina", wybranagodzina).get()
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
                            firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data", wybranadata).whereEqualTo("Godzina", wybranagodzina).get()
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
                            firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data", wybranadata).whereEqualTo("Godzina", wybranagodzina).get()
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
                            firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data", wybranadata).whereEqualTo("Godzina", wybranagodzina).get()
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
                            minute+=1;
                        }
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
                            Log.d(TAG,"Lalala: Gotowosc jest:"+zajety1);
                            WylaczStolik1=true;
                            Log.d(TAG,"Lalala: Gotowosc jest:"+WylaczStolik1);
                        }else if(zajety1==false && zajety2==true){
                            Log.d(TAG,"Lalala: Gotowosc jest:"+zajety2);
                            WylaczStolik2=true;
                            Log.d(TAG,"Lalala: Gotowosc jest:"+WylaczStolik2);
                        }else if(zajety1==true && zajety2==true){
                            textOstrzezenie.setText("W tym terminie stoliki są zajęte");
                            buttonDalej.setClickable(false);
                        }
                        break;
                    case R.id.drugaosobaradioButton:
                        ilosc=2;
                        if(zajety1==true && zajety2==false){
                            Log.d(TAG,"Lalala: Gotowosc jest:"+zajety1);
                            WylaczStolik1=true;
                            Log.d(TAG,"Lalala: Gotowosc jest:"+WylaczStolik1);
                        }else if(zajety1==false && zajety2==true){
                            Log.d(TAG,"Lalala: Gotowosc jest:"+zajety2);
                            WylaczStolik2=true;
                            Log.d(TAG,"Lalala: Gotowosc jest:"+WylaczStolik2);
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
                            Log.d(TAG,"Lalala: Gotowosc jest:"+zajety4);
                            WylaczStolik4=true;
                            Log.d(TAG,"Lalala: Gotowosc jest:"+WylaczStolik4);
                        }else if(zajety4==false && zajety5==true){
                            Log.d(TAG,"Lalala: Gotowosc jest:"+zajety5);
                            WylaczStolik5=true;
                            Log.d(TAG,"Lalala: Gotowosc jest:"+WylaczStolik5);
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
                wybranagodzina=Wybrana;
                if ((wybranadata == null) && (wybranagodzina == null) && (ilosc == 0)) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru obowiązkowych opcji!", Toast.LENGTH_SHORT).show();
                } else if (wybranagodzina == null) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru godziny!", Toast.LENGTH_SHORT).show();
                } else if (wybranadata == null) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru daty!", Toast.LENGTH_SHORT).show();
                } else if (ilosc == 0) {
                    Toast.makeText(RezerwujDesignPatternsActivity.this, "Nie dokonałes wyboru ilosci osob!!", Toast.LENGTH_SHORT).show();
                }else {
                    singleton.przekazdaterezerwacji(wybranadata);
                    singleton.przekazgodzinerezerwacji(wybranagodzina);
                    singleton.przekazilosc(ilosc);
                    singleton.przekazwylaczStolik1(WylaczStolik1);
                    singleton.przekazwylaczStolik2(WylaczStolik2);
                    singleton.przekazwylaczStolik4(WylaczStolik4);
                    singleton.przekazwylaczStolik5(WylaczStolik5);
                    Intent intent = new Intent(RezerwujDesignPatternsActivity.this, StolikiDesignPatternsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
