package com.mm.mm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemID;
    private String title;
    private String description;
    private LocalDate uploadDate;
    private String category;
    private String approvingStatus;
    private String uploaderID;
    private String approverID;


    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUploaderID() {
        return uploaderID;
    }

    public void setUploaderID(String uploaderID) {
        this.uploaderID = uploaderID;
    }

    public String getApproverID() {
        return approverID;
    }

    public void setApproverID(String approverID) {
        approverID = approverID;
    }


    public String getApprovingStatus() {
        return approvingStatus;
    }

    public void setApprovingStatus(String approvingStatus) {
        this.approvingStatus = approvingStatus;
    }
}