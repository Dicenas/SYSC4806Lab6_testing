package com.lab6.BuddyAddressBook;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    private String name;

    private String address;

    private String phoneNumber;

    public BuddyInfo() { }

    /**
     * Constructor for BuddyInfo.
     * @param name          String, name of Buddy
     * @param address       String, address of Buddy
     * @param phoneNumber   String, phone number of Buddy
     */
    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the buddy's name.
     * @param name  String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the buddy's address.
     * @param address   String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set the buddy's phone number.
     * @param phoneNumber   String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return the toString method of BuddyInfo.
     * BuddyInfo is displayed on a single line, with each attribute of the BuddyInfo separated from one another using
     * a special character.
     * @return      String
     */
    @Override
    public String toString() {
        return String.format(
                "BuddyInfo[name='%s', address='%s', phoneNumber='%s']",
                name, address, phoneNumber);
    }

    /**
     * Check for BuddyInfo object equality.
     * @param obj   BuddyInfo object
     * @return      boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        BuddyInfo otherBuddy = (BuddyInfo) obj;
        return this.name.equals(otherBuddy.name) && this.address.equals(otherBuddy.address) && this.phoneNumber.equals(otherBuddy.phoneNumber);
    }

}
