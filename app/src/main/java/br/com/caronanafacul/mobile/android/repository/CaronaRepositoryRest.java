package br.com.caronanafacul.mobile.android.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.caronanafacul.mobile.android.model.Carona;
import br.com.caronanafacul.mobile.android.model.Usuario;

/**
 * Created by bruno on 13/04/16.
 */
public class CaronaRepositoryRest {

    //TODO refatorar essa porra toda
    public void put(Carona carona){
        String uri = "http://52.34.246.113:8080/CaronaNaFaculServer/carona";

        Log.e("Carona PUT", "Dentro do PUT");
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(uri, carona);
    }


    public List<Carona> getCaronasByUserId(int userId){
        String URL = "http://52.34.246.113:8080/CaronaNaFaculServer/carona?user="+userId;

        RestTemplate restTemplate = new RestTemplate();

        String json = restTemplate.getForObject(URL, String.class);

        Type collectionType = new TypeToken<List<Carona>>() {}.getType();

        Log.e("Caronas JSON", json);

        String newJson = json.replace("{\"carona\":","");
        String newJson2 = newJson.replace("]}", "]");

        //TODO trocar essa parada, tá demorando demais
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        List<Carona> caronas = gson.fromJson(newJson2, collectionType);

        return caronas;
    }
}
