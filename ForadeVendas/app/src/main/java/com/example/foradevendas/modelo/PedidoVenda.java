package com.example.foradevendas.modelo;

import com.example.foradevendas.dao.EnderecoDao;

import java.util.ArrayList;

public class PedidoVenda {

    private int codigo;
    private Cliente cliente;
    private Endereco endereco;
    private double vlrTotal;
    private String formaPgto;

    public PedidoVenda() {
    }

    public PedidoVenda(int codigo, Cliente cliente, Endereco endereco, double vlrTotal, String formaPgto) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.endereco = endereco;
        this.vlrTotal = vlrTotal;
        this.formaPgto = formaPgto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }
}
