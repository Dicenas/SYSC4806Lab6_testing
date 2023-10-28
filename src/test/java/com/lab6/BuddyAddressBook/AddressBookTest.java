package com.lab6.BuddyAddressBook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookController addressBookController;

    @Test
    public void contextLoads() {
        assertThat(addressBookController).isNotNull();
    }

    @Test
    public void responseShouldReturnMessageFromAddressBook() throws Exception {
        AddressBook buddies = new AddressBook();
        List<BuddyInfo> buddyInfoList = new ArrayList<>();
        buddyInfoList.add(new BuddyInfo("Alice", "7897 Kshlerin Throughway", "(350) 271-9203"));
        buddyInfoList.add(new BuddyInfo("Bauer", "15208 Reichel Trafficway", "(943) 763-4627"));
        buddyInfoList.add(new BuddyInfo("Chloe", "9270 Rebekah Mountains", "(457) 683-2161"));
        buddies.setBuddies(buddyInfoList);
        when(addressBookController.createAddressBook(buddies)).thenReturn(buddies);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(buddyInfoList);

        MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
        this.mockMvc.perform(post("/addressbook")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
