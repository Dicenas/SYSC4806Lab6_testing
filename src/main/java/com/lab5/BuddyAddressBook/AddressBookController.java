package com.lab5.BuddyAddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value="/addressbook")
public class AddressBookController {
    @Autowired
    private AddressBookRepository addressBookRepository;

    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @GetMapping
    public String addressBook() {
        return "addressbook";
    }

    @GetMapping(value="/{id}")
    public String showAddressBook(@PathVariable Long id, Model model) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            model.addAttribute("addressBook", addressBook.getBuddies());
            return "addressbook";
        } else {
            // Handle address book not found
            return "error";
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        addressBookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
