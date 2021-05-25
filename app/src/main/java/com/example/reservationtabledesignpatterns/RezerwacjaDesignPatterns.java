package com.example.reservationtabledesignpatterns;

import java.io.Serializable;

public class RezerwacjaDesignPatterns implements Serializable {
    public String documentId;
    private String imie;
    private String nazwisko;
    private String stolik;
    private String godzina;
    private String data;
    private String email;
    private String telefon;
    private String ilosc;
    private String kod;

    public RezerwacjaDesignPatterns(){

    }

    public RezerwacjaDesignPatterns(String imie, String nazwisko, String stolik, String godzina, String ilosc,String telefon,String email,String data){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.stolik=stolik;
        this.godzina=godzina;
        this.ilosc=ilosc;
        this.data=data;
        this.telefon=telefon;
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId=documentId;
    }

    public String getImie(){
        return imie;
    }

    public void setImie(String imie){
        this.imie=imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }

    public String getStolik(){
        return stolik;
    }

    public void setStolik(String stolik){
        this.stolik=stolik;
    }

    public String getGodzina(){
        return godzina;
    }

    public void setGodzina(String Godzina){
        this.godzina=godzina;
    }

    public void setIlosc(String ilosc) {
        ilosc = ilosc;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setTelefon(String telefon) {
        telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public void setData(String data) {
        data = data;
    }

    public String getData() {
        return data;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        kod = kod;
    }
}
