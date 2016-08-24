package br.com.caronanafacul.mobile.android.repository;

import android.service.notification.NotificationListenerService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

import br.com.caronanafacul.mobile.android.model.Usuario;

/**
 * Created by bruno on 13/04/16.
 */
public class UsuarioRepositoryRest extends Repository implements UsuarioRepository {

    public UsuarioRepositoryRest() {
        super("/CaronaNaFaculServer/usuario", MediaType.APPLICATION_JSON);
    }

    @Override
    public Usuario post(Usuario usuario) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Usuario> httpEntity = restTemplate.postForEntity(getURI(), usuario, Usuario.class);
        Usuario usuarioRetornado = httpEntity.getBody();
        return usuarioRetornado;
    }
}
