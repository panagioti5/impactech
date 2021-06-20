package com.exercise.controller;

import com.exercise.entities.PhoneBook;
import com.exercise.repository.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhoneBookController {

    private final PhoneBookRepository phoneBookRepository;

    @Autowired
    public PhoneBookController(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }

    @PostMapping("/phonebook")
    public void createPhoneBook(@RequestBody PhoneBook phoneBook){
        phoneBookRepository.save(phoneBook);
    }

    @DeleteMapping("/phonebook/{pbId}")
    public void deletePbx(@PathVariable long pbId){
        phoneBookRepository.delete(phoneBookRepository.findPhoneBookById(pbId));
    }

    @PutMapping("/phonebook")
    public void updatePbx(@RequestBody PhoneBook phoneBook){
        phoneBookRepository.findPhoneBookById(phoneBook.getPhoneBookId());
        phoneBookRepository.save(phoneBook);
    }

    @GetMapping("/phonebook/{phonebookId}")
    public PhoneBook findPhoneBookById(@PathVariable long phonebookId){
        return phoneBookRepository.findPhoneBookById(phonebookId);
    }

}
