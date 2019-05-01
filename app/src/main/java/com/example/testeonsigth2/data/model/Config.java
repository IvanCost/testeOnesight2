package com.example.testeonsigth2.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Config {

    @JsonProperty("images")
    public Imagens imagens;
    @JsonProperty("change_keys")
    public List<String> changeKeys = null;

}
