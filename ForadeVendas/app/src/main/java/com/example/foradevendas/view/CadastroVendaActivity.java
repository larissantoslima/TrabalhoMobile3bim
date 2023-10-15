package com.example.foradevendas.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foradevendas.R;
import com.example.foradevendas.adapter.ItemVendaAdapter;
import com.example.foradevendas.controller.ClienteController;
import com.example.foradevendas.controller.EnderecoController;
import com.example.foradevendas.controller.ItemController;
import com.example.foradevendas.controller.PedidoController;
import com.example.foradevendas.controller.PedidoVendaController;
import com.example.foradevendas.modelo.Cliente;
import com.example.foradevendas.modelo.Endereco;
import com.example.foradevendas.modelo.Item;
import com.example.foradevendas.modelo.Pedido;
import com.example.foradevendas.modelo.PedidoVenda;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CadastroVendaActivity extends AppCompatActivity {

    private Spinner spEnderecoVenda;
    private EditText edEnderecoVenda;
    private Spinner spClienteVenda;
    private EditText edClienteVenda;
    private Spinner spItemVenda;
    private EditText edItemVenda;
    private RadioGroup radioGroupVenda;
    private Spinner spQtdeParcelas;
    private EditText edValorTotalVenda;
    private Button btAdicionarItemVenda;
    private Button btSalvarVenda;
    private EditText edCodigoVenda;
    private RecyclerView rvItemVenda;
    private EditText edQuantidadeItem;

    private ClienteController clienteController;
    private EnderecoController enderecoController;
    private ItemController itemController;
    private PedidoVendaController pedidoVendaController;
    private PedidoController pedidoController;

    private ArrayAdapter adapterItem;
    private ArrayAdapter adapterCliente;
    private ArrayAdapter adapterEndereco;
    private ArrayAdapter adapterParcelas;
    private ArrayList<Pedido> itensVenda = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_venda);
        setTitle("Cadastrar Venda");
        spEnderecoVenda = findViewById(R.id.spEnderecoVenda);
        edEnderecoVenda = findViewById(R.id.edEnderecoVenda);
        spClienteVenda = findViewById(R.id.spClienteVenda);
        edClienteVenda = findViewById(R.id.edClienteVenda);
        spItemVenda = findViewById(R.id.spItemVenda);
        edItemVenda = findViewById(R.id.edItemVenda);
        radioGroupVenda = findViewById(R.id.radioGroupVenda);
        spQtdeParcelas = findViewById(R.id.spQtdeParcelas);
        edValorTotalVenda = findViewById(R.id.edVlrTotalVenda);
        btAdicionarItemVenda = findViewById(R.id.btAdicionarItemVenda);
        btSalvarVenda = findViewById(R.id.btSalvarVenda);
        edCodigoVenda = findViewById(R.id.edCodigoVenda);
        rvItemVenda = findViewById(R.id.rvItemVenda);
        edQuantidadeItem = findViewById(R.id.edQuantidadeItem);


        clienteController = new ClienteController(this);
        enderecoController = new EnderecoController(this);
        itemController = new ItemController(this);
        pedidoVendaController = new PedidoVendaController(this);
        pedidoController = new PedidoController(this);

        ArrayList<Endereco> enderecos = enderecoController.retornarTodosEnderecos();
        ArrayList<Item> items = itemController.retornarTodosItens();
        ArrayList<Cliente> clientes = clienteController.retornarTodosClientes();

        String[] vetorEnderecos = new String[enderecos.size()];
        String[] vetorItems = new String[items.size()];
        String[] vetorClientes = new String[clientes.size()];
        String[] vetorQtdeParcelas = new String[10];

        for(int i = 0; i < enderecos.size(); i++){
            vetorEnderecos[i] = enderecos.get(i).getBairro() +", "+enderecos.get(i).getLogradouro()+ ", "+ enderecos.get(i).getNumero() +
                    " ("+enderecos.get(i).getCidade() +"-"+enderecos.get(i).getUf()+")";
        }

        for(int i = 0; i < items.size(); i++){
            vetorItems[i] = items.get(i).getDescricao();
        }

        for(int i = 0; i < clientes.size(); i++){
            vetorClientes[i] = clientes.get(i).getNome();
        }

        adapterEndereco = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorEnderecos);
        adapterItem = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorItems);
        adapterCliente = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorClientes);

        spEnderecoVenda.setAdapter(adapterEndereco);
        spItemVenda.setAdapter(adapterItem);
        spClienteVenda.setAdapter(adapterCliente);

        spEnderecoVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edEnderecoVenda.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spItemVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edItemVenda.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spClienteVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edClienteVenda.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        radioGroupVenda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbAvista:
                        spQtdeParcelas.setVisibility(View.GONE);
                        break;
                    case R.id.rbParcelado:
                        spQtdeParcelas.setVisibility(View.VISIBLE);
                        calculaParcelas();
                        break;
                }
                calculaValor();
            }
        });

        btAdicionarItemVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarItemVenda();
            }
        });
    }

    private void calculaParcelas(){
        if(!edValorTotalVenda.getText().toString().isEmpty()){
            Double valorVenda = Double.parseDouble(edValorTotalVenda.getText().toString());
            String[] vetorQtdeParcelas = new String[10];
            for(int i = 1; i <= 10; i++){
                vetorQtdeParcelas[i] = (i) +"X - R$"+valorVenda/i;
            }
        }
    }

    private void adicionarItemVenda(){
        Item item = itemController.retornarItem(Integer.parseInt(edItemVenda.getText().toString()));
        if(item != null){
            Pedido pedido = new Pedido();
            pedido.setItem(item);
            pedido.setQuantidade(Integer.parseInt(edQuantidadeItem.getText().toString()));
            itensVenda.add(pedido);
            ItemVendaAdapter adapter = new ItemVendaAdapter(itensVenda, this);
            rvItemVenda.setLayoutManager(new LinearLayoutManager(this));
            rvItemVenda.setAdapter(adapter);
            calculaValor();
            salvarVenda();
            //salvarPedidoVenda();
        }



    }
    private void calculaValor(){
        Double vlrTotal = 0.0;
        if(!edValorTotalVenda.getText().toString().isEmpty()){
            vlrTotal = Double.valueOf(edValorTotalVenda.getText().toString());
        }

        for (Pedido pedido: itensVenda) {
            vlrTotal = vlrTotal + pedido.getItem().getVlrUnit() * pedido.getQuantidade();
        }

        int radioButtonId = radioGroupVenda.getCheckedRadioButtonId();
        switch (radioButtonId){
            case R.id.rbAvista:
                vlrTotal = vlrTotal * 0.95;
                break;
            default:
                break;
        }

        edValorTotalVenda.setText(String.valueOf(vlrTotal));

    }

    private void salvarVenda(){
        if(edCodigoVenda.getText().toString().isEmpty()){
            ArrayList<PedidoVenda> vendas = pedidoVendaController.retornarTodosPedidosVenda();
            Long retorno = Long.valueOf(0);
            retorno =
            pedidoVendaController.salvarPedidoVenda(
                    String.valueOf(vendas.size()+1),
                    edClienteVenda.getText().toString(),
                    edEnderecoVenda.getText().toString(),
                    edValorTotalVenda.getText().toString(),
                    ""
            );

            if (retorno > 0) {
                edCodigoVenda.setText(String.valueOf(vendas.size()+1));
                Toast.makeText(this, "Venda atualizado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao atualizar Venda, verifique LOG", Toast.LENGTH_SHORT).show();
            }
        }else{
            Long retorno = Long.valueOf(0);
            retorno =
            pedidoVendaController.atualizarPedidoVenda(
                    edCodigoVenda.getText().toString(),
                    edClienteVenda.getText().toString(),
                    edEnderecoVenda.getText().toString(),
                    edValorTotalVenda.getText().toString(),
                    ""
            );
            if (retorno > 0) {
                Toast.makeText(this, "Venda atualizado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao atualizar Venda, verifique LOG", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void salvarPedidoVenda(){
        ArrayList<Pedido> pedidos = pedidoController.retornarTodosPedidos();

        Long retorno = Long.valueOf(0);
        retorno =
        pedidoController.salvarPedido(
                String.valueOf(pedidos.size()+1),
                edItemVenda.getText().toString(),
                edCodigoVenda.getText().toString(),
                edQuantidadeItem.getText().toString()
        );
        if (retorno > 0) {
            Toast.makeText(this, "Itens do pedido atualizados com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao atualizar itens do pedido, verifique LOG", Toast.LENGTH_SHORT).show();
        }
    }

}
