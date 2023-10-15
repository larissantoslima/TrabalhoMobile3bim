package com.example.foradevendas.modelo;

public class Cliente {

    private Integer codigo;
    private String nome;
    private String cpf;
    private String dtNasc;

    private Endereco endereco;

    public Cliente(int codigo, String nome, String cpf, String dtNasc,Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.endereco = endereco;
    }

    public Cliente() {
    }

    public int getCodigo() {

        return codigo;
    }

    public void setCodigo(int codigo) {

        this.codigo = codigo;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getDtNasc() {

        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {

        this.dtNasc = dtNasc;
    }

    public Endereco getEndereco() {

        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
