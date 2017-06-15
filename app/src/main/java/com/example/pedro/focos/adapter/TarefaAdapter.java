package com.example.pedro.focos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pedro.focos.ListaTarefasActivity;
import com.example.pedro.focos.R;
import com.example.pedro.focos.modelo.Tarefa;

import java.util.List;

/**
 * Created by pedro on 07/06/2017.
 */

public class TarefaAdapter extends BaseAdapter {
    private final List<Tarefa> tarefas;
    private final Context contex;

    public TarefaAdapter(Context contex, List<Tarefa> tarefas) {
        this.contex = contex;
        this.tarefas = tarefas;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tarefa tarefa = tarefas.get(position);

        LayoutInflater inflater = LayoutInflater.from(contex);
        View view = inflater.inflate(R.layout.lista_item, null);

        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(tarefa.getNome());

        double tempoDecorrido = (((System.currentTimeMillis() - tarefa.getDataIni()) / (86400000.00))) * tarefa.getHoras();

        double tempoFoco;
        if (tarefa.getFoco() == 0)
            tempoFoco = tarefa.getFoco();
        else
            tempoFoco = (tarefa.getFoco() / (3600000.00));

        ImageView img1 = (ImageView) view.findViewById(R.id.img_ok);
        ImageView img2 = (ImageView) view.findViewById(R.id.img_atencao);
        ImageView img3 = (ImageView) view.findViewById(R.id.img_cuidado);

        if ( tempoFoco > tempoDecorrido/3) {
            img1.setImageResource(R.drawable.ok);
        }
        else if (tempoFoco > tempoDecorrido/2) {
            img1.setImageResource(R.drawable.atencao);
            img2.setImageResource(R.drawable.atencao);
        }
        else {
            img1.setImageResource(R.drawable.cuidado);
            img2.setImageResource(R.drawable.cuidado);
            img3.setImageResource(R.drawable.cuidado);
        }

        return view;
    }
}
