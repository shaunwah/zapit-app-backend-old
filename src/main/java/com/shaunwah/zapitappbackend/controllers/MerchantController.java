package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;
}
