package com.constelis.constelis.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactInformation {
    private final String observation;
    private final String technoTools;
    private final LocalDateTime plaquette;

    public ContactInformation(@JsonProperty("observation") String observation,
            @JsonProperty("technoTools") String technoTools, @JsonProperty("plaquette") LocalDateTime plaquette) {
        this.observation = observation;
        this.technoTools = technoTools;
        this.plaquette = plaquette;
    }

    public LocalDateTime getPlaquette() {
        return plaquette;
    }

    public String getTechnoTools() {
        return technoTools;
    }

    public String getObservation() {
        return observation;
    }

}