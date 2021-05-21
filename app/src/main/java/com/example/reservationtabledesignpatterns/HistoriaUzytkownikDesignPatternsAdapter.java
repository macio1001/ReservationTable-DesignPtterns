package com.example.reservationtabledesignpatterns;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriaUzytkownikDesignPatternsAdapter extends RecyclerView.Adapter<HistoriaUzytkownikDesignPatternsAdapter.ViewHolder>{
    public List<RezerwacjaDesignPatterns> historiaUzytkownikList;
    public HistoriaUzytkownikDesignPatternsActivity historiaUzytkownikDesignPatternsActivity;

    public HistoriaUzytkownikDesignPatternsAdapter(HistoriaUzytkownikDesignPatternsActivity historiaUserDesignPatternsActivity,List<RezerwacjaDesignPatterns> historiaUserList){
        this.historiaUzytkownikDesignPatternsActivity=historiaUserDesignPatternsActivity;
        this.historiaUzytkownikList=historiaUserList;
    }

    @NonNull
    @Override
    public HistoriaUzytkownikDesignPatternsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemdesignpatterns,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaUzytkownikDesignPatternsAdapter.ViewHolder holder, int position) {
        holder.imieText.setText(historiaUzytkownikList.get(position).getImie());
        holder.nazwiskotext.setText(historiaUzytkownikList.get(position).getNazwisko());
        holder.stolikText.setText(historiaUzytkownikList.get(position).getStolik());
        holder.godzinaText.setText(historiaUzytkownikList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return historiaUzytkownikList.size();
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
            RezerwacjaDesignPatterns rezerwacjaDesignPatterns=historiaUzytkownikList.get(getAdapterPosition());
            Intent intent=new Intent(historiaUzytkownikDesignPatternsActivity, RezerwacjaInfoDesignPatternsActivity.class);
            intent.putExtra("rezerwacja",rezerwacjaDesignPatterns);
            historiaUzytkownikDesignPatternsActivity.startActivity(intent);
        }
    }
}
