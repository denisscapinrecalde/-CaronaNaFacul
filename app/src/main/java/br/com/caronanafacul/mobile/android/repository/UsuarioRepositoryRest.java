package br.com.caronanafacul.mobile.android.repository;

import android.service.notification.NotificationListenerService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

import br.com.caronanafacul.mobile.android.model.Usuario;

/**
 * Created by bruno on 13/04/16.
 */
public class UsuarioRepositoryRest {

    public Usuario getUserByEmail(String email){

        // The connection URL
        String url = "http://52.34.246.113:8080/CaronaNaFaculServer/usuario?email="+email;

        RestTemplate restTemplate = new RestTemplate();

        String json = restTemplate.getForObject(url, String.class);

        Type collectionType = new TypeToken<Usuario>(){}.getType();

        //TODO trocar essa parada, tá demorando demais
        Gson gson = new GsonBuilder().create();
        Usuario usuario = gson.fromJson(json, collectionType);

        return usuario;
    }
}
