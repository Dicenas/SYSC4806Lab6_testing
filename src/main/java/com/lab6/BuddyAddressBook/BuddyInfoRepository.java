package com.lab6.BuddyAddressBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Crud repository for BuddyInfo.
 * @author trong0dn
 */
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    @NonNull
    List<BuddyInfo> findAll();

    Optional<BuddyInfo> findByName(String name);

}
