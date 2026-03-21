package com.example.project_1.controller;

import com.example.project_1.service.BookingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {
    private BookingService bookingService;
}
