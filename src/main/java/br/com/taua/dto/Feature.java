package br.com.taua.dto;

import lombok.Data;

@Data
public class Feature  {
    private Integer id;
    private String description;
    private Image image;
    private Format format;
    private Xsmall xsmall;

}




