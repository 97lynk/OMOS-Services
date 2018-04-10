/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt3.accountservice.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author 97lynk
 */
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userName;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Profile profile;


    private boolean enabled;


    //contructors
    public Account() {
        this.enabled = false;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.enabled = false;
    }

    // standard getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonGetter("password")
    public String getHiddenPassword() {
        return "*******";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account acc = (Account) obj;
        if (!userName.equals(acc.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Account [id=").append(id)
                .append(", userName=").append(userName)
                .append(", password=").append(password)
                .append(", enabled=").append(enabled);
        return builder.toString();
    }
}
