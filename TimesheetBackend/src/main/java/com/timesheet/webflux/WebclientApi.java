package com.timesheet.webflux;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("app/web_client_api")
public class WebclientApi {
    @GetMapping("weather_forecast")
    public ResponseEntity<String> getWeatherForecast(@RequestParam("location") String location, @RequestParam("days") String days) {

        WebClient client = WebClient.create();

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("q", location);
        urlVariables.put("days", days);

        String weatherMono = client.get()
                .uri("https://weatherapi-com.p.rapidapi.com/forecast.json", urlVariables)
                .headers(httpHeaders -> {
                    httpHeaders.add("X-RapidAPI-Key", "89979732a4msh09d8e5d2b0cca98p139815jsna8f2c02adad9");
                    httpHeaders.add("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");

                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(weatherMono);
    }

    @GetMapping("country_info")
    public ResponseEntity<String> getCountryInfo(@RequestParam("country") String country, HttpServletRequest request) {
        try {
            WebClient client = WebClient.create();
            String result = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("restcountries.com")
                            .pathSegment("v3.1", "name", "{name}")
                            .build(country))
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                            Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found")))
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                            Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error")))
                    .toEntity(String.class).toFuture().get().getBody();
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("country_info_rest_template")
    public ResponseEntity<?> getCountryInfoRestTempalte(@RequestParam("country") String country, HttpServletResponse response) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String resourceUrl = "https://restcountries.com/v3.1/name";

            return ResponseEntity.ok(restTemplate.getForEntity(resourceUrl + "/" + country, String.class));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(response.getStatus());
        }
    }

    @GetMapping("country_info_2")
    public Mono<ResponseEntity<String>> getCountryInfo2(@RequestParam("country") String country, HttpServletRequest request) {
        try {
            WebClient client = WebClient.create();
            Mono<String> countryInfo = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("restcountries.com")
                            .pathSegment("v3.1", "name", "{name}")
                            .build(country))
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                            Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found")))
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                            Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error")))
                    .bodyToMono(String.class);

                    return countryInfo.map(body -> {
                        System.out.println(body);
                        return ResponseEntity.ok(body);
                    })
                    .onErrorResume(error -> {
                        System.out.println(error.getMessage());
                        return Mono.just(ResponseEntity.ok(null));
                    });
        }
        catch (Exception e) {
            e.printStackTrace();
            return Mono.just(ResponseEntity.ok(null));
        }
    }
}
