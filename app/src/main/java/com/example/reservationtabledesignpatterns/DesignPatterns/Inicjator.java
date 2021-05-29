package com.example.reservationtabledesignpatterns.DesignPatterns;

import android.util.Log;

public class Inicjator {
    private Boolean status;

    public Boolean ustaw(Boolean nowyStatus){
        Log.d("Zmiana statusu:","Nowy status"+nowyStatus);
        this.status=nowyStatus;
        return nowyStatus;
    }

    public Memento zapiszWMemento(){
        Log.d("Zmiana statusu:","Zapisywanie do Memento");
        return new Memento(status);
    }

    public Boolean przywracanieZMemento(Memento memento){
        status=memento.getZapiszStatus();
        Log.d("Zmiana statusu:","'Przywrazanie z Memento"+status);
        return status;
    }
}
