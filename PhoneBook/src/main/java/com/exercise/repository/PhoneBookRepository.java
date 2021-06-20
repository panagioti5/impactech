package com.exercise.repository;

import com.exercise.core.constants.ErrorCode;
import com.exercise.core.exceptions.ExceptionResponse;
import com.exercise.entities.PhoneBook;
import com.exercise.exceptions.PhoneBookNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PhoneBookRepository extends JpaRepository<PhoneBook,Long> {

    default PhoneBook findPhoneBookById(long phoneBookId) {
        Optional<PhoneBook> phoneBook = this.findById(phoneBookId);
        if(!phoneBook.isPresent()){
            throw new PhoneBookNotFoundException(new ExceptionResponse("No Phone Book with ID: " + phoneBookId + " found", ErrorCode.PHONE_BOOK_ID_NOT_FOUND, new Date()));
        }
        return phoneBook.get();
    }

}
