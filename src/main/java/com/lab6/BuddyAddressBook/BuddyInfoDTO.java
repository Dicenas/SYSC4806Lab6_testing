package com.lab6.BuddyAddressBook;


public class BuddyInfoDTO {
    private Long addressBookId;
    private Long buddyId;
    private BuddyInfo buddy;

    public BuddyInfo getBuddy() {
        return buddy;
    }

    public BuddyInfoDTO(Long addressBookId) {
        this.addressBookId = addressBookId;
        this.buddy = null;
        this.buddyId = null;
    }

    public BuddyInfoDTO(Long addressBookId, BuddyInfo buddy) {
        this(addressBookId);
        this.buddy = buddy;
        this.buddyId = buddy.getId();
    }

    public Long getAddressBookId() {
        return addressBookId;
    }

    public Long getBuddyId() {
        return buddyId;
    }
}
