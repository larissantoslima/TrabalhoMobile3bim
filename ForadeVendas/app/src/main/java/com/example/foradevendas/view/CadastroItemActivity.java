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
import com.example.foradevendas.controller.ItemController;
import com.example.foradevendas.modelo.Endereco;
import com.example.foradevendas.modelo.Item;

import java.util.ArrayList;

public class CadastroItemActivity extends AppCompatActivity {

    private EditText edCodigoItem;
    private EditText edDescricaoItem;
    private EditText edVlrUnitItem;
    private EditText edUniMedidaItem;
    private ItemController itemController;

    private EnderecoController EnderecoController;
    private Button btSalvarItem;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroitem);
        setTitle("Cadastrar Item");
        edCodigoItem = findViewById(R.id.edCodigoItem);
        edDescricaoItem = findViewById(R.id.edDescricaoItem);
        edVlrUnitItem = findViewById(R.id.edVlrUnitItem);
        edUniMedidaItem = findViewById(R.id.edUniMedidaItem);
        btSalvarItem = findViewById(R.id.btSalvarItem);
        itemController = new ItemController(this);

        btSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItem();
            }
        });
    }



    private void salvarItem() {
        String validacao = itemController.validaItem(
                edDescricaoItem.getText().toString(),
                edVlrUnitItem.getText().toString(),
                edUniMedidaItem.getText().toString());


        if (!validacao.equals("")) {
            if (validacao.toUpperCase().contains("CODIGO")) {
                edCodigoItem.setError(validacao);
            }
            if (validacao.toUpperCase().contains("DESCRICAO")) {
                edDescricaoItem.setError(validacao);
            }
            if (validacao.toUpperCase().contains("VLRUNIT")) {
                edVlrUnitItem.setError(validacao);
            }
            if (validacao.toUpperCase().contains("UNIMEDIDA")) {
                edUniMedidaItem.setError(validacao);
            }
        } else {
            Long retorno = Long.valueOf(0);
            if (edCodigoItem.getText().toString() == null || edCodigoItem.getText().toString().equals("")) {

                ArrayList<Item> items = itemController.retornarTodosItens();
                retorno = itemController.salvarItem(
                        items.size() + 1,
                        edDescricaoItem.getText().toString(),
                        edVlrUnitItem.getText().toString(),
                        edUniMedidaItem.getText().toString()
                        );
                if (retorno > 0) {
                    Toast.makeText(this, "Item cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao cadastrar item, verifique LOG", Toast.LENGTH_SHORT).show();
                }
            } else {
                retorno = itemController.atualizarItem(
                        Integer.parseInt(edCodigoItem.getText().toString()),
                        edDescricaoItem.getText().toString(),
                        edVlrUnitItem.getText().toString(),
                        edUniMedidaItem.getText().toString()
                        );
                if (retorno > 0) {
                    Toast.makeText(this, "Item atualizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao atualizar item, verifique LOG", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
