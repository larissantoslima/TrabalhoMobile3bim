package com.example.foradevendas.controller;

import android.content.Context;

import com.example.foradevendas.dao.ClienteDao;
import com.example.foradevendas.modelo.Cliente;
import com.example.foradevendas.modelo.Endereco;

import java.util.ArrayList;

public class ClienteController {

    private Context context;
    private EnderecoController enderecoController = new EnderecoController(context);

    public ClienteController(Context context) {
        this.context = context;
    }

    public long salvarCliente(String codigo, String dtNasc, String nome, String cpf, String codEndereco){
        Cliente cliente = new Cliente(Integer.parseInt(codigo), (nome), (cpf), (dtNasc), (enderecoController.retornarEndereco(Integer.parseInt(codEndereco))));
        return ClienteDao.getInstance(context).insert(cliente);
    }
    public long atualizarCliente(Integer codigo, String nome, String cpf, String dtNasc, Integer codEndereco){
        Cliente cliente = new Cliente((codigo), (nome), (cpf), (dtNasc), (enderecoController.retornarEndereco(codEndereco)));
        return ClienteDao.getInstance(context).update(cliente);
    }
    public long apagarCliente(Integer codigo, String nome, String cpf, String dtNasc, Integer codEndereco){
        Cliente cliente = new Cliente((codigo), (nome), (cpf), (dtNasc), (enderecoController.retornarEndereco(codEndereco)));
        return ClienteDao.getInstance(context).delete(cliente);

    }
    public ArrayList<Cliente> retornarTodosClientes(){
        return ClienteDao.getInstance(context).getAll();
    }
    public Cliente retornarCliente(int codigo){
        return ClienteDao.getInstance(context).getById(codigo);

    }
    public  String validaCliente(String nome, String cpf, String dtNasc, Integer codEndereco){
        String mensagem = "";
        if (dtNasc == null || dtNasc.isEmpty()){
            mensagem += "O logradouro deve ser informado";
        }
        if (nome == null || nome.isEmpty()){
            mensagem += "O numero deve ser informado";
        }
        if (cpf == null || cpf.isEmpty()){
            mensagem += "O bairro deve ser informado";
        }
        return mensagem;
    }
    public String validaCpf(Integer codigo, String nome, String cpf, String dtNasc, Integer codEndereco){
        String mensagem = "";
        if (cpf == null || cpf.isEmpty()){
            mensagem = "CPF do cliente deve ser preenchido";
        }else{
            try{
                if (Integer.parseInt(cpf)<=0){
                    mensagem += "O CPF deve ser informado!";
                }
            }catch (NumberFormatException ex){
                mensagem += "CPF do cliente devem ser números validos!\n";
            }
        }

        return mensagem;
    }
}



