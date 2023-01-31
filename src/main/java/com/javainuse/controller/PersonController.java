package com.javainuse.controller;

import com.javainuse.dto.PersonDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class PersonController {

    final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/person/{profileId}")
    public ResponseEntity<PersonDataResponse> test(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String auth, @PathVariable("profileId") String profileId) {
        logger.info("auth: {}", auth);
        return ResponseEntity.ok(PersonDataResponse.of("Person-" + profileId));
    }
}
