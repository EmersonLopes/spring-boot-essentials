package com.example.springboot2.client;

import com.example.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        /*ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/2", Anime.class);
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));


        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {
                });
        log.info(exchange);

        Anime kingdom = new Anime();
        kingdom.setName("Kingdom");

        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
        log.info("Anime saved {}",kingdomSaved);*/


        Anime samurai = new Anime();
        samurai.setName("Samurai Champploo");

        ResponseEntity<Anime> samuraiSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(samurai, createJsonHeader()),
                Anime.class);

        log.info("Samura saved {}",samuraiSaved);

//        PUT
        Anime samuraToUpdate = samuraiSaved.getBody();
        samuraToUpdate.setName("Samurai Champploo 2");

        ResponseEntity<Void> samuraiUpdated = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(samuraToUpdate, createJsonHeader()),
                Void.class);

        log.info(samuraiUpdated);


//        DELETE
        ResponseEntity<Void> samuraiDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                samuraToUpdate.getId());

        log.info(samuraiDeleted);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
