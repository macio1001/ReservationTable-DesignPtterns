package com.example.reservationtabledesignpatterns.DesignPatterns;

public class Memento {
    private Boolean status;

    public Memento(Boolean zapiszstatus){
        status=zapiszstatus;
    }

    public Boolean getZapiszstatus(){
        return status;
    }
}
