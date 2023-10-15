package com.example.foradevendas.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foradevendas.R;
import com.example.foradevendas.controller.EnderecoController;
import com.example.foradevendas.modelo.Endereco;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

public class CadastroEnderecoActivity extends AppCompatActivity {

    private EditText edCodigoEnderecoEndereco;
    private EditText edLogradouroEndereco;
    private EditText edNumeroEndereco;
    private EditText edBairroEndereco;
    private EditText edCidadeEndereco;
    private EditText edUfEndereco;

    private Button salvarEndereco;

    private EnderecoController enderecoController;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);
        setTitle("Cadastrar Endereco");
        edCodigoEnderecoEndereco = findViewById(R.id.edCodEnderecoEndereco);
        edLogradouroEndereco = findViewById(R.id.edLogradouroEndereco);
        edNumeroEndereco = findViewById(R.id.edNumeroEndereco);
        edBairroEndereco = findViewById(R.id.edBairroEndereco);
        edCidadeEndereco = findViewById(R.id.edCidadeEndereco);
        edUfEndereco = findViewById(R.id.edUfEndereco);
        salvarEndereco = findViewById(R.id.btSalvarEndereco);
        enderecoController = new EnderecoController(this);

        salvarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarEndereco();
            }
        });
    }


    private void salvarEndereco() {
        //(String logradouro, String numero, String bairro, String cidade, String uf)
        String validacao = enderecoController.validaEndereco(
                edLogradouroEndereco.getText().toString(),
                edNumeroEndereco.getText().toString(),
                edBairroEndereco.getText().toString(),
                edCidadeEndereco.getText().toString(),
                edUfEndereco.getText().toString());


        if (!validacao.equals("")){
            if(validacao.toUpperCase().contains("LOGRADOURO")){
                edLogradouroEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("NUMERO")){
                edNumeroEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("BAIRRO")){
                edBairroEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("CIDADE")){
                edCidadeEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("UF")){
                edUfEndereco.setError(validacao);
            }
        }else {
            Long retorno = Long.valueOf(0);
            if (edCodigoEnderecoEndereco.getText().toString()== null || edCodigoEnderecoEndereco.getText().toString().equals("")){

                ArrayList<Endereco> enderecos = enderecoController.retornarTodosEnderecos();

                retorno = enderecoController.salvarEndereco(
                        enderecos.size() +1,
                        edLogradouroEndereco.getText().toString(),
                        edNumeroEndereco.getText().toString(),
                        edBairroEndereco.getText().toString(),
                        edCidadeEndereco.getText().toString(),
                        edUfEndereco.getText().toString()
                );
                if (retorno > 0){
                    Toast.makeText(this, "Endereco cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Erro ao cadastrar endereco, verifique LOG", Toast.LENGTH_SHORT).show();
                }
            }else {
                retorno = enderecoController.atualizarEndereco(
                        Integer.parseInt(edCodigoEnderecoEndereco.getText().toString()),
                        edLogradouroEndereco.getText().toString(),
                        edNumeroEndereco.getText().toString(),
                        edBairroEndereco.getText().toString(),
                        edCidadeEndereco.getText().toString(),
                        edUfEndereco.getText().toString()
                );
                if (retorno > 0){
                    Toast.makeText(this, "Endereco atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Erro ao atualizar endereco, verifique LOG", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

