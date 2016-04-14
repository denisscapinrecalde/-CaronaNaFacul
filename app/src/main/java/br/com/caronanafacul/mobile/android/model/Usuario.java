package br.com.caronanafacul.mobile.android.model;

import java.io.Serializable;

/**
 * Created by bruno on 13/04/16.
 */
public class Usuario implements Serializable {

    private Integer id;
    private String email;
    private String faculdade;

    public Usuario() {
    }

    public Usuario(String email, String faculdade) {
        this.id = 1;
        this.email = email;
        this.faculdade = faculdade;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculdade() {
        return faculdade;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", faculdade='" + faculdade + '\'' +
                '}';
    }
}