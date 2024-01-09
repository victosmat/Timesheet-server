package com.timesheet.controller;

import com.timesheet.dto.checkinImage.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/app/checkin_image")
@SecurityRequirement(name = "bearer-key")
public class CheckinImageRestController {

    private final WebClient.Builder webClient;

    public CheckinImageRestController(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    @PostMapping("/recognize_face")
    public Object recognizeFace(@RequestBody RecognizeFaceDTO recognizeFaceDTO) {
        return webClient.build()
                .post()
                .uri("/recognize_face")
                .bodyValue(recognizeFaceDTO)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Scheduled(cron = "0 5 15 * * *")
    public void trainModelImageClassification() {
        webClient.build()
                .get()
                .uri("/train_model_image_classification")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @PostMapping("/delete_image")
    public Object deleteImage(@RequestBody DeleteImageDTO deleteImageDTO) {
        return webClient.build()
                .post()
                .uri("/delete_image")
                .bodyValue(deleteImageDTO)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @PostMapping("/get_images_base64")
    public Object getImagesBase64(@RequestBody GetImagesDTO getImagesDTO) {
        return webClient.build()
                .post()
                .uri("/get_images_base64")
                .bodyValue(getImagesDTO)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @PostMapping("/save_image")
    public Object saveImage(@RequestBody ImageDTO imagesDTO) {
        return webClient.build()
                .post()
                .uri("/save_image")
                .bodyValue(imagesDTO)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @PostMapping("/save_list_images")
    public Object saveListImages(@RequestBody SaveImagesDTO saveImagesDTO) {
        return webClient.build()
                .post()
                .uri("/save_list_images")
                .bodyValue(saveImagesDTO)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @PostMapping("/get_avatar")
    public Object getAvatar(@RequestBody ImageDTO imageDTO){
        return webClient.build()
                .post()
                .uri("/get_avatar")
                .bodyValue(imageDTO)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
