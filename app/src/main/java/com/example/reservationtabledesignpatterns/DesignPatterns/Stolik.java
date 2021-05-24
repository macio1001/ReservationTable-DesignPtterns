package com.example.reservationtabledesignpatterns.DesignPatterns;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Callable;

import androidx.annotation.NonNull;

public class Stolik {
    protected int numer;

    public Stolik(int numer){
        this.numer=numer;
    }

    public Stolik() {
    }

    public String getInformacja() {
        return "Wybrano stolik nr."+this.numer+"!";
    }

    public int getNumer() {
        return this.numer;
    }
}
