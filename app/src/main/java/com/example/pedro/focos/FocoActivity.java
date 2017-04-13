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

/**
 * Created by pedro on 12/04/2017.
 */
public class FocoActivity extends AppCompatActivity {

    private Chronometer tempo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foco);
        tempo = (Chronometer) findViewById(R.id.foco_tempo);
        tempo.start();

        ImageButton stopFoco = (ImageButton) findViewById(R.id.foco_button_stop);

        stopFoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long elapsedMillis = SystemClock.elapsedRealtime() - tempo.getBase();
                Toast.makeText(FocoActivity.this, "Elapsed milliseconds: " + elapsedMillis,
                        Toast.LENGTH_SHORT).show();
                
                //// TODO: 12/04/2017 salvar tempor no banco 
                
                Intent intentStopFoco = new Intent(FocoActivity.this, ListaTarefasActivity.class);
                startActivity(intentStopFoco);
            }
        });

    }


}
