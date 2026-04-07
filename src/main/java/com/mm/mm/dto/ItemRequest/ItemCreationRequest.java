package com.mm.mm.dto.ItemRequest;

import org.springframework.web.multipart.MultipartFile;

public class ItemCreationRequest {

    private String title;
    private String description;
    private String category;
    private String uploaderID;
    private MultipartFile pdfFile;
    private MultipartFile imageCover;

    // Getters and setters

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

    public MultipartFile getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(MultipartFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public MultipartFile getImageCover() {
        return imageCover;
    }

    public void setImageCover(MultipartFile imageCover) {
        this.imageCover = imageCover;
    }
}
