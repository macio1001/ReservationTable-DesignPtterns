package com.example.reservationtabledesignpatterns.DesignPatterns;


import android.view.View;


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

    public void wolne(){

    }

    public void zajete(){

    }

    public void niedostepne(){

    }

    public void stanPoczatkowyStolikow(){

    }

    public void widoczny(){

    }

    public void setOnClickListener(View.OnClickListener listener){

    }
}
