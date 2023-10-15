package com.example.foradevendas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foradevendas.helper.SQLiteDataHelper;
import com.example.foradevendas.modelo.Cliente;
import com.example.foradevendas.modelo.Item;

import java.util.ArrayList;

public class ItemDao implements GenericDao<Item> {

    //Abrir conexão com BD

    private SQLiteOpenHelper openHelper;
    //Base de Dados
    private SQLiteDatabase bd;
    //Nome das colunas da tabela
    //        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER, DESCRICAO VARCHAR(200), VLRUNITARIO INTEGER, UNMEDIDA VARCHAR(20))");
    private String[]colunas = {"CODIGO", "DESCRICAO", "VLRUNITARIO", "UNMEDIDA"};

    private String tableName = "ITEM";

    private static ItemDao instancia;
    private Context context;

    public static ItemDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new ItemDao(context);
        else
            return instancia;
    }

    public ItemDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getReadableDatabase();
    }
    @Override
    public long insert(Item obj) {
        try{
            //        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER, DESCRICAO VARCHAR(200), VLRUNITARIO INTEGER, UNMEDIDA VARCHAR(20))");
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VLRUNITARIO", obj.getVlrUnit());
            valores.put("UNMEDIDA", obj.getUnMedida());

            return bd.insert(tableName, null, valores);
        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.insert(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Item obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("UNMEDIDA", obj.getUnMedida());
            valores.put("VLRUNIT", obj.getVlrUnit());


            String[] identificador = {String.valueOf(obj.getCodigo())};
            return bd.update(tableName, valores,"CODIGO = ?", identificador);
        }catch(SQLException ex){
            Log.e("update", ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Item obj) {
        try {
            String[] identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas, null, null, null,null, "CODIGO asc");

            if(cursor.moveToFirst()){
                do{
                    Item item = new Item();
                    item.setCodigo(cursor.getInt(0));
                    item.setDescricao(cursor.getString(1));
                    item.setUnMedida(cursor.getString(2));
                    item.setVlrUnit(cursor.getInt(3));

                    lista.add(item);
                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.getAll(): "+ex.getMessage());
        }
        return lista;

    }

    @Override
    public Item getById(int id) {
       try{
           String[]identificador = {String.valueOf(id)};

           //Cursor cursor = bd.query(tableName, colunas,"CODIGO = ?", identificador,null,null,null);
           Cursor cursor = bd.query(tableName, colunas,"CODIGO = ?", identificador,null,null, "CODIGO asc");

           if(cursor.moveToFirst()){
               Item item = new Item();
               item.setCodigo(cursor.getInt(0));
               item.setDescricao(cursor.getString(1));
               item.setUnMedida(cursor.getString(2));
               item.setVlrUnit(cursor.getInt(3));

               return item;
           }else{
               System.out.println("teste");
           }


       }catch (SQLException ex){
           Log.e("ERRO", "ItemDao.getAll(): "+ex.getMessage());
       }
       return null;
    }
}
