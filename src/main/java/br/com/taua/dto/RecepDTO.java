package br.com.taua.dto;

import br.com.taua.model.Recepcao;

import java.util.List;
import java.util.stream.Collectors;

public class RecepDTO {

    private Integer inOut;
    private Integer maleiro;
    private Integer arrumacao;
    private Integer loja;
    private Integer enfermaria;
    private Integer frigobar;
    private Integer servicoQuarto;



    public RecepDTO(Recepcao recepcao) {
        this.inOut = recepcao.getInOut();
        this.enfermaria = recepcao.getEnfermaria();
        this.maleiro = recepcao.getMaleiro();
        this.arrumacao = recepcao.getArrumacao();
        this.loja = recepcao.getLoja();
        this.frigobar = recepcao.getFrigobar();
        this.servicoQuarto = recepcao.getServicoQuarto();



    }

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
        return loja;
    }

    public Integer getEnfermaria() { return enfermaria;}

    public Integer getFrigobar() {
        return frigobar;
    }

    public Integer getServicoQuarto() {
        return servicoQuarto;
    }


    public static List<RecepDTO> converter(List<Recepcao> recepcao) {
        return recepcao.stream().map(RecepDTO::new).collect(Collectors.toList());
    }
}
