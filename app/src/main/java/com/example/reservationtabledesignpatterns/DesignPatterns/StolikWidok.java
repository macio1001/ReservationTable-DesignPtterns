package com.example.reservationtabledesignpatterns.DesignPatterns;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.reservationtabledesignpatterns.DesignPatterns.Caretaker;
import com.example.reservationtabledesignpatterns.DesignPatterns.Originator;
import com.example.reservationtabledesignpatterns.DesignPatterns.Stolik;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StolikWidok extends Stolik {
    protected Stolik stolik;
    protected CheckBox checkBox;
    private boolean onOff;
    private boolean stolikOn;

    public StolikWidok(Stolik stolik,CheckBox checkBox){
        this.stolik=stolik;
        this.checkBox=checkBox;
    }

    public void setOnChangeListener(){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.ustaw(false);
        caretaker.dodajMemento(originator.zapiszWMemento());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (checkBox.isChecked()) {
                    firebaseFirestore.collection("Stoliknr"+stolik.getNumer()).document("Status").update("OnOff", true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    onOff = originator.ustaw(true);
                                    Log.d(TAG,"Lalala:"+stolik.getNumer()+""+onOff);
                                    caretaker.dodajMemento(originator.zapiszWMemento());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error uptading document", e);
                        }
                    });
                } else {
                    firebaseFirestore.collection("Stoliknr"+stolik.getNumer()).document("Status").update("OnOff", false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    onOff = originator.przywracanieZMemento(caretaker.getMemento(0));
                                    Log.d(TAG,"LalalaPrzywracanie:"+stolik.getNumer()+""+onOff);
                                    Log.d(TAG, "DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error uptading document", e);
                        }
                    });
                }
            }
        });

    }

    public void sprawdzCzyWlaczony(){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Stoliknr"+stolik.getNumer()).document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                stolikOn = documentSnapshot.getBoolean("OnOff");
                                if (stolikOn == true) {
                                    checkBox.setChecked(true);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
}
