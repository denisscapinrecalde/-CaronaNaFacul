package br.com.caronanafacul.mobile.android.repository;

import br.com.caronanafacul.mobile.android.model.Usuario;

/**
 * Created by bruno on 13/04/16.
 */
public class UsuarioRepository {

    public Usuario getUserByEmail(String email){
        return new Usuario("email@test", "faculdade");
    }
}