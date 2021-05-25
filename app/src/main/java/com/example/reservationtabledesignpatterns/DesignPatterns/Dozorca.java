package com.example.reservationtabledesignpatterns.DesignPatterns;

import java.util.ArrayList;

public class Dozorca {
    ArrayList<Memento> zapiszStatus=new ArrayList<Memento>();

    public void dodajMemento(Memento memento){
        zapiszStatus.add(memento);
    }

    public Memento getMemento(int index){
        return zapiszStatus.get(index);
    }
}
