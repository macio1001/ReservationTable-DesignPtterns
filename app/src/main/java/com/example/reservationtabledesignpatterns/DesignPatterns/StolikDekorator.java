package com.example.reservationtabledesignpatterns.DesignPatterns;

import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.example.reservationtabledesignpatterns.DesignPatterns.Stolik;

public class StolikDekorator extends Stolik {
    ImageView image;
    Stolik stolik;

    public StolikDekorator(Stolik stolik, ImageView image) {
        this.stolik = stolik;
        this.image = image;
    }

    public void wolne(){
        image.setBackgroundColor(Color.GREEN);
        image.setClickable(true);
    }

    public void zajete(){
        image.setBackgroundColor(Color.RED);
        image.setClickable(true);
    }

    public void niedostepne(){
        image.setBackgroundColor(Color.GRAY);
        image.setClickable(false);
    }

    public void stanPoczatkowyStolikow(){
        image.setVisibility(View.INVISIBLE);
        image.setClickable(false);
        image.setBackgroundColor(Color.GRAY);
    }

    public void setOnClickListener(View.OnClickListener listener){
        image.setOnClickListener(listener);
    }

    public void widoczny(){
        image.setVisibility(View.VISIBLE);
    }
}
