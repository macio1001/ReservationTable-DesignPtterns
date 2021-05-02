package com.example.reservationtabledesignpatterns;

public class Stoliknr3 extends InformacjeAbstraktDekorator{
    public Stoliknr3(InformacjeAbstrakt informacjeAbstrakt){
        super(informacjeAbstrakt);
    }
    @Override
    public String getInformacja() {
        return informacjeAbstrakt.getInformacja()+"Wybrano stolik nr.3!";
    }

    @Override
    public int getNumer() {
        return informacjeAbstrakt.getNumer()+1;
    }
}
