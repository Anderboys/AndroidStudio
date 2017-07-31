package com.example.administrador.myapplication;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Beans.UsuarioBeans;

/**
 * Created by Windows on 17/07/2017.
 */

public class AdapterPersonas

        extends RecyclerView.Adapter<AdapterPersonas.ViewHolderPeronas>
        //1. implemetar
        implements View.OnClickListener
        {

        //datos
        ArrayList<UsuarioBeans>listausuarios;
        //3.
        private View.OnClickListener listener;


    public AdapterPersonas(ArrayList<UsuarioBeans>listausuarios){
        this.listausuarios = listausuarios;
    }

    @Override
    public ViewHolderPeronas onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempersonas,null,false);


        //4. para q escuche el evento de seleccion
        view.setOnClickListener(this);

        return new ViewHolderPeronas(view);


    }

    @Override
    public void onBindViewHolder(ViewHolderPeronas holder, int position) {

        holder.textdocumento.setText(listausuarios.get(position).getDni());
        holder.textnombre.setText(listausuarios.get(position).getNom());
        holder.textapellido.setText(listausuarios.get(position).getApe());

    }

    @Override
    public int getItemCount() {
        return listausuarios.size();
    }


    //5.crear metodo
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    //2.generar metodo onclick
    @Override
    public void onClick(View view) {
        //6.
        if(listener!=null){
            listener.onClick(view);
        }

    }

        public class ViewHolderPeronas extends RecyclerView.ViewHolder{


        TextView textdocumento,textnombre,textapellido;

        public ViewHolderPeronas(View itemView) {
            super(itemView);

            textdocumento = (TextView)itemView.findViewById(R.id.textDocumento);
            textnombre = (TextView)itemView.findViewById(R.id.textNombre);
            textapellido = (TextView)itemView.findViewById(R.id.textTelefono);

        }
    }
}
