package com.example.reservationtabledesignpatterns.DesignPatterns;

public class Singleton {

    private String wybranaData;
    private String wybranaGodzina;
    private String email;
    private int ilosc;
    private int stolik;
    private Boolean wylaczStolikPierwszy;
    private Boolean wylaczStolikDrugi;
    private Boolean wylaczStolikCzwarty;
    private Boolean wylaczStolikPiaty;

    private static Singleton instance=null;
    public static synchronized Singleton getInstance(){
        if(instance==null)
            instance=new Singleton();
        return instance;
    }


    public String pokazDateRezerwacji(){
        return wybranaData;
    }

    public void przekazDateRezerwacji(String wybranaData){
        this.wybranaData =wybranaData;
    }

    public String pokazGodzineRezerwacji(){
        return wybranaGodzina;
    }

    public void przekazGodzineRezerwacji(String wybranaGodzina) {
        this.wybranaGodzina = wybranaGodzina;
    }

    public String pokazEmail(){
        return email;
    }

    public void przekazEmail(String email){
        this.email=email;
    }

    public int pokazIlosc(){
        return ilosc;
    }


    public void przekazIlosc(int ilosc){
        this.ilosc=ilosc;
    }

    public int pokazStolik(){
        return stolik;
    }

    public void przekazSttolik(int stolik){
        this.stolik=stolik;
    }

    public Boolean pokazWylaczStolik1(){
        return wylaczStolikPierwszy;
    }

    public void przekazWylaczStolik1(Boolean wylaczStolikPierwszy){
        this.wylaczStolikPierwszy=wylaczStolikPierwszy;
    }

    public Boolean pokazWylaczStolik2(){
        return wylaczStolikDrugi;
    }

    public void przekazWylaczStolik2(Boolean wylaczStolikDrugi){
        this.wylaczStolikDrugi=wylaczStolikDrugi;
    }

    public Boolean pokazWylaczStolik4(){
        return wylaczStolikCzwarty;
    }

    public void przekazWylaczStolik4(Boolean wylaczStolikCzwarty){
        this.wylaczStolikCzwarty=wylaczStolikCzwarty;
    }

    public Boolean pokazWylaczStolik5(){
        return wylaczStolikPiaty;
    }

    public void przekazWylaczStolik5(Boolean wylaczStolikPiaty){
        this.wylaczStolikPiaty=wylaczStolikPiaty;
    }
}
