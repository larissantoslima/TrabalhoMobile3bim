package com.example.foradevendas.controller;

import android.content.Context;

import com.example.foradevendas.dao.PedidoVendaDao;
import com.example.foradevendas.modelo.PedidoVenda;

import java.util.ArrayList;

public class PedidoVendaController  {

    private Context context;

    private EnderecoController enderecoController = new EnderecoController(context);

    private ClienteController clienteController = new ClienteController(context);;

    //private ItemController itemController = new ItemController(context);
    //private PedidoVendaController pedidoVendaController = new PedidoVendaController(context);
    public PedidoVendaController(Context context){
        this.context = context;
        //clienteController = new ClienteController(context);
        //itemController = new ItemController(context);
        //pedidoVendaController = new PedidoVendaController(context);
    }
    public long salvarPedidoVenda(String codigo, String codCliente, String codEndereco, String vlrTotal, String formaPgto){
        PedidoVenda pedidoVenda = new PedidoVenda(Integer.parseInt(codigo),
                clienteController.retornarCliente(Integer.parseInt(codCliente)),
                enderecoController.retornarEndereco(Integer.parseInt(codEndereco)),
                Double.parseDouble(vlrTotal),
                (formaPgto));
        return PedidoVendaDao.getInstance(context).insert(pedidoVenda);
    }

    public long atualizarPedidoVenda(String codigo, String codCliente, String codEndereco, String vlrTotal, String formaPgto){
        PedidoVenda pedidoVenda = new PedidoVenda(Integer.parseInt(codigo),
                clienteController.retornarCliente(Integer.parseInt(codCliente)),
                enderecoController.retornarEndereco(Integer.parseInt(codEndereco)),
                Double.parseDouble(vlrTotal),
                (formaPgto));

        return PedidoVendaDao.getInstance(context).update(pedidoVenda);
    }

    public long apagarPedidoVenda(String codigo, String codCliente, String codEndereco, String vlrTotal, String formaPgto){
        PedidoVenda pedidoVenda = new PedidoVenda(Integer.parseInt(codigo),
                clienteController.retornarCliente(Integer.parseInt(codCliente)),
                enderecoController.retornarEndereco(Integer.parseInt(codEndereco)),
                Double.parseDouble(vlrTotal),
                (formaPgto));
        return PedidoVendaDao.getInstance(context).delete(pedidoVenda);
    }

    public ArrayList<PedidoVenda> retornarTodosPedidosVenda(){
        return PedidoVendaDao.getInstance(context).getAll();
    }

    public PedidoVenda retornaVenda(int codigo){
        return PedidoVendaDao.getInstance(context).getById(codigo);
    }
}
