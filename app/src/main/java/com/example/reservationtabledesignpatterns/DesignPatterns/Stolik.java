package com.example.reservationtabledesignpatterns.DesignPatterns;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

public class Stolik {
    protected int numer;

    public Stolik(int numer){
        this.numer=numer;
    }

    public Stolik() {
    }


    public String getInformacja() {
        return "Wybrano stolik nr."+this.numer+"!";
    }

    public int getNumer() {
        return this.numer;
    }
}
