package UI;

import Domain.Movie;
import Service.MovieService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowMoviesOrderedByBookingsController {
    public TableColumn tableColumnBookingsNumber;
    public TableView tableViewMovies;
    public TableColumn tableColumnIdMovie;
    public TableColumn tableColumnNameMovie;
    public MovieService movieService;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();


    public void setService(MovieService movieService) {
        this.movieService = movieService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<Movie> orderedMovies = movieService.getAll();
                orderedMovies.sort(Comparator.comparing(Movie::getBookings).reversed());

                movies.addAll(orderedMovies);

                tableViewMovies.setItems(movies);
            } catch (RuntimeException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window: Movies By Bookings.", e);
            }
        });
    }
}
