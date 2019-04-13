package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private IValidator<Movie> validatorMovie = new MovieValidator();
    private IRepository<Movie> movieRepository = new JsonFileRepository<>(validatorMovie, "movies.json", Movie.class);
    private MovieService movieService = new MovieService(movieRepository);

    @Test
    void insert() {
        movieService.insert("1", "batman", 2000, 32, true);
        assertEquals(1, movieService.getAll().size());
    }

    @Test
    void update() {
        movieService.insert("1", "batman", 2000, 32, true);
        movieService.update("1", "superman", 2000, 32, true);
        assertEquals("superman", movieService.getAll().get(0).getName());
    }

    @Test
    void remove() {



    }

    @Test
    void getAll() {
    }

    @Test
    void fullTextSearch() {
        movieService.insert("1", "batman", 2000, 32, true);
        assertEquals(1, movieService.fullTextSearch("batman").size());
        assertEquals(0, movieService.fullTextSearch("aahgs").size());

    }
}