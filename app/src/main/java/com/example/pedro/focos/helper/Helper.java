package com.example.pedro.focos.helper;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.pedro.focos.FormularioActivity;
import com.example.pedro.focos.R;
import com.example.pedro.focos.RelatorioActivity;
import com.example.pedro.focos.modelo.Tarefa;

import java.util.Date;

/**
 * Created by pedro on 09/04/2017.
 */

public class Helper {

    private final EditText campoNome;
    private final DatePicker campoDataIni;
    private final DatePicker campoDataFin;
    private final NumberPicker campoHoras;
    private Tarefa tarefa;

    public Helper(Activity activity ){

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoDataIni = (DatePicker) activity.findViewById(R.id.formulario_data_incial);
        campoDataFin = (DatePicker) activity.findViewById(R.id.formulario_data_final);
        campoHoras = (NumberPicker) activity.findViewById(R.id.formulario_foco_diario);
        tarefa = new Tarefa();

    }



    public Tarefa pegaTarefa() {

        tarefa.setNome(campoNome.getText().toString());

        Date dataIni = new Date(campoDataIni.getYear() - 1900,campoDataIni.getMonth(),campoDataIni.getDayOfMonth());
        tarefa.setDataIni(dataIni.getTime());
        Date dataFin = new Date(campoDataFin.getYear() - 1900,campoDataFin.getMonth(),campoDataFin.getDayOfMonth());
        tarefa.setDataFin(dataFin.getTime());
        tarefa.setMinIni(System.currentTimeMillis() - dataIni.getTime());
        tarefa.setclick(0);


        tarefa.setHoras(campoHoras.getValue());

        return tarefa;
    }

    public void preencheFormulario(Tarefa tarefa) {

        campoNome.setText(tarefa.getNome());

        Date dataI = new Date(tarefa.getDataIni());

        campoDataIni.updateDate(dataI.getYear() + 1900,dataI.getMonth(),dataI.getDate());

        Date dataF = new Date(tarefa.getDataFin());
        campoDataFin.updateDate(dataF.getYear() + 1900,dataF.getMonth(),dataF.getDate());

        campoHoras.setValue(tarefa.getHoras());
        this.tarefa = tarefa;
    }

}
