package br.com.taua.controller;

import br.com.taua.dto.Feature;
import br.com.taua.dto.ResponseAPI;
import br.com.taua.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/{id-hotel}/feature/{id-feature}")
    public ResponseEntity getFeatures(@PathVariable("id-hotel") Integer idHotel, @PathVariable("id-feature") Integer idFeature){
        try {
            Feature feature = hotelService.getFeatures(idHotel, idFeature);
            return ResponseEntity.ok(ResponseAPI.getInstance("Success", Collections.singletonList(feature)));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ResponseAPI.getInstance(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity getIndex(){
        return ResponseEntity.ok("Success");
    }
}
