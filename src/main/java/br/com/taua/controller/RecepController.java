package br.com.taua.controller;

import br.com.taua.repository.RecepcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class RecepController {

    @GetMapping("/{id}/inout")
    public ResponseEntity getInOut(@PathVariable("id") Integer id){
        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity getIndex(){
        return ResponseEntity.ok("Success");
    }
}
