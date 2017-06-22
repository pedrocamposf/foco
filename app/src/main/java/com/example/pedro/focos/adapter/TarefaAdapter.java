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

        ImageView img1 = (ImageView) view.findViewById(R.id.img_ok);
        ImageView img2 = (ImageView) view.findViewById(R.id.img_atencao);
        ImageView img3 = (ImageView) view.findViewById(R.id.img_cuidado);

        long tempoDecorrido = System.currentTimeMillis() - (tarefa.getDataIni() + tarefa.getMinIni());

        long tempoP = tempoDecorrido;
        int daysP = (int) ((tempoDecorrido) / (1000*60*60*24)) + 1;
        int horasP = daysP*tarefa.getHoras()*1000*60*60;

        if (tempoDecorrido > 86400000)
            tempoP = ((tempoDecorrido) / (1000*60*60*24))*tarefa.getHoras();

        if (tempoP  > horasP)
            tempoP = horasP;

        if ( tarefa.getFoco() >= tempoP) {
            img1.setImageResource(R.drawable.ok);
        }
        else if(3*tarefa.getFoco() < tempoP){
            img1.setImageResource(R.drawable.cuidado);
            img2.setImageResource(R.drawable.cuidado);
            img3.setImageResource(R.drawable.cuidado);
        }
        else {
            img1.setImageResource(R.drawable.atencao);
            img2.setImageResource(R.drawable.atencao);
        }

        return view;
    }
}
