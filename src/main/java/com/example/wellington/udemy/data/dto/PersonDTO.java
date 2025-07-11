package com.example.wellington.udemy.data.dto;
import java.io.Serializable;
import java.util.Objects;


public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstname;
    private String lasttname;
    private String adress;
    private String gender;

    public PersonDTO(){}

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

    public String getLasttname() {
        return lasttname;
    }

    public void setLasttname(String lasttname) {
        this.lasttname = lasttname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO person)) return false;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstname(), person.getFirstname()) && Objects.equals(getLasttname(), person.getLasttname()) && Objects.equals(getAdress(), person.getAdress()) && Objects.equals(getGender(), person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLasttname(), getAdress(), getGender());
    }
}
