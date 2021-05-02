package com.example.reservationtabledesignpatterns;

public abstract class InformacjeAbstraktDekorator extends InformacjeAbstrakt {
    protected InformacjeAbstrakt informacjeAbstrakt;

    public InformacjeAbstraktDekorator(InformacjeAbstrakt informacjeAbstrakt){
        this.informacjeAbstrakt=informacjeAbstrakt;
    }

    @Override
    public abstract String getInformacja();

    @Override
    public abstract int getNumer();
}
