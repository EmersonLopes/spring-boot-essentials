package com.example.springboot2.repository;

import com.example.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;


    @Test
    @DisplayName("Save persist anime when successful")
    void save_PersistAnime_WhenSuccessful() {

        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save update anime when successful")
    void save_UpdateAnime_WhenSuccessful() {

        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        animeSaved.setName("Overlord");

        Anime animeUpdated = this.animeRepository.save(animeSaved);
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Delete remove anime when successful")
    void delete_RemovesAnime_WhenSuccessful() {

        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeDeleted = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeDeleted).isEmpty();
    }

    @Test
    @DisplayName("Find by Name returns a list of anime when successful")
    void fynByName_ReturnsListOfAnime_WhenSuccessful() {

        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animeList = this.animeRepository.findByName(name);

        Assertions.assertThat(animeList).contains(animeSaved);
    }

    @Test
    @DisplayName("Find by Name returns a Empty list of anime when no anome is found")
    void fynByName_ReturnsEmptyList_WhenAnimeIsNotFound() {

        List<Anime> animeList = this.animeRepository.findByName("x10564");

        Assertions.assertThat(animeList).isEmpty();
    }

    private Anime createAnime() {
        Anime anime = new Anime();
        anime.setName("Hajime no ippo");
        return anime;

    }
}