package com.example.cognicolor;

public class User {
    private String username;
    private String email;
    private String dob;
    private String country;

    public User(String username, String email, String dob, String country) {
        this.username = username;
        this.email = email;
        this.dob = dob;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }
}
