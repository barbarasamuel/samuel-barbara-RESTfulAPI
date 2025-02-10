package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import com.nnk.springboot.dto.CurvePointDTO;
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
public class CurvePointControllerTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * To return to the curvePoint/list page after saving the new curvePoint
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addCurvePointTest() throws Exception {
        //Arrange
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setCurveId(1);
        curvePointDTO.setTerm("10.2");
        curvePointDTO.setValue("10.5");

        //Act
        mockMvc.perform(post("/curvePoint/validate")
                        .flashAttr("CurvePointDTO", curvePointDTO)
                        .param("curveId", String.valueOf(curvePointDTO.getCurveId()))
                        .param("term", curvePointDTO.getTerm())
                        .param("value", curvePointDTO.getValue())
                        .contentType("CurvePointDTO"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To return to the curvePoint/list page after updating the curvePoint
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void updateCurvePointTest() throws Exception {
        //Arrange
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        curvePointDTO.setCurveId(1);
        curvePointDTO.setTerm("10.22");
        curvePointDTO.setValue("10.55");

        //Act
        mockMvc.perform(post("/curvePoint/update/1")
                        .flashAttr("CurvePointDTO", curvePointDTO)
                        .param("id", String.valueOf(curvePointDTO.getId()))
                        .param("curveId", String.valueOf(curvePointDTO.getCurveId()))
                        .param("term", curvePointDTO.getTerm())
                        .param("value", curvePointDTO.getValue())
                        .contentType("CurvePointDTO"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To access to the curvePoint/add page
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addPageCurvePointTest() throws Exception {

        //Act
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk());

    }
}
