package com.example.reservationtabledesignpatterns.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reservationtabledesignpatterns.Activity.HistoriaDesignPatternsActivity;
import com.example.reservationtabledesignpatterns.R;
import com.example.reservationtabledesignpatterns.RezerwacjaDesignPatterns;
import com.example.reservationtabledesignpatterns.Activity.RezerwacjaInfoDesignPatternsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriaDesignPatternsAdapter extends RecyclerView.Adapter<HistoriaDesignPatternsAdapter.ViewHolder>{
    public List<RezerwacjaDesignPatterns> historiaList;
    public HistoriaDesignPatternsActivity historiaDesignPatternsActivity;

    public HistoriaDesignPatternsAdapter(HistoriaDesignPatternsActivity historiaDesignPatternsActivity,List<RezerwacjaDesignPatterns> historiaList){
        this.historiaList=historiaList;
        this.historiaDesignPatternsActivity=historiaDesignPatternsActivity;
    }

    @NonNull
    @Override
    public HistoriaDesignPatternsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemdesignpatterns,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaDesignPatternsAdapter.ViewHolder holder, int position) {
        holder.imieText.setText(historiaList.get(position).getImie());
        holder.nazwiskotext.setText(historiaList.get(position).getNazwisko());
        holder.stolikText.setText(historiaList.get(position).getStolik());
        holder.godzinaText.setText(historiaList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return historiaList.size();
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
        public void onClick(View view) {
            RezerwacjaDesignPatterns rezerwacjaDesignPatterns =historiaList.get(getAdapterPosition());
            Intent intent=new Intent(historiaDesignPatternsActivity, RezerwacjaInfoDesignPatternsActivity.class);
            intent.putExtra("rezerwacja",rezerwacjaDesignPatterns);
            historiaDesignPatternsActivity.startActivity(intent);
        }
    }
}
