package br.com.caronanafacul.mobile.android.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caronanafacul.mobile.android.R;
import br.com.caronanafacul.mobile.android.model.Carona;
import br.com.caronanafacul.mobile.android.model.Usuario;


public class CaronaRepositoryRest extends Repository implements CaronaRepository {

    private String formatoData = "yyyy-MM-dd'T'HH:mm:ss"; //TODO colocar isso em ma classe útil genérica, ou xml

    public CaronaRepositoryRest() {
        super("/CaronaNaFaculServer/carona", MediaType.APPLICATION_JSON); //TODO pegar isso do arquivo XML
    }

    public void post(Carona carona){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(getMediaType());

        Gson gson = new GsonBuilder().setDateFormat(formatoData).create();
        HttpEntity<?> entity = new HttpEntity<String>(gson.toJson(carona),headers);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Carona> httpEntity = restTemplate.postForEntity(getURI(), entity, Carona.class);
        httpEntity.getBody();
    }

    public List<Carona> getCaronasByUserId(int userId){
        String parametros = "?user=" + userId;
        String URL = getURI().concat(parametros);

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(URL, String.class);

        Type collectionType = new TypeToken<List<Carona>>() {}.getType();
        Log.e("[getCaronasByUserId JS]", json);

        //TODO mudar, marretada
        String newJson = json.replace("{\"carona\":","").replace("]}", "]");

        Gson gson = new GsonBuilder().setDateFormat(formatoData).create();
        List<Carona> caronas = gson.fromJson(newJson, collectionType);

        return caronas;
    }
}
