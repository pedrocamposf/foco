package com.example.pedro.focos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.pedro.focos.dao.TarefaDAO;
import com.example.pedro.focos.modelo.Tarefa;

import java.util.Date;
import java.util.List;

public class ListaTarefasActivity extends AppCompatActivity {

    private ListView listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        listaTarefas = (ListView) findViewById(R.id.lista_tarefas);

        listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Tarefa tarefa = (Tarefa) listaTarefas.getItemAtPosition(position);
                Toast.makeText(ListaTarefasActivity.this, "Foco na tarefa " + tarefa.getNome() + "!!",Toast.LENGTH_SHORT).show();
                Intent intentFoco = new Intent(ListaTarefasActivity.this, FocoActivity.class);
                intentFoco.putExtra("tarefa", tarefa);
                startActivity(intentFoco);
            }
        });

        Button novaTarefa = (Button) findViewById(R.id.lista_botao_salvar);
        novaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(ListaTarefasActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaTarefas);

    }

    private void carregaLista() {

        TarefaDAO dao = new TarefaDAO(this);
        List<Tarefa> tarefas = dao.buscaTarefas();
        dao.close();


        ArrayAdapter<Tarefa> adapter= new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        listaTarefas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem atualizar = menu.add("Atualizar");
        atualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Tarefa tarefa = (Tarefa) listaTarefas.getItemAtPosition(info.position);

                Intent intentVaiProFormulario = new Intent(ListaTarefasActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("tarefa", tarefa);
                startActivity(intentVaiProFormulario);
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Tarefa tarefa = (Tarefa) listaTarefas.getItemAtPosition(info.position);
                Toast.makeText(ListaTarefasActivity.this,"Tarefa " + tarefa.getNome() + " deletada!",Toast.LENGTH_SHORT).show();
                TarefaDAO dao = new TarefaDAO(ListaTarefasActivity.this);
                dao.deleta(tarefa);
                dao.close();
                carregaLista();
                return false;
            }
        });

    }

}
