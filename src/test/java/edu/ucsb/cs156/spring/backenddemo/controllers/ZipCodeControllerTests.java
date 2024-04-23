package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ZipCodeController.class)
public class ZipCodeControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ZipCodeQueryService mockZipCodeQueryService;

    @Test
    public void test_zipCode() throws Exception {
        String fakeJsonResult = "{ \"fake\" : \"result\" }";
        String zipcode = "93117";
        when(mockZipCodeQueryService.getJSON(zipcode)).thenReturn(fakeJsonResult);

        String url = String.format("/api/zipcode/get?zipcode=%s", zipcode);

        MvcResult response = mockMvc
                .perform(get(url).contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        String responseString = response.getResponse().getContentAsString();

        assertEquals(fakeJsonResult, responseString);
    }
}
