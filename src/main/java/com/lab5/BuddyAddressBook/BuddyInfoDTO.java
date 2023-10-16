package com.lab5.BuddyAddressBook;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
public class BuddyInfoDTO {
    private Long addressBookId;
    private Long buddyId;
    private BuddyInfo buddy;

    public BuddyInfoDTO(Long addressBookId){
        this.addressBookId = addressBookId;
        this.buddy = null;
        this.buddyId = null;
    }

    public BuddyInfoDTO(Long addressBookId, BuddyInfo buddy){
        this(addressBookId);
        this.buddy = buddy;
        this.buddyId = buddy.getId();
    }
}
