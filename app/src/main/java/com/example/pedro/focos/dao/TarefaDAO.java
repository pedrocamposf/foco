package com.example.pedro.focos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.pedro.focos.modelo.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 09/04/2017.
 */

public class TarefaDAO extends SQLiteOpenHelper {

    public TarefaDAO(Context context) {
        super(context, "Tarefas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Tarefas (id INTEGER PRIMARY KEY," +
                " nome TEXT NOT NULL," +
                " dataini INTEGER," +
                " datafin INTEGER," +
                " horas INTEGER," +
                " foco INTEGER," +
                " minIni INTEGER," +
                " click INTEGER," +
                " tempo1 INTEGER," +
                " tempo2 INTEGER," +
                " tempo3 INTEGER);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Tarefas";
        db.execSQL(sql);
    }

    public void insere(Tarefa tarefa) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues dados = getContentValuesTarefa(tarefa);

        db.insert("Tarefas", null, dados);
    }

    @NonNull
    private ContentValues getContentValuesTarefa(Tarefa tarefa) {
        ContentValues dados = new ContentValues();
        dados.put("nome", tarefa.getNome());
        dados.put("dataini", tarefa.getDataIni());
        dados.put("datafin", tarefa.getDataFin());
        dados.put("horas", tarefa.getHoras());
        dados.put("foco", tarefa.getFoco());
        dados.put("minIni", tarefa.getMinIni());
        dados.put("click", tarefa.getclick());
        dados.put("tempo1", tarefa.getTempo1());
        dados.put("tempo2", tarefa.getTempo2());
        dados.put("tempo3", tarefa.getTempo3());
        return dados;
    }

    public List<Tarefa> buscaTarefas() {
        String sql = "SELECT * FROM Tarefas";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(c.getLong(c.getColumnIndex("id")));
            tarefa.setNome(c.getString(c.getColumnIndex("nome")));
            tarefa.setDataIni(c.getLong(c.getColumnIndex("dataini")));
            tarefa.setDataFin(c.getLong(c.getColumnIndex("datafin")));
            tarefa.setHoras(c.getInt(c.getColumnIndex("horas")));
            tarefa.setFoco(c.getLong(c.getColumnIndex("foco")));
            tarefa.setMinIni(c.getLong(c.getColumnIndex("minIni")));
            tarefa.setclick(c.getInt(c.getColumnIndex("click")));
            tarefa.setTempo1(c.getInt(c.getColumnIndex("tempo1")));
            tarefa.setTempo2(c.getInt(c.getColumnIndex("tempo2")));
            tarefa.setTempo3(c.getInt(c.getColumnIndex("tempo3")));
            tarefas.add(tarefa);
        }
        c.close();

        return tarefas;
    }

    public void deleta(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {Long.toString(tarefa.getId())};
        db.delete("Tarefas", "id = ?", params);
    }

    public void altera(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesTarefa(tarefa);
        String [] params = {Long.toString(tarefa.getId())};
        db.update("Tarefas", dados, "id = ?", params);
    }

}
