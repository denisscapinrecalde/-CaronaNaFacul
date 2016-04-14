package br.com.caronanafacul.mobile.android.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created by bruno on 13/04/16.
 */
public class Carona {

    private Integer id;
    private String pontoPartida;
    private int numeroVagas;
    private Date horario;
    private Date tempoTolerancia;
    private Usuario usuario;
    private Collection<Vaga> vagas;
    private Collection<PontoPassagem> pontosPassagem;

    public Carona() {
    }

    public Carona(String pontoPartida, int numeroVagas, Date horario, Date tempoTolerancia, Usuario usuario) {
        this.pontoPartida = pontoPartida;
        this.numeroVagas = numeroVagas;
        this.horario = horario;
        this.tempoTolerancia = tempoTolerancia;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public String getPontoPartida() {
        return pontoPartida;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }

    public Date getHorario() {
        return horario;
    }

    public Date getTempoTolerancia() {
        return tempoTolerancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Collection<Vaga> getVagas() {
        return vagas;
    }

    public Collection<PontoPassagem> getPontosPassagem() {
        return pontosPassagem;
    }

    @Override
    public String toString() {
        return "Carona{" +
                "id=" + id +
                ", pontoPartida='" + pontoPartida + '\'' +
                ", numeroVagas=" + numeroVagas +
                ", horario=" + horario +
                ", tempoTolerancia=" + tempoTolerancia +
                ", usuario=" + usuario +
                ", vagas=" + vagas +
                ", pontosPassagem=" + pontosPassagem +
                '}';
    }
}