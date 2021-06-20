package com.exercise.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "TB_PHONE_BOOK")
public class PhoneBook {

    @Id
    @TableGenerator(name = "phonebook_sequence", table = "TB_PHONEBOOK_SEQ", pkColumnName = "SEQ_ID", valueColumnName = "GEN_VAL", pkColumnValue = "PHONEBOOK_GEN", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "phonebook_sequence")
    @Column
    private long phoneBookId;

    @Column
    private String name;

    @Email
    @Column
    private String email;


    @Column
    private long number;

    public long getPhoneBookId() {
        return phoneBookId;
    }

    public void setPhoneBookId(long phoneBookId) {
        this.phoneBookId = phoneBookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
