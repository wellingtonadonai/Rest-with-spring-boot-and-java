package com.example.wellington.udemy.data.dto;
import com.example.wellington.udemy.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
//@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;
    private String sensitiveData;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    private String adress;

    @JsonSerialize(using = GenderSerializer.class)
    private String gender;

    public PersonDTO() {
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lasttname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO personDTO)) return false;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstname(), personDTO.getFirstname()) && Objects.equals(getLastname(), personDTO.getLastname()) && Objects.equals(getPhoneNumber(), personDTO.getPhoneNumber()) && Objects.equals(getSensitiveData(), personDTO.getSensitiveData()) && Objects.equals(getBirthDay(), personDTO.getBirthDay()) && Objects.equals(getAdress(), personDTO.getAdress()) && Objects.equals(getGender(), personDTO.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getPhoneNumber(), getSensitiveData(), getBirthDay(), getAdress(), getGender());
    }
}