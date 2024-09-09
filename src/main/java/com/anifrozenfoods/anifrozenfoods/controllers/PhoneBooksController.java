package com.anifrozenfoods.anifrozenfoods.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.anifrozenfoods.anifrozenfoods.models.PhoneBook;
import com.anifrozenfoods.anifrozenfoods.models.PhoneBookDto;
import com.anifrozenfoods.anifrozenfoods.services.PhoneBooksRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/phoneBooks")
public class PhoneBooksController {
    @Autowired
    private PhoneBooksRepository repo;

    @GetMapping({ "", "/" })
    public String showProductList(Model model) {
        List<PhoneBook> phoneBooks = repo.findAll();
        model.addAttribute("phoneBooks", phoneBooks);
        return "phoneBooks/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        PhoneBookDto phoneBookDto = new PhoneBookDto();
        model.addAttribute("phoneBookDto", phoneBookDto);
        return "phoneBooks/CreatePhoneBook";
    }

    @PostMapping("/create")
    public String createPhoneBook(
            @Valid @ModelAttribute PhoneBookDto phoneBookDto,
            BindingResult result) {

        if (phoneBookDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("phoneBookDto", "imageFile", "The image file is empty"));
        }

        if (result.hasErrors()) {
            return "phoneBooks/CreatePhoneBook";
        }

        // Convert PhoneBookDto to PhoneBook entity
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setName(phoneBookDto.getName());
        phoneBook.setGender(phoneBookDto.getGender());
        phoneBook.setPhone_no(phoneBookDto.getPhone_no());
        phoneBook.setEmail(phoneBookDto.getEmail());
        phoneBook.setCategory(phoneBookDto.getCategory());

        // Save image file
        MultipartFile image = phoneBookDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        // Set additional fields and save product
        phoneBook.setCreatedAt(createdAt);
        phoneBook.setImageFileName(storageFileName);

        repo.save(phoneBook);

        return "redirect:/phoneBooks";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id) {

        try {
            PhoneBook phoneBook = repo.findById(id).get();
            model.addAttribute("phoneBook", phoneBook);

            PhoneBookDto phoneBookDto = new PhoneBookDto();
            phoneBookDto.setName(phoneBook.getName());
            phoneBookDto.setGender(phoneBook.getGender());
            phoneBookDto.setPhone_no(phoneBook.getPhone_no());
            phoneBookDto.setEmail(phoneBook.getEmail());
            phoneBookDto.setCategory(phoneBook.getCategory());

            model.addAttribute("phoneBookDto", phoneBookDto);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/phoneBooks";
        }

        return "phoneBooks/EditPhoneBook";
    }

    @PostMapping("/edit")
    public String updatePhoneBook(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute PhoneBookDto phoneBookDto,
            BindingResult result) {

        try {
            PhoneBook phoneBook = repo.findById(id).get();
            model.addAttribute("phoneBook", phoneBook);

            if (result.hasErrors()) {
                return "phoneBooks/EditPhoneBook";
            }

            if (!phoneBookDto.getImageFile().isEmpty()) {
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + phoneBook.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }

                MultipartFile image = phoneBookDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }

                phoneBook.setImageFileName(storageFileName);

            }

            phoneBook.setName(phoneBookDto.getName());
            phoneBook.setGender(phoneBookDto.getGender());
            phoneBook.setPhone_no(phoneBookDto.getPhone_no());
            phoneBook.setEmail(phoneBookDto.getEmail());
            phoneBook.setCategory(phoneBookDto.getCategory());
            repo.save(phoneBook);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/phoneBooks";
    }

    @GetMapping("/delete")
    public String deletePhoneBook(
            @RequestParam int id) {

        try {
            PhoneBook phoneBook = repo.findById(id).get();

            Path imagePath = Paths.get("public/images/" + phoneBook.getImageFileName());

            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

            repo.delete(phoneBook);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/phoneBooks";
    }
/*
    @GetMapping("/emailEditor")
    public String showEmailEditor(@RequestParam String to, Model model) {
        model.addAttribute("to", to);
        model.addAttribute("email", new Email());
        return "emailEditor";
    }
*/
}
