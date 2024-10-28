package com.lab6.BuddyAddressBook;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<BuddyInfo> buddies;

    /**
     * Creates a new instance of AddressBook.
     */
    public AddressBook() {
        this.buddies = new ArrayList<>();
    }

    /**
     * Set BuddyInfo object.
     * @param buddies   List<BuddyInfo> object
     */
    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    /**
     * Add BuddyInfo object to AddressBook.
     * @param aBuddy     BuddyInfo object
     */
    public void addBuddy(BuddyInfo aBuddy) {
        if (aBuddy == null || buddies.contains(aBuddy))
            return;
        this.buddies.add(aBuddy);
    }

    /**
     * Remove BuddyInfo object from AddressBook.
     * @ @param aBuddy     BuddyInfo object
     */
    public void removeBuddy(BuddyInfo aBuddy) {
        if (aBuddy == null || !buddies.contains(aBuddy))
            return;
        this.buddies.remove(aBuddy);
    }

    /**
     * Get the size of the AddressBook.
     * @return  int
     */
    public int size() {
        return buddies.size();
    }

    /**
     * Check for AddressBook object equality.
     * @param obj   Objects
     * @return      boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AddressBook addressBook))
            return false;
        return this.size() == addressBook.size();
    }

    /**
     * Return the toString method of AddressBook.
     * @return  String
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
