package com.example.foradevendas.controller;

import android.content.Context;

import com.example.foradevendas.dao.PedidoDao;
import com.example.foradevendas.dao.PedidoDao;
import com.example.foradevendas.modelo.Pedido;
import com.example.foradevendas.modelo.Pedido;
import com.example.foradevendas.modelo.PedidoVenda;

import java.util.ArrayList;

public class PedidoController {

    private Context context;

    private ItemController itemController = new ItemController(context);
    private PedidoVendaController pedidoVendaController = new PedidoVendaController(context);
    public PedidoController(Context context){
        this.context = context;
    }
    public long salvarPedido(String codigo, String codItem, String codVenda, String quantidade){
        Pedido pedido = new Pedido(Integer.parseInt(codigo), itemController.retornarItem(Integer.parseInt(codItem)),
                pedidoVendaController.retornaVenda(Integer.parseInt(codVenda)),
                Integer.parseInt(quantidade));
        return PedidoDao.getInstance(context).insert(pedido);
    }

    public long atualizarPedido(String codigo, String codItem, String codVenda, String quantidade){
        Pedido pedido = new Pedido(Integer.parseInt(codigo), itemController.retornarItem(Integer.parseInt(codItem)),
                pedidoVendaController.retornaVenda(Integer.parseInt(codVenda)),
                Integer.parseInt(quantidade));

        return PedidoDao.getInstance(context).update(pedido);
    }

    public long apagarPedido(String codigo, String codItem, String codVenda, String quantidade){
        Pedido pedido = new Pedido(Integer.parseInt(codigo), itemController.retornarItem(Integer.parseInt(codItem)),
                pedidoVendaController.retornaVenda(Integer.parseInt(codVenda)),
                Integer.parseInt(quantidade));
        return PedidoDao.getInstance(context).delete(pedido);
    }

    public ArrayList<Pedido> retornarTodosPedidos(){
        return PedidoDao.getInstance(context).getAll();
    }

    public Pedido retornarPedido(int codigo){
        return PedidoDao.getInstance(context).getById(codigo);
    }

    public String validaPedido(Integer codigo, String codItem, String codVenda, String quantidade){
        String mensagem = "";
        if (codigo == null || codigo == 0){
            mensagem = "Pedido do Cliente deve ser preenchido";
        }else {
            try{
                if(codigo <=0){
                    mensagem += "O pedido deve ser informado!";
                }
            }catch (NumberFormatException ex){
                mensagem += "Pedido do cliente devem ser caracteres validos";
            }
        }
        return mensagem;
    }
}
