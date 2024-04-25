package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;


import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(value = LocationController.class)
public class LocationControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  LocationQueryService mockLocationQueryService; //java object that has a certain interface, has a constructor w no parameters, and getters and setters

  @Test
  public void test_getLocations() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    String location = "California";
    when(mockLocationQueryService.getJSON(eq(location))).thenReturn(fakeJsonResult);

    String url = String.format("/api/locations/get?location=%s", location); //get because in our controller we implement mapping at /get

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json")) //check that content type is json
        .andExpect(status().isOk()).andReturn(); //will be same as fake json result

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}