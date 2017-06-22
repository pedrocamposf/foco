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
        TextView decorrido = (TextView) this.findViewById(R.id.relatorio_decorrido);
        ImageView img = (ImageView) this.findViewById(R.id.relatorio_img);
        TextView click = (TextView) this.findViewById(R.id.relatorio_clicks);
        TextView tempo1 = (TextView) this.findViewById(R.id.relatorio_tempo1);
        TextView tempo2 = (TextView) this.findViewById(R.id.relatorio_tempo2);
        TextView tempo3 = (TextView) this.findViewById(R.id.relatorio_tempo3);

        java.util.Date dateIni = new java.util.Date(tarefa.getDataIni());
        java.util.Date dateFin = new java.util.Date(tarefa.getDataFin());

        nome.setText(tarefa.getNome());

        SimpleDateFormat dataP = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder ini = new StringBuilder( dataP.format( dateIni ) );
        dataIni.setText(ini);

        StringBuilder fin = new StringBuilder( dataP.format( dateFin ) );
        dataFin.setText(fin);

        int horas;
        if (tarefa.getHoras() == 0)
            horas = 0;
        else
            horas = tarefa.getHoras();

        focoDiario.setText(Integer.toString(horas) + " hr");

        long days = ((tarefa.getDataFin() - tarefa.getDataIni()) / (1000*60*60*24)) + 1;
        int tempo = (int) (days*tarefa.getHoras());

        long tempoDecorrido = System.currentTimeMillis() - (tarefa.getDataIni() + tarefa.getMinIni());

        esperado.setText(Integer.toString(tempo) + " hr");


        decorrido.setText(convertHora(tempoDecorrido));

        feito.setText(convertHora(tarefa.getFoco()));

        click.setText(Integer.toString(tarefa.getclick()));

        tempo1.setText(Integer.toString(tarefa.getTempo1()));
        tempo2.setText(Integer.toString(tarefa.getTempo2()));
        tempo3.setText(Integer.toString(tarefa.getTempo3()));

        long tempoP = tempoDecorrido;
        int daysP = (int) ((tempoDecorrido) / (1000*60*60*24)) + 1;
        int horasP = daysP*tarefa.getHoras()*1000*60*60;

        if (tempoDecorrido > 86400000)
            tempoP = ((tempoDecorrido) / (1000*60*60*24))*tarefa.getHoras();

        if (tempoP  > horasP)
            tempoP = horasP;


        if ( tarefa.getFoco() >= tempoP) {
            img.setImageResource(R.drawable.ok);
        }
        else if(3*tarefa.getFoco() < tempoP){
            img.setImageResource(R.drawable.cuidado);
        }
        else {
            img.setImageResource(R.drawable.atencao);
        }


    }

    private String convertHora(long l) {
        long segundos = ( l / 1000 ) % 60;      // se não precisar de segundos, basta remover esta linha.
        long minutos  = ( l / 60000 ) % 60;     // 60000   = 60 * 1000
        long hr    = l / 3600000;            // 3600000 = 60 * 60 * 1000
        return String.format( "%02d:%02d:%02d", hr, minutos, segundos);
    }


    private String validaPl(int i) {
        if (i == 1)
            return " ";
        else
            return "s";
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
