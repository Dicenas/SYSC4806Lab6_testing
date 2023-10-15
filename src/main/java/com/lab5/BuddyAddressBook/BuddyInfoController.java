package com.lab5.BuddyAddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value="/buddyinfo")
public class BuddyInfoController {
    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddyInfo) {
        return buddyInfoRepository.save(buddyInfo);
    }

    @GetMapping
    public String buddyInfo() {
        return "buddyinfo";
    }

    @GetMapping(value="/{id}")
    public String showBuddyInfo(@PathVariable Long id, Model model) {
        Optional<BuddyInfo> buddyInfoOptional = buddyInfoRepository.findById(id);
        if (buddyInfoOptional.isPresent()) {
            BuddyInfo buddyInfo = buddyInfoOptional.get();
            model.addAttribute("buddyInfo", buddyInfo);
            return "buddyinfo";
        } else {
            // Handle address book not found
            return "error";
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteBuddyInfo(@PathVariable Long id) {
        buddyInfoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
