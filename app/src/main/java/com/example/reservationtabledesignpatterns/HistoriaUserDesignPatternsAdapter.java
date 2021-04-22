package com.example.reservationtabledesignpatterns;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriaUserDesignPatternsAdapter extends RecyclerView.Adapter<HistoriaUserDesignPatternsAdapter.ViewHolder>{
    public List<RezerwacjaDesignPatterns> historiaUserList;
    public HistoriaUserDesignPatternsActivity historiaUserDesignPatternsActivity;

    public HistoriaUserDesignPatternsAdapter(HistoriaUserDesignPatternsActivity historiaUserActivity,List<RezerwacjaDesignPatterns> historiaUserList){
        this.historiaUserDesignPatternsActivity=historiaUserDesignPatternsActivity;
        this.historiaUserList=historiaUserList;
    }

    @NonNull
    @Override
    public HistoriaUserDesignPatternsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemdesignpatterns,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaUserDesignPatternsAdapter.ViewHolder holder, int position) {
        holder.imieText.setText(historiaUserList.get(position).getImie());
        holder.nazwiskotext.setText(historiaUserList.get(position).getNazwisko());
        holder.stolikText.setText(historiaUserList.get(position).getStolik());
        holder.godzinaText.setText(historiaUserList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return historiaUserList.size();
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
            RezerwacjaDesignPatterns rezerwacjaDesignPatterns=historiaUserList.get(getAdapterPosition());
            Intent intent=new Intent(historiaUserDesignPatternsActivity, RezerwacjaInfoDesignPatternsActivity.class);
            intent.putExtra("rezerwacja",rezerwacjaDesignPatterns);
            historiaUserDesignPatternsActivity.startActivity(intent);
        }
    }
}
