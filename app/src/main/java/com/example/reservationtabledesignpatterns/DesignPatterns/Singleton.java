package com.example.reservationtabledesignpatterns.DesignPatterns;

public class Singleton {

    private String wybranadata;
    private String wybranagodzina;
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


    public String pokazdaterezerwacji(){
        return wybranadata;
    }

    public void przekazdaterezerwacji(String wybranadata){
        this.wybranadata=wybranadata;
    }

    public String pokazgodzinerezerwacji(){
        return wybranagodzina;
    }

    public void przekazgodzinerezerwacji(String wybranagodzina) {
        this.wybranagodzina = wybranagodzina;
    }

    public String pokazEmail(){
        return email;
    }

    public void przekazEmail(String email){
        this.email=email;
    }

    public int pokazilosc(){
        return ilosc;
    }


    public void przekazilosc(int ilosc){
        this.ilosc=ilosc;
    }

    public int pokazstolik(){
        return stolik;
    }

    public void przekazsttolik(int stolik){
        this.stolik=stolik;
    }

    public Boolean pokazwylaczStolik1(){
        return wylaczStolikPierwszy;
    }

    public void przekazwylaczStolik1(Boolean wylaczStolikPierwszy){
        this.wylaczStolikPierwszy=wylaczStolikPierwszy;
    }

    public Boolean pokazwylaczStolik2(){
        return wylaczStolikDrugi;
    }

    public void przekazwylaczStolik2(Boolean wylaczStolikDrugi){
        this.wylaczStolikDrugi=wylaczStolikDrugi;
    }

    public Boolean pokazwylaczStolik4(){
        return wylaczStolikCzwarty;
    }

    public void przekazwylaczStolik4(Boolean wylaczStolikCzwarty){
        this.wylaczStolikCzwarty=wylaczStolikCzwarty;
    }

    public Boolean pokazwylaczStolik5(){
        return wylaczStolikPiaty;
    }

    public void przekazwylaczStolik5(Boolean wylaczStolikPiaty){
        this.wylaczStolikPiaty=wylaczStolikPiaty;
    }
}
