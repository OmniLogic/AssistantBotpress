
package br.com.taua.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class rest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://taua-cms.omnilogic.com.br/features";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/6", String.class);
        System.out.println(response);

    }
}

