package com.example.pedro.focos.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pedro on 09/04/2017.
 */
public class Tarefa implements Serializable {

    private long id;
    private String nome;
    private long dataIni;
    private long dataFin;
    private int horas;

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public long getDataFin() {
        return dataFin;
    }

    public void setDataFin(long dataFin) {
        this.dataFin = dataFin;
    }

    public long getDataIni() {
        return dataIni;
    }

    public void setDataIni(long dataIni) {
        this.dataIni = dataIni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getId() + "-" + getNome();
    }
}
