package com.example.foradevendas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foradevendas.controller.ClienteController;
import com.example.foradevendas.controller.EnderecoController;
import com.example.foradevendas.controller.ItemController;
import com.example.foradevendas.controller.PedidoVendaController;
import com.example.foradevendas.helper.SQLiteDataHelper;
import com.example.foradevendas.modelo.Pedido;
import com.example.foradevendas.modelo.PedidoVenda;

import java.util.ArrayList;

public class PedidoVendaDao implements GenericDao<PedidoVenda>{


    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private  String[]colunas = {"CODIGO", "CODCLIENTE", "CODENDERECO", "VLRTOTAL", "FORMAPGTO"};

    private String tableName = "PEDIDOVENDA";

    private static Context context;

    private static PedidoVendaDao instancia;
    private ClienteController clienteController = new ClienteController(context);;

    private EnderecoController enderecoController = new EnderecoController(context);


    public static PedidoVendaDao getInstance(Context context){
        if(instancia == null)
            return instancia = new PedidoVendaDao(context);
        else
            return instancia;
    }

    public PedidoVendaDao(Context context){
        this.context = context;
        //Carregando a base de dados.
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null,1);
        //dando permissão de escrever dentro da base de dados
        bd = openHelper.getReadableDatabase();
        //itemController = new ItemController(context);
        //enderecoController = new EnderecoController(context);
        //pedidoVendaController = new PedidoVendaController(context);
        //clienteController = new ClienteController(context);
    }

    @Override
    public long insert(PedidoVenda obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("CODCLIENTE", obj.getCliente().getCodigo());
            valores.put("CODENDERECO", obj.getEndereco().getCodigo());
            valores.put("VLRTOTAL", obj.getVlrTotal());
            valores.put("FORMAPGTO", obj.getFormaPgto());


            return bd.insert(tableName,null,valores);
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaDao.insert():"+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(PedidoVenda obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("CODCLIENTE", obj.getCliente().getCodigo());
            valores.put("CODENDERECO", obj.getEndereco().getCodigo());
            valores.put("VLRTOTAL", obj.getVlrTotal());
            valores.put("FORMAPGTO", obj.getFormaPgto());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("update", ex.getMessage());
        }
        return - 1;

    }

    @Override
    public long delete(PedidoVenda obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaDao.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<PedidoVenda> getAll() {

        ArrayList<PedidoVenda> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null,"CODIGO asc");
            if(cursor.moveToFirst()){
                do{
                    //private  String[]colunas = {"CODIGO", "CODCLIENTE", "CODENDERECO", "VLRTOTAL", "FORMAPGTO"};
                    PedidoVenda pedidoVenda = new PedidoVenda();
                    pedidoVenda.setCodigo(cursor.getInt(0));
                    pedidoVenda.setCliente(clienteController.retornarCliente(cursor.getInt(1)));
                    pedidoVenda.setEndereco(enderecoController.retornarEndereco(cursor.getInt(2)));
                    pedidoVenda.setVlrTotal(cursor.getInt(3));
                    pedidoVenda.setFormaPgto(cursor.getString(4));

                    lista.add(pedidoVenda);

                }while (cursor.moveToNext());
            }


        }catch (SQLException ex){

            Log.e("ERRO", "PedidoDao.getAll(): "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public PedidoVenda getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null, null, null);

            if (cursor.moveToFirst()){
                //private  String[]colunas = {"CODIGO", "CODCLIENTE", "CODENDERECO", "VLRTOTAL", "FORMAPGTO"};
                PedidoVenda pedidoVenda = new PedidoVenda();
                pedidoVenda.setCodigo(cursor.getInt(0));
                pedidoVenda.setCliente(clienteController.retornarCliente(cursor.getInt(1)));
                pedidoVenda.setEndereco(enderecoController.retornarEndereco(cursor.getInt(2)));
                pedidoVenda.setVlrTotal(cursor.getInt(3));
                pedidoVenda.setFormaPgto(cursor.getString(4));


                return pedidoVenda;
            }
        }catch (SQLException ex){

            Log.e("ERRO", "PedidoVenda.getAll(): "+ex.getMessage());
        }
        return null;

    }
}
