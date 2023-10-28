package com.lab6.BuddyAddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping()
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/addressbook")
    public String addressBook(Model model) {
        List<AddressBook> addressBookList = addressBookRepository.findAll();
        model.addAttribute("AddressBookList", addressBookList);
        return "addressbook";
    }

    @GetMapping("/addaddressbook")
    public String addAddressBook(Model model){
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        model.addAttribute("AddressId", addressBook.getId());
        return "addaddressbook";
    }

    @GetMapping(value="/showaddressbook")
    public String showAddressBook(@RequestParam Long id, Model model) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            model.addAttribute("addressBook", addressBook);
            return "showaddressbook";
        } else {
            // Handle address book not found
            return "error";
        }
    }

    @GetMapping("/addbuddy")
    public String addBuddy(@RequestParam Long id, Model model){
        BuddyInfoDTO buddyDTO = new BuddyInfoDTO(id, new BuddyInfo());
        model.addAttribute("BuddyDTO", buddyDTO);
        return "addbuddyinfo";
    }

    @GetMapping("/removebuddy")
    public String removeBuddy(@RequestParam Long id, Model model){
        BuddyInfoDTO buddyDTO = new BuddyInfoDTO(id);
        model.addAttribute("BuddyDTO", buddyDTO);
        return "removeBuddyInfo";
    }

    @PostMapping("/createbuddy")
    public String createBuddy(@ModelAttribute("BuddyDTO") BuddyInfoDTO buddyDTO, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(buddyDTO.getAddressBookId());
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            addressBook.addBuddy(buddyDTO.getBuddy());
            addressBookRepository.save(addressBook);
            redirectAttributes.addAttribute("id", addressBook.getId());
            return "redirect:showaddressbook";
        }
        else {
            return "redirect:addressbook";
        }
    }

    /**
     * Create buddy manually for unit testing.
     * @param id Long, buddy id number
     * @param name String, buddy name
     * @param phoneNumber String, buddy phone number
     * @param address String, buddy address
     * @param redirectAttributes RedirectAttribute
     * @return String, post mapping
     */
    @PostMapping("/createbuddyentry")
    public String createBuddy(@RequestParam Long id, @RequestParam String name, @RequestParam String phoneNumber, @RequestParam String address, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);
        if (addressBookOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            BuddyInfo buddy = new BuddyInfo(name, phoneNumber, address);
            addressBook.addBuddy(buddy);
            addressBookRepository.save(addressBook);
            redirectAttributes.addAttribute("id", addressBook.getId());
            return "redirect:showaddressbook";
        }
        else {
            return "redirect:addressbook";
        }
    }

    @PostMapping("/deletebuddy")
    public String deleteBuddy(@ModelAttribute("BuddyDTO") BuddyInfoDTO buddyDTO, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(buddyDTO.getAddressBookId());
        Optional<BuddyInfo> buddyInfoOptional = buddyInfoRepository.findById(buddyDTO.getBuddyId());
        return getString(redirectAttributes, addressBookOptional, buddyInfoOptional);
    }

    /**
     * Delete buddy manually for unit testing.
     * @param id Long, buddy id number
     * @param name String, buddy name
     * @param redirectAttributes RedirectAttribute
     * @return String, post mapping
     */
    @PostMapping("/deletebuddyentry")
    public String deleteBuddy(@RequestParam Long id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);
        Optional<BuddyInfo> buddyInfoOptional = buddyInfoRepository.findByName(name);
        return getString(redirectAttributes, addressBookOptional, buddyInfoOptional);
    }

    private String getString(RedirectAttributes redirectAttributes, Optional<AddressBook> addressBookOptional, Optional<BuddyInfo> buddyInfoOptional) {
        if (addressBookOptional.isPresent() && buddyInfoOptional.isPresent()) {
            AddressBook addressBook = addressBookOptional.get();
            BuddyInfo buddyInfo = buddyInfoOptional.get();
            addressBook.removeBuddy(buddyInfo);
            addressBookRepository.save(addressBook);
            redirectAttributes.addAttribute("id", addressBook.getId());
            return "redirect:showaddressbook";
        } else {
            return "redirect:addressbook";
        }
    }

    @DeleteMapping(value="/")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        addressBookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
