package com.hotel.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping
    public ResponseEntity<Map<String, String>> getStaff(){

        Map<String, String> staff = new HashMap<>();
        staff.put("Staff-1", "Gaurav");
        staff.put("Staff-2", "Vaibhav");
        staff.put("Staff-3", "Devang");

        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
}
