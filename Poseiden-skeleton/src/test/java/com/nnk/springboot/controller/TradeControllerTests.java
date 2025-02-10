package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import com.nnk.springboot.dto.TradeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PoseidenApplication.class})
@AutoConfigureMockMvc
public class TradeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * To return to the trade/list page after saving the new trade
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addTradeTest() throws Exception {
        //Arrange
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setAccount("Account");
        tradeDTO.setType("Type1");
        tradeDTO.setBuyQuantity("10.5");

        //Act
        mockMvc.perform(post("/trade/validate")
                        .flashAttr("TradeDTO", tradeDTO)
                        .param("account", tradeDTO.getAccount())
                        .param("type", tradeDTO.getType())
                        .param("buyQuantity", tradeDTO.getBuyQuantity())
                        .contentType("TradeDTO"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To return to the trade/list page after updating the trade
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void updateBidListTest() throws Exception {
        //Arrange
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(1);
        tradeDTO.setAccount("Account");
        tradeDTO.setType("Type2");
        tradeDTO.setBuyQuantity("10.55");

        //Act
        mockMvc.perform(post("/trade/update/1")
                        .flashAttr("TradeDTO", tradeDTO)
                        .param("id", String.valueOf(tradeDTO.getId()))
                        .param("account", tradeDTO.getAccount())
                        .param("type", tradeDTO.getType())
                        .param("buyQuantity", tradeDTO.getBuyQuantity())
                        .contentType("TradeDTO"))
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
    public void addPageTradeTest() throws Exception {

        //Act
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk());

    }
}
