// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package com.lab5.BuddyAddressBook;

import jakarta.persistence.*;

/**
 * This class contains information about a buddy.
 * @author Trong Nguyen
 */
@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;

    /**
     * Creates a new instance of BuddyInfo.
     */
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
     * Gets the id of this BuddyInfo. The persistence provider should
     * autogenerate a unique id for new BuddyInfo objects.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the buddy's name.
     * @return      String, buddy's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the buddy's address.
     * @return      String, buddy's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the buddy's phone number.
     * @return      String, buddy's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
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