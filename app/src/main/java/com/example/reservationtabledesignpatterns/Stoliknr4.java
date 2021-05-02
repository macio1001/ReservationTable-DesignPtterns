package com.example.reservationtabledesignpatterns;

public class Stoliknr4 extends InformacjeAbstraktDekorator {
    public Stoliknr4(InformacjeAbstrakt informacjeAbstrakt){
        super(informacjeAbstrakt);
    }
    @Override
    public String getInformacja() {
        return informacjeAbstrakt.getInformacja()+"Wybrano stolik nr.4!";
    }

    @Override
    public int getNumer() {
        return informacjeAbstrakt.getNumer()+1;
    }
}
