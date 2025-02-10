package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import com.nnk.springboot.dto.BidListDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PoseidenApplication.class})
@AutoConfigureMockMvc
public class BidControllerTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * To return to the bidList/list page after saving the new bidList
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addBidListTest() throws Exception {
        //Arrange
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setAccount("Account");
        bidListDTO.setType("Type1");
        bidListDTO.setBidQuantity("10.5");

        //Act
        mockMvc.perform(post("/bidList/validate")
                        .flashAttr("BidListDTO", bidListDTO)
                        .param("account", bidListDTO.getAccount())
                        .param("type", bidListDTO.getType())
                        .param("bidQuantity", bidListDTO.getBidQuantity())
                        .contentType("BidListDTO"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To return to the bidList/list page after updating the bidList
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void updateBidListTest() throws Exception {
        //Arrange
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setId(1);
        bidListDTO.setAccount("Account");
        bidListDTO.setType("Type2");
        bidListDTO.setBidQuantity("10.5");

        //Act
        mockMvc.perform(post("/bidList/update/1")
                        .flashAttr("BidListDTO", bidListDTO)
                        .param("id", String.valueOf(bidListDTO.getId()))
                        .param("account", bidListDTO.getAccount())
                        .param("type", bidListDTO.getType())
                        .param("bidQuantity", bidListDTO.getBidQuantity())
                        .contentType("BidListDTO"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To access to the bidList/add page
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addPageBidListTest() throws Exception {

        //Act
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());

    }
}
