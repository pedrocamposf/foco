package com.example.pedro.focos;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.pedro.focos.modelo.Tarefa;

import java.util.Date;

/**
 * Created by pedro on 09/04/2017.
 */

public class FormularioHelper {

    private final EditText campoNome;
    private final DatePicker campoDataIni;
    private final DatePicker campoDataFin;
    private final NumberPicker campoHoras;
    private Tarefa tarefa;

    public FormularioHelper(FormularioActivity activity ){

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoDataIni = (DatePicker) activity.findViewById(R.id.formulario_data_incial);
        campoDataFin = (DatePicker) activity.findViewById(R.id.formulario_data_final);
        campoHoras = (NumberPicker) activity.findViewById(R.id.formulario_foco_diario);
        tarefa = new Tarefa();

    }

    public Tarefa pegaTarefa(FormularioActivity a) {

        tarefa.setNome(campoNome.getText().toString());

        Date dataIni = new Date(campoDataIni.getYear(),campoDataIni.getMonth(),campoDataIni.getDayOfMonth());
        tarefa.setDataIni(dataIni.getTime());

        Date dataFin = new Date(campoDataFin.getYear(),campoDataFin.getMonth(),campoDataFin.getDayOfMonth());
        tarefa.setDataFin(dataFin.getTime());

        tarefa.setHoras(campoHoras.getValue());

        return tarefa;
    }

    public void preencheFormulario(Tarefa tarefa, FormularioActivity a) {

        campoNome.setText(tarefa.getNome());

        Date dataI = new Date(tarefa.getDataIni());

        campoDataIni.updateDate(dataI.getYear(),dataI.getMonth(),dataI.getDate());

        Date dataF = new Date(tarefa.getDataFin());
        campoDataFin.updateDate(dataF.getYear(),dataF.getMonth(),dataF.getDate());

        campoHoras.setValue(tarefa.getHoras());
        this.tarefa = tarefa;
    }

}
