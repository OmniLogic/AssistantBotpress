package br.com.taua.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Supplier;

public class SimpleRestClient {
    private final String server;
    private final RestTemplate rest;
    private final HttpHeaders headers;

    public SimpleRestClient(String server, Map<String, String> addHeaders) {
        Assert.notNull(server, "Server parameter can't be null");
        this.server = server;
        this.rest = new RestTemplate(getClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        if (addHeaders != null) {
            for (Map.Entry<String, String> header : addHeaders.entrySet()) {
                this.headers.add(header.getKey(), header.getValue());
            }
        }
        this.headers.addIfAbsent("Content-Type", "application/json");
        this.headers.addIfAbsent("Accept", "application/json");
    }
    public SimpleRestClient(String server, Map<String, String> addHeaders, RestTemplate rest) {
        Assert.notNull(server, "Server parameter can't be null");
        this.server = server;
        this.rest = rest;
        this.headers = new HttpHeaders();
        if (addHeaders != null) {
            for (Map.Entry<String, String> header : addHeaders.entrySet()) {
                this.headers.add(header.getKey(), header.getValue());
            }
        }
        this.headers.addIfAbsent("Content-Type", "application/json");
        this.headers.addIfAbsent("Accept", "application/json");
    }

    public SimpleRestClient(Map<String, String> addHeaders) {
        this("", addHeaders);
    }

    public SimpleRestClient() {
        this("", null);
    }

    public SimpleRestClient(String server) {
        this(server, null);
    }

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(120000);
        clientHttpRequestFactory.setReadTimeout(120000);
        return clientHttpRequestFactory;
    }

    private <T> ResponseEntity<T> withExceptionHandling(Supplier<ResponseEntity<T>> action) {
        try {
            return action.get();
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }

    public <E> ResponseEntity<E> get(String uri, Class<E> returnObjectClass) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return withExceptionHandling(() -> rest.exchange(server + uri, HttpMethod.GET, requestEntity, returnObjectClass));
        //return rest.exchange(server + uri, HttpMethod.GET, requestEntity, returnObjectClass);
    }

    public <E, T> ResponseEntity<E> post(String endpoint, T json, Class<E> returnObjectClass) {
        HttpEntity<T> requestEntity = new HttpEntity<>(json, headers);
        String URL = server + endpoint;
        return rest.exchange(URL, HttpMethod.POST, requestEntity, returnObjectClass);
    }

    public <E, T> ResponseEntity<E> postForEntity(String endpoint, T entity, Class<E> returnObjectClass) {
        HttpEntity<T> requestEntity = new HttpEntity<T>(entity, headers);
        String URL = server + endpoint;
        return rest.postForEntity(URL, requestEntity, returnObjectClass);
    }

    public <E, T> ResponseEntity<E> put(String uri, T json, Class<E> returnObjectClass) {
        HttpEntity<T> requestEntity = new HttpEntity<>(json, headers);
        return rest.exchange(server + uri, HttpMethod.PUT, requestEntity, returnObjectClass);
    }

    public <E> ResponseEntity<E> delete(String uri, Class<E> returnObjectClass) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return rest.exchange(server + uri, HttpMethod.DELETE, requestEntity, returnObjectClass);
    }

    public <E, T> ResponseEntity<E> patch(String uri, T json, Class<E> returnObjectClass) {
        HttpEntity<T> requestEntity = new HttpEntity<>(json, headers);
        return rest.exchange(server + uri, HttpMethod.PATCH, requestEntity, returnObjectClass);
    }

    public ResponseEntity<String> get(String uri) {
        return this.get(uri, String.class);
    }

    public <T> ResponseEntity<String> post(String uri, T json) {
        return this.post(uri, json, String.class);
    }

    public <T> ResponseEntity<String> put(String uri, T json) {
        return this.put(uri, json, String.class);
    }

    public ResponseEntity<String> delete(String uri) {
        return this.delete(uri, String.class);
    }

    public <T> ResponseEntity<String> patch(String uri, T json) {
        return this.patch(uri, json, String.class);
    }
}
