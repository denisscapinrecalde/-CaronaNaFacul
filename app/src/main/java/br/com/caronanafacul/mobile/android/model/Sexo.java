package br.com.caronanafacul.mobile.android.model;

/**
 * Created by bruno on 29/04/16.
 */
public enum Sexo {
    MALE("Masculino"), FEMALE("Feminino");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
