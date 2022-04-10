package com.interview.ct.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CoindeskControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldGetAOriginAPIWhenCallMethod() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/coindesk")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        assertNotNull(result);

        JSONObject jsonObject = new JSONObject(result);
        assertNotEquals(new JSONObject(), jsonObject);
    }

    @Test
    public void shouldGetAReshapeAPIWhenCallMethod() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/coindeskRe")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        assertNotNull(result);

        JSONObject jsonObject = new JSONObject(result);
        assertNotEquals(new JSONObject(), jsonObject);
    }
}
