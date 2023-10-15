package com.example.foradevendas.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {
    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER, NOME VARCHAR(100), CPF VARCHAR(11), DTNASC VARCHAR(10), CODENDERECO INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (CODIGO INTEGER, LOGRADOURO VARCHAR(100), NUMERO VARCHAR(10), BAIRRO VARCHAR(100), CIDADE VARCHAR(50), UF VARCHAR(2))");
        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER, DESCRICAO VARCHAR(200), VLRUNITARIO INTEGER, UNMEDIDA VARCHAR(20))");
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO (CODIGO INTEGER, CODITEM INTEGER, CODVENDA INTEGER, QUANTIDADE INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDOVENDA (CODIGO INTEGER, CODCLIENTE INTEGER, CODENDERECO INTEGER, VLRTOTAL INTEGER, FORMAPGTO VARCHAR(10))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
