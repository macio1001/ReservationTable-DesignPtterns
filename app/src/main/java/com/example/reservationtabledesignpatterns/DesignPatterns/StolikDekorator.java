package com.example.reservationtabledesignpatterns.DesignPatterns;

import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.example.reservationtabledesignpatterns.DesignPatterns.Stolik;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StolikDekorator extends Stolik {
    ImageView image;
    Stolik stolik;

    public StolikDekorator(Stolik stolik, ImageView image) {
        this.stolik = stolik;
        this.image = image;
    }

    @Override
    public void wolne(){
        stolik.wolne();
        image.setBackgroundColor(Color.GREEN);
        image.setClickable(true);
    }

    @Override
    public void zajete(){
        stolik.zajete();
        image.setBackgroundColor(Color.RED);
        image.setClickable(true);
    }

    @Override
    public void niedostepne(){
        stolik.niedostepne();
        image.setBackgroundColor(Color.GRAY);
        image.setClickable(false);
    }

    @Override
    public void stanPoczatkowyStolikow(){
        stolik.stanPoczatkowyStolikow();
        image.setVisibility(View.INVISIBLE);
        image.setClickable(false);
        image.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener){
        stolik.setOnClickListener(listener);
        image.setOnClickListener(listener);
    }

    @Override
    public void widoczny(){
        stolik.widoczny();
        image.setVisibility(View.VISIBLE);
    }
}
