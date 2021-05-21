package com.example.reservationtabledesignpatterns;

public class Memento {
    private Boolean status;

    public Memento(Boolean zapiszstatus){
        status=zapiszstatus;
    }

    public Boolean getZapiszstatus(){
        return status;
    }
}
