package com.mm.mm.dto.StudentRequest;

public class StudentProfileSetupRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;  // match frontend key
    private String email;
    private String dateOfBirth;  // match frontend key
    private String country;
    private String role;
    private String university;

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
}