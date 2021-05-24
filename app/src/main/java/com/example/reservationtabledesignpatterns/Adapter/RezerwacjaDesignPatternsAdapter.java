package com.example.reservationtabledesignpatterns.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.RezerwacjaDesignPatterns;
import com.example.reservationtabledesignpatterns.Activity.RezerwacjaInfoDesignPatternsActivity;
import com.example.reservationtabledesignpatterns.Activity.WyszukiwanieRezerwacjiDesignPatternsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RezerwacjaDesignPatternsAdapter extends RecyclerView.Adapter<RezerwacjaDesignPatternsAdapter.ViewHolder>{

    public List<RezerwacjaDesignPatterns> rezerwacjaList;
    public WyszukiwanieRezerwacjiDesignPatternsActivity wyszukiwanieRezerwacjiDesignPatternsActivity;

    public RezerwacjaDesignPatternsAdapter(WyszukiwanieRezerwacjiDesignPatternsActivity wyszukiwanieRezerwacjiDesignPatternsActivity,List<RezerwacjaDesignPatterns> rezerwacjaList){

        this.rezerwacjaList=rezerwacjaList;
        this.wyszukiwanieRezerwacjiDesignPatternsActivity=wyszukiwanieRezerwacjiDesignPatternsActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemdesignpatterns,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imieText.setText(rezerwacjaList.get(position).getImie());
        holder.nazwiskotext.setText(rezerwacjaList.get(position).getNazwisko());
        holder.stolikText.setText(rezerwacjaList.get(position).getStolik());
        holder.godzinaText.setText(rezerwacjaList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return rezerwacjaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View widok;

        public TextView imieText;
        public TextView nazwiskotext;
        public TextView stolikText;
        public TextView godzinaText;

        public ViewHolder(View itemView){
            super(itemView);
            widok=itemView;

            imieText=(TextView) widok.findViewById(R.id.ImieTextView);
            nazwiskotext=(TextView) widok.findViewById(R.id.NazwiskoTextView);
            stolikText=(TextView) widok.findViewById(R.id.StolikTextVIew);
            godzinaText=(TextView) widok.findViewById(R.id.GodzinaTextView);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            RezerwacjaDesignPatterns rezerwacjaDesignPatterns=rezerwacjaList.get(getAdapterPosition());
            Intent intent=new Intent(wyszukiwanieRezerwacjiDesignPatternsActivity, RezerwacjaInfoDesignPatternsActivity.class);
            intent.putExtra("rezerwacja",rezerwacjaDesignPatterns);
            wyszukiwanieRezerwacjiDesignPatternsActivity.startActivity(intent);
        }
    }
}
