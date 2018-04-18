package com.example.ahsanulhoque.finoffer.domain;


import android.support.annotation.NonNull;

import java.util.Date;

public class UserProfile {

    @NonNull
    private  String id;
    @NonNull
    private String firstName;
    private String lastName;
    private String image;
    @NonNull
    private String email;
    @NonNull
    private Date created;
    private Date updated;

    public UserProfile() {
    }

    public UserProfile(String id, String firstName, String lastName, String image, String email, Date created, Date updated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
