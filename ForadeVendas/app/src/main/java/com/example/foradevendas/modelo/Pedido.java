package com.example.foradevendas.modelo;

public class Pedido {

    private int codigo;
    private Item item;
    private int quantidade;

    private PedidoVenda pedidoVenda;

    public Pedido() {
    }

    public Pedido(int codigo, Item item,  PedidoVenda pedidoVenda, int quantidade) {
        this.codigo = codigo;
        this.item = item;
        this.quantidade = quantidade;
        this.pedidoVenda = pedidoVenda;
    }

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
