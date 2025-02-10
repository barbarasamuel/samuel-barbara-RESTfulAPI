package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import com.nnk.springboot.domain.RuleName;
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
public class RuleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * To return to the ruleName/list page after saving the new ruleName
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addRuleNameTest() throws Exception {
        //Arrange
        RuleName ruleName = new RuleName();
        ruleName.setName("Name");
        ruleName.setDescription("Description1");
        ruleName.setTemplate("Template");
        ruleName.setJson("{}");
        ruleName.setSqlStr("select id from rulename");
        ruleName.setSqlPart("select id");

        //Act
        mockMvc.perform(post("/ruleName/validate")
                        .flashAttr("RuleName", ruleName)
                        .param("name", ruleName.getName())
                        .param("description", ruleName.getDescription())
                        .param("template", ruleName.getTemplate())
                        .param("json", ruleName.getJson())
                        .param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlPart())
                        .contentType("RuleName"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To return to the ruleName/list page after updating the ruleName
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void updateRuleNameTest() throws Exception {
        //Arrange
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Description2");
        ruleName.setTemplate("Template2");
        ruleName.setJson("{1}");
        ruleName.setSqlStr("select * from rulename");
        ruleName.setSqlPart("select *");

        //Act
        mockMvc.perform(post("/ruleName/update/1")
                        .flashAttr("RuleName", ruleName)
                        .param("id", String.valueOf(ruleName.getId()))
                        .param("name", ruleName.getName())
                        .param("description", ruleName.getDescription())
                        .param("template", ruleName.getTemplate())
                        .param("json", ruleName.getJson())
                        .param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlPart())
                        .contentType("RuleName"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To access to the ruleName/add page
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addPageRuleNameTest() throws Exception {

        //Act
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk());

    }
}

