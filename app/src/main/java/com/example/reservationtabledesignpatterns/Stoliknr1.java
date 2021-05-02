package com.example.reservationtabledesignpatterns;

public class Stoliknr1 extends InformacjeAbstraktDekorator{

    public Stoliknr1(InformacjeAbstrakt informacjeAbstrakt){
        super(informacjeAbstrakt);
    }

    @Override
    public String getInformacja() {
        return informacjeAbstrakt.getInformacja()+"Wybrano stolik nr.1!";
    }

    @Override
    public int getNumer() {
        return informacjeAbstrakt.getNumer()+1;
    }
}
