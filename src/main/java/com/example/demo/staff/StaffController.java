package com.example.demo.staff;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    // You can add more endpoints to manage staff-related data
    @GetMapping("/details")
    public List<String> getStaffDetails()
    {
        // Example data, replace with actual logic
        return List.of("Staff member 1", "Staff member 2", "Staff member 3");
    }
}
