package com.example.pedro.focos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.NumberPicker;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        DatePicker datePicker_ini = (DatePicker) findViewById(R.id.data_inicial);
        DatePicker datePicker_fin = (DatePicker) findViewById(R.id.data_final);
        datePicker_ini.setMinDate(System.currentTimeMillis());
        datePicker_fin.setMinDate(System.currentTimeMillis());
        NumberPicker hora_foco = (NumberPicker) findViewById(R.id.foco_diario);
        hora_foco.setMaxValue(24);
        hora_foco.setMinValue(1);
    }
}
