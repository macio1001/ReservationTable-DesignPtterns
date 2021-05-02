package com.example.reservationtabledesignpatterns;

public abstract class InformacjeAbstrakt {
    protected String informacja;
    protected int numer;

    public String getInformacja(){
        return informacja;
    }

    public int getNumer(){
        return numer;
    }

    @Override
    public String toString(){
        return getInformacja();
    }

}
