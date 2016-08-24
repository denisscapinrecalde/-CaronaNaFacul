package br.com.caronanafacul.mobile.android.model;

import java.io.Serializable;

/**
 * Created by bruno on 13/04/16.
 */
public class Usuario implements Serializable {

    private Integer id;
    private String nome;
    private Sexo sexo;
    private String email;
    private String faculdade;
    private Long facebookId;

    public Usuario(){
    }

    public Usuario(String nome, String email, Sexo sexo, String faculdade, Long facebookId) {
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.faculdade = faculdade;
        this.facebookId = facebookId;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public Long getFacebookId() {
        return facebookId;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", sexo=" + sexo +
                ", faculdade='" + faculdade + '\'' +
                ", facebookId=" + facebookId +
                '}';
    }
}