package com.mm.mm.dto.ItemRequest;

public class ItemCreationRequest {

        private String title;
        private String description;
        private String uploadDate;
        private String category;
        private String approvingStatus;

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

        public String getUploadDate() {
            return uploadDate;
        }

        public void setUploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String categoryID) {
            this.category = categoryID;
        }

        public String getApprovingStatus() {
            return approvingStatus;
        }

        public void setApprovingStatus(String approvingStatus) {
            this.approvingStatus = approvingStatus;
        }
}


