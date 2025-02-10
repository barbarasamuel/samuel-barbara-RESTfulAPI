package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PoseidenApplication.class})
@AutoConfigureMockMvc
public class LoginControllerTests {
    @Autowired
    MockMvc mockMvc;

    /**
     *
     * To test we stay in the login page when the password is wrong
     *
     */
    @Test
    public void loginSecureTest() throws Exception {
        mockMvc.perform(post("/access")
                        .param("username","alexandre")
                        .param("password","Admin25"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andReturn();
    }

    /**
     *
     * Should go to login page
     *
     */
    @Test
    public void loginGoToLoginTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();
    }

    /**
     *
     * Should go to login page after click in the logout link
     *
     */
    @Test
    public void logoutGoToLoginTest() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"))
                .andReturn();
    }
}

