package com.example.testeonsigth2.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iso_3166_1",
        "name"
})
public class ProdutoraPais {

    @JsonProperty("iso_3166_1")
    public String iso31661;
    @JsonProperty("name")
    public String name;

}
