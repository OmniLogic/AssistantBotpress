package br.com.taua.service;

import br.com.taua.client.SimpleRestClient;
import br.com.taua.dto.Feature;
import br.com.taua.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    //@Value("${cms.url}")
    private String URL_CMS = "https://taua-cms.omnilogic.com.br";

    public Feature getFeatures(Integer idHotel, Integer idFeature) throws Exception {
        SimpleRestClient client = new SimpleRestClient(URL_CMS);
        Hotel hotel = null;

        try {
            ResponseEntity<Hotel> response = client.get(String.format("/hotels/%d", idHotel), Hotel.class);
            if (response.getStatusCode().equals(HttpStatus.OK))
                hotel = response.getBody();
            else
                throw new Exception(String.format("ERROR client.get Hotel %d >> %s", idHotel, response));
        } catch (Exception ex) {
            log.error(String.format("ERROR getFeatures client.get hotels >> %s", ex.getMessage()));
            throw ex;
        }

        if (!Optional.ofNullable(hotel).isPresent()) {
            throw new Exception(String.format("Hotel %d not found.", idHotel));
        }

        Feature feature = hotel.getFeatures().stream()
                .filter(item -> item.getId().equals(idFeature))
                .findFirst()
                    .orElseThrow(() -> new Exception(String.format("Hotel %d and feature %d not found.", idHotel, idFeature)));

        return feature;
    }
}
