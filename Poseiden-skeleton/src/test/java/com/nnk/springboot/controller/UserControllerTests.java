package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PoseidenApplication.class})
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;


    /**
     *
     * To check the user/list page can't be accessed without the identifiers
     *
     */
    @Test
    public void userSecureTest() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To check the user/list page is not accessible with the identifiers if the role is USER
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void userNoAdminTest() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isForbidden());
    }


    /**
     *
     * To check the user/list page is accessible with the identifiers if the user is ADMIN
     *
     */
    @Test
    @WithMockUser(username="alexandre",authorities={"ADMIN"})
    public void userAdminTest() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk());
    }


}
