package com.nnk.springboot.controller;

import com.nnk.springboot.PoseidenApplication;
import com.nnk.springboot.domain.Rating;
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
public class RatingControllerTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * To return to the rating/list page after saving the new rating
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addRatingTest() throws Exception {
        //Arrange
        Rating rating = new Rating();
        rating.setMoodysRating("MoodysRating");
        rating.setSandPRating("SandPRating");
        rating.setFitchRating("FitchRating");
        rating.setOrderNumber(12);

        //Act
        mockMvc.perform(post("/rating/validate")
                        .flashAttr("Rating", rating)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", rating.getFitchRating())
                        .param("orderNumber", String.valueOf(rating.getOrderNumber()))
                        .contentType("Rating"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To return to the rating/list page after updating the rating
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void updateRatingTest() throws Exception {
        //Arrange
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("MoodysRating2");
        rating.setSandPRating("SandPRating2");
        rating.setFitchRating("FitchRating2");
        rating.setOrderNumber(14);

        //Act
        mockMvc.perform(post("/rating/update/1")
                        .flashAttr("Rating", rating)
                        .param("id", String.valueOf(rating.getId()))
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", rating.getFitchRating())
                        .param("orderNumber", String.valueOf(rating.getOrderNumber()))
                        .contentType("Rating"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

    }

    /**
     *
     * To access to the rating/add page
     *
     */
    @Test
    @WithMockUser(username="paul",roles={"USER"})
    public void addPageRatingTest() throws Exception {

        //Act
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());

    }
}
