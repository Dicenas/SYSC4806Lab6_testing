package com.lab5.BuddyAddressBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    @NonNull
    List<AddressBook> findAll();
}
