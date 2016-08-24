package br.com.caronanafacul.mobile.android.repository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caronanafacul.mobile.android.model.Carona;
import br.com.caronanafacul.mobile.android.model.Usuario;
import br.com.caronanafacul.mobile.android.model.Vaga;

/**
 * Created by bruno on 13/04/16.
 */
public interface CaronaRepository {

    public void post(Carona carona);

    public List<Carona> getCaronasByUserId(int userId);
}