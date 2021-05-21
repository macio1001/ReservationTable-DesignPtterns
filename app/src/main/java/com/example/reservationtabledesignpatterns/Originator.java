package com.example.reservationtabledesignpatterns;

import android.util.Log;

public class Originator {
    private Boolean status;

    public Boolean ustaw(Boolean nowystatus){
        Log.d("Zmiana statusu:","Nowy status"+nowystatus);
        this.status=nowystatus;
        return nowystatus;
    }

    public Memento zapiszwMemento(){
        Log.d("Zmiana statusu:","Zapisywanie do Memento");
        return new Memento(status);
    }

    public Boolean przywracaniezMemento(Memento memento){
        status=memento.getZapiszstatus();
        Log.d("Zmiana statusu:","'Przywrazanie z Memento"+status);
        return status;
    }
}
