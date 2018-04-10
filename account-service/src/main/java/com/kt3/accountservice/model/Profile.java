package com.kt3.accountservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Profile implements Serializable {
    @GeneratedValue
    @Id
    private int id;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private String emailAddress;

    @JsonIgnore
    @OneToOne(fetch= FetchType.LAZY, mappedBy="profile")
    private Account account;

    protected Profile() {
    }

    public Profile(String firstName, String lastName, Date birthDay, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
