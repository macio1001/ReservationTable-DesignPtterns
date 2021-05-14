package com.example.reservationtabledesignpatterns;

public class Singleton {

    private String wybranadata;
    private String wybranagodzina;
    private String Email;
    private int ilosc;
    private int stolik;
    private Boolean WylaczStolik1;
    private Boolean WylaczStolik2;
    private Boolean WylaczStolik4;
    private Boolean WylaczStolik5;

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
        return Email;
    }

    public void przekazEmail(String Email){
        this.Email=Email;
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
        return WylaczStolik1;
    }

    public void przekazwylaczStolik1(Boolean WylaczStolik1){
        this.WylaczStolik1=WylaczStolik1;
    }

    public Boolean pokazwylaczStolik2(){
        return WylaczStolik2;
    }

    public void przekazwylaczStolik2(Boolean WylaczStolik2){
        this.WylaczStolik2=WylaczStolik2;
    }

    public Boolean pokazwylaczStolik4(){
        return WylaczStolik4;
    }

    public void przekazwylaczStolik4(Boolean WylaczStolik4){
        this.WylaczStolik4=WylaczStolik4;
    }

    public Boolean pokazwylaczStolik5(){
        return WylaczStolik5;
    }

    public void przekazwylaczStolik5(Boolean WylaczStolik5){
        this.WylaczStolik5=WylaczStolik5;
    }
}
