package com.example.testeonsigth2.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "genres",
        "id",
        "name"
})
public class Generos {

    @JsonProperty("genres")
    public List<Genero> generos = null;
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;

}
