package br.com.taua.dto;

import lombok.Data;

@Data
public class Image {
    private Integer id;
    private String name;
    private String alternativeText;
    private String caption;
    private Integer width;
    private Integer height;
    private Format format;

}
