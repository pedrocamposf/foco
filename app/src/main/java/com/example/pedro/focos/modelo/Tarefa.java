package com.example.pedro.focos.modelo;

import java.util.Date;

/**
 * Created by pedro on 09/04/2017.
 */
public class Tarefa {

    private long id;
    private String nome;
    private Date dataIni;
    private Date datafin;
    private int horas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatafin() {
        return datafin;
    }

    public void setDatafin(Date datafin) {
        this.datafin = datafin;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
