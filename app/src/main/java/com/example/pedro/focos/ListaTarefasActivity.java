package com.example.pedro.focos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaTarefasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        String[] tarefas = {"TCC","ACADEMIA"};
        ListView listaTarefas = (ListView) findViewById(R.id.lista_tarefas);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tarefas);
        listaTarefas.setAdapter(adapter);
    }
}
