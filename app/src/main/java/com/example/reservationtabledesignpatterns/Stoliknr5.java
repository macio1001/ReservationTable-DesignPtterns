package com.example.reservationtabledesignpatterns;

public class Stoliknr5 extends InformacjeAbstraktDekorator {
    public Stoliknr5(InformacjeAbstrakt informacjeAbstrakt){
        super(informacjeAbstrakt);
    }
    @Override
    public String getInformacja() {
        return informacjeAbstrakt.getInformacja()+"Wybrano stolik nr.5!";
    }

    @Override
    public int getNumer() {
        return informacjeAbstrakt.getNumer()+1;
    }
}
