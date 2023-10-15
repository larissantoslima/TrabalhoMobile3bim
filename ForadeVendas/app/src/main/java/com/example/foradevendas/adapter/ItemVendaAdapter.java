package com.example.foradevendas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foradevendas.R;
import com.example.foradevendas.modelo.Item;
import com.example.foradevendas.modelo.Pedido;
import com.example.foradevendas.modelo.PedidoVenda;

import java.util.ArrayList;

public class ItemVendaAdapter extends
        RecyclerView.Adapter<ItemVendaAdapter.ViewHolder> {

    private ArrayList<Pedido> listaItems;
    private Context context;

    public ItemVendaAdapter(ArrayList<Pedido> listaItems, Context context) {
        this.listaItems = listaItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * Método responsável em carregar o arquivo de layout na tela
         */
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_venda,
                parent, false);
        return new ViewHolder(listItem);
    }

    /**Método que adiciona dados nos campos da tela**/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvItemVendaGrid.setText(listaItems.get(position).getItem().getDescricao());
        holder.tvQuantidadeVendaGrid.setText(String.valueOf(listaItems.get(position).getQuantidade()));
    }

    /**Retorna a quantidade de dados existente na lista**/
    @Override
    public int getItemCount() {
        return this.listaItems.size();
    }

    /**Classe que vincula o componente do xml para ser manipulado**/
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvItemVendaGrid;
        public TextView tvQuantidadeVendaGrid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvItemVendaGrid = itemView.findViewById(R.id.tvItemVendaGrid);
            this.tvQuantidadeVendaGrid = itemView.findViewById(R.id.tvItemVendaGrid);
        }
    }

}