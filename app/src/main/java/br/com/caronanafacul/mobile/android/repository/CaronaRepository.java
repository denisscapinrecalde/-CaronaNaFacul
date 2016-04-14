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
public class CaronaRepository {

    public void put(Carona carona){

    }

    public List<Carona> getCaronasByUserId(int userId){
        List<Carona> caronas = new ArrayList<Carona>();
        caronas.add(new Carona("Fatec Ipiranga", 4, new Date(), new Date(), new Usuario()));
        caronas.add(new Carona("Mackenzie", 6, new Date(), new Date(), new Usuario()));

        return caronas;
    }
}