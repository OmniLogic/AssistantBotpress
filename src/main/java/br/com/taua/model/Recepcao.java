package br.com.taua.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;

import java.util.Scanner;
@Data
public class Recepcao {

    private Integer inOut;
    private Integer maleiro;
    private Integer arrumacao;
    private Integer Loja;
    private Integer enfermaria;
    private Integer Frigobar;
    private Integer servicoQuarto;

    public Integer getInOut() {
        return inOut;
    }

    public Integer getMaleiro() {
        return maleiro;
    }

    public Integer getArrumacao() {
        return arrumacao;
    }

    public Integer getLoja() {
        return Loja;
    }

    public Integer getEnfermaria() {
        return enfermaria;
    }

    public Integer getFrigobar() {
        return Frigobar;
    }

    public Integer getServicoQuarto() {
        return servicoQuarto;
    }
}
