package com.example.pedro.focos;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

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

    public FormularioHelper(FormularioActivity activity ){

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoDataIni = (DatePicker) activity.findViewById(R.id.formulario_data_incial);
        campoDataFin = (DatePicker) activity.findViewById(R.id.formulario_data_final);
        campoHoras = (NumberPicker) activity.findViewById(R.id.formulario_foco_diario);

    }

    public Tarefa pegaTarefa() {
        Tarefa tarefa = new Tarefa();

        tarefa.setNome(campoNome.getText().toString());

        Date dataIni = new Date(campoDataIni.getYear(),campoDataIni.getMonth(),campoDataIni.getDayOfMonth());
        tarefa.setDataIni(dataIni);

        Date dataFin = new Date(campoDataFin.getYear(),campoDataFin.getMonth(),campoDataFin.getDayOfMonth());
        tarefa.setDatafin(dataFin);

        tarefa.setHoras(campoHoras.getValue());

        return tarefa;
    }
}
