package Service;

import Domain.Movie;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private IRepository<Movie> movieRepository;

    public MovieService(IRepository<Movie> movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * adds a movie
     *
     * @param id        the movie's id
     * @param name      the movie's name
     * @param year      the movie's year
     * @param price     the price of a ticker for this movie
     * @param onScreens boolean for setting if movie is on screens or not
     */
    public void insert(String id, String name, int year, double price, boolean onScreens) {
        Movie movie = new Movie(id, name, year, price, onScreens);
        movieRepository.insert(movie);
    }

    /**
     * updates a movie
     *
     * @param id        the movie's id we want to update
     * @param name      the movie's name
     * @param year      the movie's year
     * @param price     the price of a ticker for this movie
     * @param onScreens boolean for setting if movie is on screens or not
     */
    public void update(String id, String name, int year, double price, boolean onScreens) {
        Movie movie = new Movie(id, name, year, price, onScreens);
        movieRepository.update(movie);
    }

    /**
     * removes a movie
     *
     * @param id the id of the movie that we want to remove
     */
    public void remove(String id) {
        movieRepository.remove(id);
    }

    /**
     * list of all movies
     *
     * @return a list with all movies
     */
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    /**
     * searches a text in all movies
     *
     * @param text the text to find
     * @return a list with all movies where text was found
     */
    public List<Movie> fullTextSearch(String text) {
        List<Movie> found = new ArrayList<>();
        for (Movie m : movieRepository.getAll()) {
            if ((m.getId().contains(text) || m.getName().contains(text) ||
                    Integer.toString(m.getYear()).contains(text) || Double.toString(m.getPrice()).contains(text) ||
                    Boolean.toString(m.isOnScreens()).contains(text)) && !found.contains(m)) {
                found.add(m);
            }
        }
        return found;
    }


}
