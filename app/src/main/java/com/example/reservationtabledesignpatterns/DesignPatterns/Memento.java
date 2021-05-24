package com.example.reservationtabledesignpatterns.DesignPatterns;

public class Memento {
    private Boolean status;

    public Memento(Boolean zapiszStatus){
        status=zapiszStatus;
    }

    public Boolean getZapiszStatus(){
        return status;
    }
}
