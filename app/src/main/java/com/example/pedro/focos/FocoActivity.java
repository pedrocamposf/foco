package com.example.pedro.focos;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pedro.focos.dao.TarefaDAO;
import com.example.pedro.focos.modelo.Tarefa;

/**
 * Created by pedro on 12/04/2017.
 */
public class FocoActivity extends AppCompatActivity {

    private Chronometer tempo;
    private Tarefa tarefa;
    private TarefaDAO dao;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foco);
        tempo = (Chronometer) findViewById(R.id.foco_tempo);
        tempo.start();

        Intent intent = getIntent();
        tarefa = (Tarefa) intent.getSerializableExtra("tarefa");

        dao = new TarefaDAO(this);

        ImageButton stopFoco = (ImageButton) findViewById(R.id.foco_button_stop);
        stopFoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long elapsedMillis = SystemClock.elapsedRealtime() - tempo.getBase();

                tarefa.setFoco(tarefa.getFoco() + elapsedMillis);
                tarefa.setclick(tarefa.getclick() + 1);

                long days = ((tarefa.getDataFin() - tarefa.getDataIni()) / (1000*60*60*24)) + 1;
                long tempo = ((days*tarefa.getHoras())*1000*60*60)/3;

                long tempoDecorrido = (System.currentTimeMillis() - (tarefa.getDataIni() + tarefa.getMinIni()));

                if (tempoDecorrido < tempo)
                    tarefa.setTempo1(tarefa.getTempo1() + 1);
                else if (tempoDecorrido < tempo * 2)
                    tarefa.setTempo2(tarefa.getTempo2() + 1);
                else
                    tarefa.setTempo3(tarefa.getTempo3() + 1);

                finish();
                if (tarefa.getId() != 0) {
                    dao.altera(tarefa);
                    Toast.makeText(FocoActivity.this, "Mantenha o Foco!!", Toast.LENGTH_SHORT).show();
                }
                
                Intent intentStopFoco = new Intent(FocoActivity.this, ListaTarefasActivity.class);
                startActivity(intentStopFoco);
            }
        });

    }


}
