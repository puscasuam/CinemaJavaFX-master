package Tests.Domain;

import Domain.*;
import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.MovieService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private IValidator<Movie> validatorMovie = new MovieValidator();
    private IRepository<Movie> movieRepository = new JsonFileRepository<>(validatorMovie, "movies.json", Movie.class);
    private MovieService movieService = new MovieService(movieRepository);


    @Test
    void setNameYearPriceOnScreen() {
        movieService.insert("1","Batman",2000,32,true);

        movieService.getAll().get(0).setName("Superman");
        assertEquals("Superman",movieService.getAll().get(0).getName());

        movieService.getAll().get(0).setYear(2008);
        assertEquals(2008,movieService.getAll().get(0).getYear());

        movieService.getAll().get(0).setPrice(40);
        assertEquals(40,movieService.getAll().get(0).getPrice());


        movieService.getAll().get(0).setOnScreens(false);
        assertFalse(movieService.getAll().get(0).isOnScreens());
    }


}