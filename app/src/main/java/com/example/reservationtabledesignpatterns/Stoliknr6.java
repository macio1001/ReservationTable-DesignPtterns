package com.example.reservationtabledesignpatterns;

public class Stoliknr6 extends InformacjeAbstraktDekorator {
    public Stoliknr6(InformacjeAbstrakt informacjeAbstrakt){
        super(informacjeAbstrakt);
    }
    @Override
    public String getInformacja() {
        return informacjeAbstrakt.getInformacja()+"Wybrano stolik nr.6!";
    }

    @Override
    public int getNumer() {
        return informacjeAbstrakt.getNumer()+1;
    }
}
