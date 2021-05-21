package com.example.reservationtabledesignpatterns;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    ArrayList<Memento> zapiszstatus=new ArrayList<Memento>();

    public void dodajMemento(Memento memento){
        zapiszstatus.add(memento);
    }

    public Memento getMemento(int index){
        return zapiszstatus.get(index);
    }
}
