package br.com.caronanafacul.mobile.android.repository;

import org.springframework.http.MediaType;

abstract class Repository {

    //TODO também colocar em aquivos XML
    private final String URI;
    private final String protocolo = "http://";
    private final String servidor = "52.40.226.170";
    private final String porta = "8080";
    private final String recurso;//TODO mudar essa porra de recurso
    private final MediaType mediaType;

    public Repository(String recurso, MediaType mediaType) {
        this.recurso = recurso;
        this.mediaType = mediaType;
        this.URI = protocolo.concat(servidor).concat(":").concat(porta).concat(recurso);
    }

    public String getURI() {
        return URI;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
}
