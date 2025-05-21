package com.mm.mm.dto;

import java.time.LocalDate;

public class ItemCreationRequest {

        private String title;
        private String description;
        private LocalDate uploadDate;
        private String category;


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

        public void setCategory(String categoryID) {
            this.category = categoryID;
        }
}


