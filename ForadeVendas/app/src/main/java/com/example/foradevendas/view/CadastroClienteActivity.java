package com.example.foradevendas.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foradevendas.R;
import com.example.foradevendas.controller.ClienteController;
import com.example.foradevendas.controller.EnderecoController;
import com.example.foradevendas.modelo.Cliente;
import com.example.foradevendas.modelo.Endereco;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edCodigoCliente;
    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private EditText edDtNascCliente;
    private EditText edCodEnderecoCliente;
    private Button btSalvarCliente;
    private Spinner spEnderecoCliente;
    private ClienteController clienteController;
    private EnderecoController enderecoController;


    private ArrayAdapter adapterEndereco;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrocliente);
        setTitle("Cadastrar Cliente");
        edCodigoCliente = findViewById(R.id.edCodigoCliente);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edDtNascCliente = findViewById(R.id.edDtNascCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        edCodEnderecoCliente = findViewById(R.id.edCodEnderecoCliente);
        spEnderecoCliente = findViewById(R.id.spEnderecoCliente);
        btSalvarCliente = findViewById(R.id.btSalvarCliente);
        clienteController = new ClienteController(this);
        enderecoController = new EnderecoController(this);

        ArrayList<Endereco> enderecos = enderecoController.retornarTodosEnderecos();
        String[] vetorEndereco = new String[enderecos.size()];
        for (int i = 0; i < enderecos.size();i++){
            vetorEndereco[i] = enderecos.get(i).getBairro() +", "+enderecos.get(i).getLogradouro()+ ", "+ enderecos.get(i).getNumero() +
                    " ("+enderecos.get(i).getCidade() +"-"+enderecos.get(i).getUf()+")";
        }
        adapterEndereco = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorEndereco);
        spEnderecoCliente.setAdapter(adapterEndereco);
        spEnderecoCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edCodEnderecoCliente.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });
    }




    private void salvarCliente() {
        String validacao = clienteController.validaCliente(
                edNomeCliente.getText().toString(),
                edCpfCliente.getText().toString(),
                edDtNascCliente.getText().toString(),
                Integer.parseInt(edCodEnderecoCliente.getText().toString()));

        if (!validacao.equals("")){
            if(validacao.toUpperCase().contains("NOME")){
                edNomeCliente.setError(validacao);
            }
            if(validacao.toUpperCase().contains("CPF")){
                edCpfCliente.setError(validacao);
            }
            if(validacao.toUpperCase().contains("DATA NASCIMENTO")){
                edDtNascCliente.setError(validacao);
            }
            if(validacao.toUpperCase().contains("CODIGO ENDERECO")) {
                edCodEnderecoCliente.setError(validacao);
            }
        }else {
            System.out.println("1");
            Long retorno = Long.valueOf(0);
            System.out.println("12");

            if (edCodigoCliente.getText().toString()== null || edCodigoCliente.getText().toString().equals("")){

                ArrayList<Cliente> clientes = clienteController.retornarTodosClientes();


                retorno = clienteController.salvarCliente(
                        String.valueOf(clientes.size()+1),
                        edDtNascCliente.getText().toString(),
                        edNomeCliente.getText().toString(),
                        edCpfCliente.getText().toString(),
                        edCodEnderecoCliente.getText().toString()
                );
                if (retorno > 0){
                    Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Erro ao cadastrar cliente, verifique LOG", Toast.LENGTH_SHORT).show();
                }
            }else {
                retorno = clienteController.atualizarCliente(
                        Integer.parseInt(edCodigoCliente.getText().toString()),
                        edNomeCliente.getText().toString(),
                        edCpfCliente.getText().toString(),
                        edDtNascCliente.getText().toString(),
                        Integer.parseInt(edCodEnderecoCliente.getText().toString())
                        );
                if (retorno > 0){
                    Toast.makeText(this, "Cliente atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Erro ao atualizar cliente, verifique LOG", Toast.LENGTH_SHORT).show();
                }
            }



        }
    }
}

