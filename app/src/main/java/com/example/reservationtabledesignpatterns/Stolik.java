package com.example.reservationtabledesignpatterns;

public class Stolik{
    protected int numer;

    public Stolik(int numer){
        this.numer=numer;
    }

    public String getInformacja() {
        return "Wybrano stolik nr."+this.numer+"!";
    }

    public int getNumer() {
        return this.numer;
    }
}
