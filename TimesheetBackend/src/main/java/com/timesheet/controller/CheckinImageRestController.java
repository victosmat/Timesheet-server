package com.timesheet.controller;

import com.timesheet.dto.CheckinImage.RecognizeFaceDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/app/checkin_image")
@SecurityRequirement(name = "bearer-key")
public class CheckinImageRestController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://127.0.0.1:5000/";

    public CheckinImageRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/recognize_face")
    public void recognizeFace(@RequestBody RecognizeFaceDTO recognizeFaceDTO) {
        restTemplate.getForObject(baseUrl + "/recognize_face", RecognizeFaceDTO.class, recognizeFaceDTO);
    }
}
