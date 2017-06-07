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
