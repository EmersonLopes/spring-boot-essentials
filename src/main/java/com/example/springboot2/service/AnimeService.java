package com.example.springboot2.service;

import com.example.springboot2.domain.Anime;
import com.example.springboot2.exception.BadRequestException;
import com.example.springboot2.repository.AnimeRepository;
import com.example.springboot2.request.AnimePostRequestBody;
import com.example.springboot2.request.AnimePuttRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public Page<Anime> list(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = new Anime();
        anime.setName(animePostRequestBody.getName());

        return animeRepository.save(anime);
    }

    public void delete(long id) {
        animeRepository.delete(findById(id));
    }

    public void replace(AnimePuttRequestBody animePuttRequestBody) {

        Anime anime = findById(animePuttRequestBody.getId());
        anime.setName(animePuttRequestBody.getName());

        animeRepository.save(anime);
    }
}
