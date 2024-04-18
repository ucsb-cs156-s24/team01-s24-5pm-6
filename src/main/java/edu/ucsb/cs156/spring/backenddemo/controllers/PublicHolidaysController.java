package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Public holiday info from Nager")
@Slf4j
@RestController
@RequestMapping("/api/publicholidays/get")
public class PublicHolidaysController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary = "Public Holiday Info from https://date.nager.at/Api", description = "Get public holidays for a given year and country")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="countryCode", description="2 letter country code, e.g. US, MX, CN", example="US") @RequestParam String countryCode,
        @Parameter(name="year", description="year, e.g. 2012", example="2012") @RequestParam String year
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}
