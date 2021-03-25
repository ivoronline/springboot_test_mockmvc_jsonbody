package com.ivoronline.springboot_test_mockmvc_jsonbody.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoronline.springboot_test_mockmvc_jsonbody.DTO.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MyControllerTest {

  @Autowired MockMvc      mockMvc;
  @Autowired MyController myController;
  @Autowired ObjectMapper objectMapper;

  @Test
  void addPerson() throws Exception {

    //CREATE PERSON DTO
    PersonDTO personDTO      = new PersonDTO();
              personDTO.name = "John";
              personDTO.age  = 20;

    //SERIALIZE PERSON DTO INTO JSON STRING
    String personDTOSeriaized = objectMapper.writeValueAsString(personDTO);

    //CREATE REQUEST
    MockHttpServletRequestBuilder request =
       post       ("/AddPerson")
      .contentType("application/json")
      .content    (personDTOSeriaized);

    //PERFORM REQUEST
    mockMvc.perform(request).andExpect(status().isOk());

  }

}


