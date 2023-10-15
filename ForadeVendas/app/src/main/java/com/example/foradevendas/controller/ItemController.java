package com.example.foradevendas.controller;

import android.content.Context;

import com.example.foradevendas.dao.ItemDao;
import com.example.foradevendas.modelo.Item;

import java.util.ArrayList;

public class ItemController {

    private Context context;

    public ItemController(Context context){
        this.context = context;
    }
    public long salvarItem(Integer codigo, String descricao, String vlrUnit, String unMedida){
        Item item = new Item((codigo), (descricao), Double.parseDouble(vlrUnit), (unMedida));
        return ItemDao.getInstancia(context).insert(item);
    }
    public long atualizarItem(Integer codigo, String descricao, String vlrUnit, String unMedida){
        Item item = new Item((codigo), (descricao), Double.parseDouble(vlrUnit), (unMedida));
        return ItemDao.getInstancia(context).update(item);
    }
    public long apagarItem(Integer codigo, String descricao, Double vlrUnit, String unMedida){
        Item item = new Item((codigo), (descricao), (vlrUnit), (unMedida));
        return ItemDao.getInstancia(context).delete(item);
    }
    public ArrayList<Item> retornarTodosItens(){
        return ItemDao.getInstancia(context).getAll();
    }
    public Item retornarItem(int codigo){
        return ItemDao.getInstancia(context).getById(codigo);
    }
    public String validaItem(String descricao, String vlrUnit, String unMedida){
        String mensagem = "";

        if (descricao == null || descricao.isEmpty()){
            mensagem += "A descricao deve ser informada";
        }
        if (vlrUnit == null || vlrUnit.isEmpty()){
            mensagem += "O valor unitario deve ser informado";
        }
        if (unMedida == null || unMedida.isEmpty()){
            mensagem += "A unidade medida deve ser informada";
        }

        return mensagem;
    }

    }


