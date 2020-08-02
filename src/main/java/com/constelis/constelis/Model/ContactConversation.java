package com.constelis.constelis.Model;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ContactConversation {

    private final String date;
    private final String conversation;
    private final String trigramme;

    public ContactConversation(@JsonProperty("date") String date,
            @JsonProperty("conversation") String conversation,@JsonProperty("trigramme") String trigramme) {
        this.date = date;
        this.conversation = conversation;
        this.trigramme = trigramme;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public String getConversation() {
        return conversation;
    }

    public String getDate() {
        return date;
    }

}