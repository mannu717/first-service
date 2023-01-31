package com.javainuse.dto;

public class PersonDataResponse {

    private String personId;

    public PersonDataResponse(String personId) {
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public static PersonDataResponse of(String personId) {
        return new PersonDataResponse(personId);
    }
}
