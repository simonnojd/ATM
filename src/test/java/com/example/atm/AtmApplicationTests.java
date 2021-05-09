package com.example.atm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AtmApplicationTests {

    // YOU HAVE TO RUN THE TESTS SEPARATELY FOR THEM TO PASS

    @Autowired
    private MockMvc mvc;

    // Test to see if bills are printed correctly in JSON format
    @Test
    public void getBillsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/atm/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"value\":1000,\"quantity\":2},{\"id\":2,\"value\":500,\"quantity\":3},{\"id\":3,\"value\":100,\"quantity\":5}]"));
    }

    // Test to withdraw 400 and get the correct output
    @Test
    public void withdraw400Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/atm/withdraw/400").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Your amount to withdraw was: 400. There are 2 1000-bills left. There are 3 500-bills left. There are 1 100-bills left."));
    }

    // Test to withdraw 600 and get the correct output
    @Test
    public void withdraw600Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/atm/withdraw/600").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Your amount to withdraw was: 600. There are 2 1000-bills left. There are 2 500-bills left. There are 4 100-bills left."));
    }

    // Test to withdraw 1000 and get the correct output
    @Test
    public void withdraw1000Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/atm/withdraw/1000").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Your amount to withdraw was: 1000. There are 1 1000-bills left. There are 3 500-bills left. There are 5 100-bills left."));
    }

    // Test to withdraw 1700 and get the correct output
    @Test
    public void withdraw1700Test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/atm/withdraw/1700").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Your amount to withdraw was: 1700. There are 1 1000-bills left. There are 2 500-bills left. There are 3 100-bills left."));
    }

    // Test to withdraw too much and get the correct output
    @Test
    public void withdrawTooMuchTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/atm/withdraw/10000").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Can't withdraw that. There are 2 1000-bills left. There are 3 500-bills left. There are 5 100-bills left."));
    }
}
