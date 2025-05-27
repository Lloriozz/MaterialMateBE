package com.mm.mm.dto.StudentRequest;

// Response DTO to avoid sending password back
public class StudentResponseRequest {
    private Long id;
    private String userName;
    private Integer totalCredit;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return userName; }
    public void setUsername(String username) { this.userName = username; }

    public Integer getTotalCredit() { return totalCredit; }
    public void setTotalCredit(Integer totalCredit) { this.totalCredit = totalCredit; }
}
