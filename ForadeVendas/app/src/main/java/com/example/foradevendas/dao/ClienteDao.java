package com.example.foradevendas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foradevendas.controller.EnderecoController;
import com.example.foradevendas.helper.SQLiteDataHelper;
import com.example.foradevendas.modelo.Cliente;

import java.util.ArrayList;

public class ClienteDao implements GenericDao<Cliente> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private  String[]colunas = {"CODIGO", "NOME", "CPF", "DTNASC", "CODENDERECO"};

    private String tableName = "CLIENTE";

    private Context context;

    private static ClienteDao instancia;
    private EnderecoController enderecoController = new EnderecoController(context);

    public static ClienteDao getInstance(Context context){
        if(instancia == null)
            return instancia = new ClienteDao(context);
        else
            return instancia;
    }

    public ClienteDao(Context context){
        this.context = context;
        //Carregando a base de dados.
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null,1);
        //dando permissão de escrever dentro da base de dados
        bd = openHelper.getReadableDatabase();
        //enderecoController = new EnderecoController(context);
    }

    @Override
    public long insert(Cliente obj) {
        try{
            //CREATE TABLE CLIENTE (CODIGO INTEGER, NOME VARCHAR(100), CPF VARCHAR(11), DTNASC VARCHAR(10), CODENDERECO INTEGER)");
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("DTNASC", obj.getDtNasc());
            valores.put("CODENDERECO", obj.getEndereco().getCodigo());

            return bd.insert(tableName,null,valores);
        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.insert():"+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Cliente obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("DTNASC", obj.getDtNasc());
            valores.put("CODENDERECO", obj.getEndereco().getCodigo());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("update", ex.getMessage());
        }
        return - 1;

    }

    @Override
    public long delete(Cliente obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDAO.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Cliente> getAll() {

        ArrayList<Cliente> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null,"CODIGO asc");
            if(cursor.moveToFirst()){
                do{
                    //private  String[]colunas = {"CODIGO", "NOME", "CPF", "DTNASC", "CODENDERECO"};
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(cursor.getInt(0));
                    cliente.setNome(cursor.getString(1));
                    cliente.setCpf(cursor.getString(2));
                    cliente.setDtNasc(cursor.getString(3));
                    cliente.setEndereco(enderecoController.retornarEndereco(cursor.getInt(4)));

                    lista.add(cliente);

                }while (cursor.moveToNext());
            }


        }catch (SQLException ex){

            Log.e("ERRO", "AlunoDao.getAll(): "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null, null, null);
            //private  String[]colunas = {"CODIGO", "NOME", "CPF", "DTNASC", "CODENDERECO"};

            if (cursor.moveToFirst()){
                Cliente cliente = new Cliente();
                cliente.setCodigo(cursor.getInt(0));
                cliente.setNome(cursor.getString(1));
                cliente.setCpf(cursor.getString(2));
                cliente.setDtNasc(cursor.getString(3));
                cliente.setEndereco(enderecoController.retornarEndereco(cursor.getInt(4)) );

                return cliente;
            }
        }catch (SQLException ex){

            Log.e("ERRO", "AlunoDao.getAll(): "+ex.getMessage());
        }
        return null;

    }
}
