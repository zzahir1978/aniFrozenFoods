package com.anifrozenfoods.anifrozenfoods.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class PhoneBookDto {

    @NotEmpty(message = "The name is required")
    private String name;

    @NotEmpty(message = "The gender is required")
    private String gender;

    @NotEmpty(message = "The phone no. is required")
    private String phone_no;

    @NotEmpty(message = "The email is required")
    private String email;

    @NotEmpty(message = "The category is required")
    private String category;

    private MultipartFile imageFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

}