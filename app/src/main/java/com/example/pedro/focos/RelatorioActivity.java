package com.example.pedro.focos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pedro.focos.helper.Helper;
import com.example.pedro.focos.modelo.Tarefa;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by pedro on 08/06/2017.
 */

public class RelatorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        Intent intent = getIntent();
        Tarefa tarefa = (Tarefa) intent.getSerializableExtra("tarefa");

        TextView nome = (TextView) this.findViewById(R.id.relatorio_nome);
        TextView dataIni = (TextView) this.findViewById(R.id.relatorio_data_ini);
        TextView dataFin = (TextView) this.findViewById(R.id.relatorio_data_fin);
        TextView focoDiario = (TextView) this.findViewById(R.id.relatorio_tempo_foco);
        TextView esperado = (TextView) this.findViewById(R.id.relatorio_esperado);
        TextView feito = (TextView) this.findViewById(R.id.relatorio_feito);
        ImageView img = (ImageView) this.findViewById(R.id.relatorio_img);

        java.util.Date dateIni = new java.util.Date(tarefa.getDataIni());
        java.util.Date dateFin = new java.util.Date(tarefa.getDataFin());

        nome.setText(tarefa.getNome());

        SimpleDateFormat dataP = new SimpleDateFormat("dd/MM/yy");

        StringBuilder ini = new StringBuilder( dataP.format( dateIni ) );
        dataIni.setText(ini);

        StringBuilder fin = new StringBuilder( dataP.format( dateFin ) );
        dataFin.setText(fin);

        int horas;
        if (tarefa.getHoras() == 0)
            horas = 0;
        else
            horas = tarefa.getHoras();

        focoDiario.setText(Integer.toString(horas) + " Horas");

        long days = ((tarefa.getDataFin() - tarefa.getDataIni()) / (1000*60*60*24)) + 1;
        int tempo = (int) (days*tarefa.getHoras());
        esperado.setText(Integer.toString(tempo) + " Horas");

        Long tempoFoco;
        if (tarefa.getFoco() == 0)
            tempoFoco = tarefa.getFoco();
        else
            tempoFoco = (tarefa.getFoco() / (1000*60*60));


        feito.setText(Long.toString(tempoFoco));

        if ( tempoFoco < tempo/3) {
            img.setImageResource(R.drawable.ok);
        }
        else if (tempoFoco < tempo/2) {
            img.setImageResource(R.drawable.atencao);
        }
        else {
            img.setImageResource(R.drawable.cuidado);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}