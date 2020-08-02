package com.constelis.constelis.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;


public class ContactNeed {

    private final String date;
    @Id
    private final String id;
    private final String needs;
    private final String ao;
    private final String aoFile;
    private final String status;
    private final String cv;
    private final String cvFile;
    private final String cvsenddate;

    public ContactNeed(@JsonProperty("date") String date,  @JsonProperty("id") String id, @JsonProperty("needs") String needs,
                       @JsonProperty("ao") String ao, @JsonProperty("aoFile") String aoFile, @JsonProperty("status") String status,
                       @JsonProperty("cv") String cv, @JsonProperty("cvFile") String cvFile,
                       @JsonProperty("cvsenddate") String cvsenddate) {
        this.date = date;
        this.id = id;
        this.needs = needs;
        this.ao = ao;
        this.aoFile = aoFile;
        this.status = status;
        this.cv = cv;
        this.cvFile = cvFile;
        this.cvsenddate = cvsenddate;
    }

    public String getCvsenddate() {
        return cvsenddate;
    }

    public String getCvFile() {
        return cvFile;
    }

    public String getCv() {
        return cv;
    }

    public String getStatus() {
        return status;
    }

    public String getAoFile() {
        return aoFile;
    }

    public String getAo() {
        return ao;
    }

    public String getNeeds() {
        return needs;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

}