package com.example.foradevendas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foradevendas.controller.PedidoVendaController;
import com.example.foradevendas.helper.SQLiteDataHelper;
import com.example.foradevendas.modelo.Cliente;
import com.example.foradevendas.modelo.Pedido;
import com.example.foradevendas.modelo.PedidoVenda;

import java.util.ArrayList;

public class PedidoDao implements GenericDao<Pedido>{


    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private  String[]colunas = {"CODIGO", "CODITEM", "CODVENDA", "QUANTIDADE"};

    private String tableName = "PEDIDO";

    private Context context;

    private static PedidoDao instancia;
    private ItemDao itemDao = new ItemDao(context);
    private PedidoVendaController pedidoVendaController = new PedidoVendaController(context);



    public static PedidoDao getInstance(Context context){
        if(instancia == null)
            return instancia = new PedidoDao(context);
        else
            return instancia;
    }

    public PedidoDao(Context context){
        this.context = context;
        //Carregando a base de dados.
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null,1);
        //dando permissão de escrever dentro da base de dados
        bd = openHelper.getReadableDatabase();
        //pedidoVendaController = new PedidoVendaController(context);
    }

    @Override
    public long insert(Pedido obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("CODITEM", obj.getItem().getCodigo());
            valores.put("CODVENDA", obj.getPedidoVenda().getCodigo());
            valores.put("QUANTIDADE", obj.getQuantidade());

            return bd.insert(tableName,null,valores);
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoDao.insert():"+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("CODITEM", obj.getItem().getCodigo());
            valores.put("CODVENDA", obj.getPedidoVenda().getCodigo());
            valores.put("QUANTIDADE", obj.getQuantidade());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("update", ex.getMessage());
        }
        return - 1;

    }

    @Override
    public long delete(Pedido obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoDao.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Pedido> getAll() {

        ArrayList<Pedido> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null,"CODIGO asc");
            if(cursor.moveToFirst()){
                do{
                    //    private  String[]colunas = {"CODIGO", "CODITEM", "CODVENDA", "QUANTIDADE"};
                    Pedido pedido = new Pedido();
                    pedido.setCodigo(cursor.getInt(0));
                    pedido.setItem(itemDao.getById(cursor.getInt(1)));
                    pedido.setPedidoVenda(pedidoVendaController.retornaVenda(cursor.getInt(2)));
                    pedido.setQuantidade(cursor.getInt(3));

                    lista.add(pedido);

                }while (cursor.moveToNext());
            }


        }catch (SQLException ex){

            Log.e("ERRO", "PedidoDao.getAll(): "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Pedido getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null, null, null);

            if (cursor.moveToFirst()){
                //    private  String[]colunas = {"CODIGO", "CODITEM", "CODVENDA", "QUANTIDADE"};
                Pedido pedido = new Pedido();
                pedido.setCodigo(cursor.getInt(0));
                pedido.setItem(itemDao.getById(cursor.getInt(1)));
                pedido.setPedidoVenda(pedidoVendaController.retornaVenda(cursor.getInt(2)));
                pedido.setQuantidade(cursor.getInt(3));

                return pedido;
            }
        }catch (SQLException ex){

            Log.e("ERRO", "Pedido.getAll(): "+ex.getMessage());
        }
        return null;

    }

}
