package com.constelis.constelis.Model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("contact")
public class Contact {

    @Id
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String status;
    private final String emailContact;
    private final String title;
    private final String linkedinContact;
    private final LocalDate reminder;
    private final String cphones1;
    private final String cphones2;
    private final String observation;
    private final String plaquetteinfo;
    private final String technoTools;
    private final LocalDate plaquette;
    private final String mobile;
    private  List<ContactNeed> contactNeed;
    private final String[] contactPushs;
    private List<ContactConversation> contactConversation;
    private  List<ContactInformation> contactInformation;

    public Contact(

            @JsonProperty("id") String id, @JsonProperty("lastName") String lastName,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("status") String status,
            @JsonProperty("emailContact") String emailContact,
            @JsonProperty("title") String title,
            @JsonProperty("reminder") LocalDate reminder,
            @JsonProperty("cphones2") String cphones2,
            @JsonProperty("cphones1") String cphones1,
            @JsonProperty("observation") String observation,
            @JsonProperty("plaquetteinfo") String plaquetteinfo,
            @JsonProperty("technoTools") String technoTools,
            @JsonProperty("plaquette") LocalDate plaquette,
            @JsonProperty("mobile") String mobile,
            @JsonProperty("linkedinContact") String linkedinContact,
            @JsonProperty("contactNeed") List<ContactNeed> contactNeed,
            @JsonProperty("contactPushs") String[] contactPushs,
            @JsonProperty("contactConversation") List<ContactConversation> contactConversation,
            @JsonProperty("contactInformation") List<ContactInformation> contactInformation
    ) {

        this.id = id;

        this.lastName = lastName;
        this.status = status;
        this.emailContact = emailContact;
        this.firstName=firstName;
        this.title = title;
        this.reminder = reminder;
        this.cphones1 = cphones1;
        this.cphones2 = cphones2;
        this.observation = observation;
        this.plaquetteinfo = plaquetteinfo;
        this.technoTools = technoTools;
        this.plaquette = plaquette;
        this.mobile = mobile;
        this.linkedinContact = linkedinContact;
        this.contactNeed = contactNeed;
        this.contactPushs = contactPushs;
        this.contactConversation = contactConversation;
        this.contactInformation = contactInformation;
    }

    public String getId() {
        return id;
    }

    public List<ContactInformation> getContactInformation() {
        return contactInformation;
    }

    public List<ContactConversation> getContactConversation() {
        if (contactConversation == null) {
            contactConversation = new ArrayList<ContactConversation>();
        }
        return contactConversation;
    }

    public String[] getContactPushs() {
        return contactPushs;
    }

    public List<ContactNeed> getContactNeed() {
        if(contactNeed==null){
            contactNeed = new ArrayList<ContactNeed>();
        }
        return contactNeed;
    }

    public String getPlaquetteinfo() {
        return plaquetteinfo;
    }

    public String getLinkedinContact() {
        return linkedinContact;
    }

    public String getCphones1() {
        return cphones1;
    }

    public String getObservation() {
        return observation;
    }

    public LocalDate getPlaquette() {
        return plaquette;
    }

    public String getTechnoTools() {
        return technoTools;
    }

    public String getCphones2() {
        return cphones2;
    }

    public String getMobile() {
        return mobile;
    }

    public LocalDate getReminder() {
        return reminder;
    }

    public String getTitle() {
        return title;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public String getStatus() {
        return status;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public HashMap<String,String> StringData(){
        HashMap<String,String> data = new HashMap<>();
        data.put("linkedinContact",getLinkedinContact());
        data.put("status",getStatus());
        data.put("emailContact",getEmailContact());
        data.put("lastName",getLastName());
        data.put("mobile",getMobile());
        data.put("cphones1",getCphones1());
        data.put("cphones2",getCphones2());
        data.put("title",getTitle());
        data.put("firstName",getFirstName());

        data.put("observation",getObservation());
        data.put("technoTools",getTechnoTools());
        data.put("plaquetteinfo",getPlaquetteinfo());
        return  data;
    }

}