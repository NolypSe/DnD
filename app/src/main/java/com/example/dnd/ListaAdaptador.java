package com.example.dnd;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaAdaptador  extends RecyclerView.Adapter<ListaAdaptador.ViewHolder>{
    private final List<ListElementos> mData;
    private final LayoutInflater mInflater;
    private final Context context;

    public ListaAdaptador(List<ListElementos> itemList, Context context){
        this.mInflater= LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public ListaAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos, null);
        return new ListaAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListaAdaptador.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImgView;
        TextView nombreCompaniero,ciudadCompaniero, claseCompaniero;

        ViewHolder(View itemView){
            super(itemView);
            iconImgView = itemView.findViewById(R.id.iconImgView);
            nombreCompaniero = itemView.findViewById(R.id.nombreCompaniero);
            ciudadCompaniero = itemView.findViewById(R.id.ciudadCompaniero);
            claseCompaniero = itemView.findViewById(R.id.claseCompaniero);
        }

        void bindData(final ListElementos item){
            iconImgView.setImageResource(item.imagen);
            nombreCompaniero.setText(item.getNombre());
            ciudadCompaniero.setText(item.getCiudad());
            claseCompaniero.setText(item.getRaza());
        }



    }
}
