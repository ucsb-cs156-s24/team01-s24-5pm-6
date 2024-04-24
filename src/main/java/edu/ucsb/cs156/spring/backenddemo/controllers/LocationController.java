package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
    public static final String ENDPOINT = "https://nominatim.openstreetmap.org/search?q={location}&format=jsonv2";
}
