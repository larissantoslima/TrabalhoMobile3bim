package com.example.foradevendas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foradevendas.R;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /****Carrega o layout do menu*/
        MenuInflater mnInflater = getMenuInflater();
        mnInflater.inflate(R.menu.menu_padrao, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**Atribuir ação ao clicar no menu**/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnEndereco) {
            Intent intent = new Intent(MainActivity.this, CadastroEnderecoActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.mnCliente) {
            Intent intent = new Intent(MainActivity.this, CadastroClienteActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.mnItem) {
            Intent intent = new Intent(MainActivity.this, CadastroItemActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.mnVenda) {
            Intent intent = new Intent(MainActivity.this, CadastroVendaActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}