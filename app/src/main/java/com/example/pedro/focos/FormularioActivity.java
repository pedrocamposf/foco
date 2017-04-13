package com.example.pedro.focos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.pedro.focos.dao.TarefaDAO;
import com.example.pedro.focos.modelo.Tarefa;

import java.util.Date;

public class FormularioActivity extends AppCompatActivity {

    private DatePicker datePicker_fin;
    private DatePicker datePicker_ini;
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Intent intent = getIntent();
        Tarefa tarefa = (Tarefa) intent.getSerializableExtra("tarefa");
        helper = new FormularioHelper(this);

        NumberPicker hora_foco = (NumberPicker) findViewById(R.id.formulario_foco_diario);
        hora_foco.setMaxValue(24);
        hora_foco.setMinValue(1);

        if (tarefa == null) {
            datePicker_ini = (DatePicker) findViewById(R.id.formulario_data_incial);
            datePicker_fin = (DatePicker) findViewById(R.id.formulario_data_final);
            datePicker_ini.setMinDate(System.currentTimeMillis());
            datePicker_fin.setMinDate(System.currentTimeMillis());
        }
        else {
            helper.preencheFormulario(tarefa);
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
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                if (valDados()) {
                    Tarefa tarefa = helper.pegaTarefa();
                    TarefaDAO dao = new TarefaDAO(this);

                    if (tarefa.getId() != 0) {
                        dao.altera(tarefa);
                        Toast.makeText(FormularioActivity.this, "Tarefa: " + tarefa.getNome() + " atualizada!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dao.insere(tarefa);
                        Toast.makeText(FormularioActivity.this, "Tarefa: " + tarefa.getNome() + " criada!", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                    finish();
                }
                else
                    Toast.makeText(FormularioActivity.this, "Data invalida!", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean valDados() {

        datePicker_ini = (DatePicker) findViewById(R.id.formulario_data_incial);
        Date dataIni = new Date(datePicker_ini.getYear(),datePicker_ini.getMonth(),datePicker_ini.getDayOfMonth());

        datePicker_fin = (DatePicker) findViewById(R.id.formulario_data_final);
        Date dataFin = new Date(datePicker_fin.getYear(),datePicker_fin.getMonth(),datePicker_fin.getDayOfMonth());

        if (dataIni.getTime() > dataFin.getTime())
            return false;
        return true;
    }

    /**
     * Created by pedro on 12/04/2017.
     */

    public static class FocoActivity {
    }
}
