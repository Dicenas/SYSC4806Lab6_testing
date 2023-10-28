package com.lab6.BuddyAddressBook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BuddyAddressBookApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Home")));
    }

    @Test
    public void testAddAddressBook() throws Exception{
        this.mockMvc.perform(get("/addaddressbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Create")));
    }

    @Test
    public void testShowAddressBook() throws Exception{
        this.mockMvc.perform(get("/showaddressbook?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("AddressBook")));
    }
    @Test
    public void testAddBuddy() throws Exception{
        this.mockMvc.perform(get("/addaddressbook")).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(post("/createbuddyentry")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("id", "2")
                        .param("name", "Anna")
                        .param("phoneNumber", "613-321-1234")
                        .param("address", "Street"))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/showaddressbook?id=2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Anna")));
    }

    @Test
    public void testRemoveBuddy() throws Exception{
        this.mockMvc.perform(get("/addaddressbook")).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(post("/createbuddyentry")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("id", "1")
                        .param("name", "Anna")
                        .param("phoneNumber", "613-321-1234")
                        .param("address", "Street"))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/showaddressbook?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Anna")));

        this.mockMvc.perform(post("/deletebuddyentry")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("id", "1")
                        .param("name", "Anna"))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/showaddressbook?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Anna"))));
    }

}
