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
    private long foco;
    private long minIni;
    private int click;
    private int tempo1;
    private int tempo2;
    private int tempo3;

    public long getMinIni() {
        return minIni;
    }

    public void setMinIni(long minIni) {
        this.minIni = minIni;
    }

    public int getTempo1() {
        return tempo1;
    }

    public void setTempo1(int tempo1) {
        this.tempo1 = tempo1;
    }

    public int getTempo2() {
        return tempo2;
    }

    public void setTempo2(int tempo2) {
        this.tempo2 = tempo2;
    }

    public int getTempo3() {
        return tempo3;
    }

    public void setTempo3(int tempo3) {
        this.tempo3 = tempo3;
    }

    public int getclick() {
        return click;
    }

    public void setclick(int click) {
        this.click = click;
    }

    public long getFoco() {
        return foco;
    }

    public void setFoco(long foco) {
        this.foco = foco;
    }

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
