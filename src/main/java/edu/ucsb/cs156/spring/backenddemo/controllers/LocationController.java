package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Tag(name="Location info from nominatim.org")
@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LocationQueryService locationQueryService;

    @Operation(summary = "Get a list of locations that match a given location name", description = "Uses API documented here: https://nominatim.org/release-docs/develop/api/Search/")
    @GetMapping("/get")
    public ResponseEntity<String> getLocations(
        @Parameter(name="location", example="name to search, e.g. \'Isla Vista\' or \'Eiffel Tower\'") @RequestParam String location
    ) throws JsonProcessingException {
        String result = locationQueryService.getJSON(location);
        return ResponseEntity.ok().body(result);
    }

}