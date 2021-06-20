package com.exercise.controller;

import com.exercise.core.exceptions.RuntimeExceptionImpl;
import com.exercise.core.utils.CoreUtils;
import com.exercise.entities.Cdr;
import com.exercise.repository.CdrHistoryRepository;
import com.exercise.repository.CdrRepository;
import com.exercise.utils.TestUtils;
import com.exercise.validation.CdrValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CdrController.class)
class CdrControllerTest {

    @MockBean
    private CdrRepository cdrRepository;

    @MockBean
    private CdrHistoryRepository cdrHistoryRepository;

    @MockBean
    private CoreUtils coreUtils;

    @Autowired
    private MockMvc mvc;


    @Test
    void invocationTargetExceptionWhileCallingPbxMicroservice() throws Exception {
        Cdr givenCdr = TestUtils.getInstance().createNewCdrPOJO();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(givenCdr);

        mvc.perform(post("/cdr")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeExceptionImpl));
    }

}