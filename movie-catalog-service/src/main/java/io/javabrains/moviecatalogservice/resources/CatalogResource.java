package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public UserRating getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://10.0.170.52:8080/ratingsdata/user/" + userId, UserRating.class);
        return userRating;
    }
    @RequestMapping("/external/{userId}")
    public UserRating getCatalog1(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://52.172.39.134/ratingsdata/user/" + userId, UserRating.class);
        return userRating;
    }
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/