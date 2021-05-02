package com.example.reservationtabledesignpatterns;

public class Stoliknr2 extends InformacjeAbstraktDekorator {

    public Stoliknr2(InformacjeAbstrakt informacjeAbstrakt){
        super(informacjeAbstrakt);
    }
    @Override
    public String getInformacja() {
        return informacjeAbstrakt.getInformacja()+"Wybrano stolik nr.2!";
    }

    @Override
    public int getNumer() {
        return informacjeAbstrakt.getNumer()+1;
    }
}
