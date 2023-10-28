package com.lab6.BuddyAddressBook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BuddyAddressBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuddyAddressBookApplication.class, args);
	}

	/**
	 * Populate AddressBook with initial buddies.
	 * @param repository	AddressBookRepository
	 * @return	CommandLineRunner
	 */
	@Bean
	public CommandLineRunner addressable(AddressBookRepository repository) {
		return (args) -> {
			AddressBook buddies = new AddressBook();
			List<BuddyInfo> buddyInfoList = new ArrayList<>();
			// save a few BuddyInfos
			buddyInfoList.add(new BuddyInfo("Alice", "7897 Kshlerin Throughway", "(350) 271-9203"));
			buddyInfoList.add(new BuddyInfo("Bauer", "15208 Reichel Trafficway", "(943) 763-4627"));
			buddyInfoList.add(new BuddyInfo("Chloe", "9270 Rebekah Mountains", "(457) 683-2161"));
			buddyInfoList.add(new BuddyInfo("David", "837 Marlon Keys", "(648) 423-9916"));
			buddyInfoList.add(new BuddyInfo("Elenor", "2483 Pouros Mews", "(386) 353-8892"));
			buddies.setBuddies(buddyInfoList);
			repository.save(buddies);
		};
	}

}
