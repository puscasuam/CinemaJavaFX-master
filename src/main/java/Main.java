import Domain.*;

import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.CustomerCardService;
import Service.MovieService;
import Service.BookingService;

import UI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();

        IValidator<Movie> movieValidator = new MovieValidator();
        IValidator<CustomerCard> cardValidator = new CustomerCardValidator();
        IValidator<Booking> bookingValidator = new BookingValidator();

        IRepository<Movie> moviesRepository = new JsonFileRepository<>(movieValidator, "movies.json", Movie.class);
        IRepository<CustomerCard> cardsRepository = new JsonFileRepository<>(cardValidator, "cards.json", CustomerCard.class);
        IRepository<Booking> bookingsRepository = new JsonFileRepository<>(bookingValidator, "bookings.json", Booking.class);

        MovieService movieService = new MovieService(moviesRepository);
//        movieService.insert("1", "Batman", 2000, 20, true);
//        movieService.insert("2", "Superman", 2001, 40, true);
//        movieService.insert("3", "Robin Hood", 2003, 70, true);
//        movieService.insert("4", "Avengers", 2004, 45, true);
//        movieService.insert("5", "Revenant", 2005, 32, true);
//        movieService.insert("6", "Horrible bosses", 2006, 25, true);
//        movieService.insert("7", "Dirty Grandpa", 2007, 15, true);
//        movieService.insert("8", "X-MEN", 2008, 18, true);
//        movieService.insert("9", "Ant Man", 2009, 37, true);
//        movieService.insert("10", "Divergent", 2010, 48, true);
//        movieService.insert("11", "Insurgent", 2011, 50, true);
//        movieService.insert("12", "Game of Thrones s01e01", 2012, 43, true);
//        movieService.insert("13", "Titanic", 2013, 67, true);
//        movieService.insert("14", "The Wolf of Wall Street ", 2014, 43, true);
//        movieService.insert("15", "Django", 2015, 29, true);
//        movieService.insert("16", "Hatefull 8", 2016, 53, true);


        CustomerCardService cardService = new CustomerCardService(cardsRepository);
//        cardService.insert("1","Name","Surname","2111111111711", LocalDate.of(1990,3,4),LocalDate.of(2016,2,3),0);
//        cardService.insert("2","Name2","Surname2","4111114119111", LocalDate.of(1991,5,5),LocalDate.of(2016,6,4),0);
//        cardService.insert("3","Name3","Surname3","6111311145111", LocalDate.of(1993,4,17),LocalDate.of(2017,3,12),0);
//        cardService.insert("4","Name4","Surname4","7111315145111", LocalDate.of(1995,6,27),LocalDate.of(2018,4,23),0);
//        cardService.insert("5","Name5","Surname5","4111311145111", LocalDate.of(1994,7,12),LocalDate.of(2018,7,25),0);
//        cardService.insert("6","Name6","Surname6","2111311145111", LocalDate.of(1989,8,8),LocalDate.of(2018,12,6),0);
//        cardService.insert("7","Name7","Surname7","2311311145111", LocalDate.of(1988,12,13),LocalDate.of(2018,11,17),0);

        BookingService bookingService = new BookingService(bookingsRepository, moviesRepository, cardsRepository);
//        bookingService.insert("1","3","1",LocalDate.of(2016,3,4), LocalTime.of(12,10));
//        bookingService.insert("2","3","2",LocalDate.of(2016,1,6), LocalTime.of(14,15));
//        bookingService.insert("3","3","1",LocalDate.of(2016,5,3), LocalTime.of(22,20));
//        bookingService.insert("4","3","3",LocalDate.of(2016,11,23), LocalTime.of(14,30));
//        bookingService.insert("5","6","4",LocalDate.of(2017,12,12), LocalTime.of(13,30));
//        bookingService.insert("6","7","3",LocalDate.of(2017,7,16), LocalTime.of(18,20));
//        bookingService.insert("7","11","3",LocalDate.of(2017,8,6), LocalTime.of(20,40));
//        bookingService.insert("8","16","3",LocalDate.of(2017,3,8), LocalTime.of(15,0));
//        bookingService.insert("9","11","5",LocalDate.of(2018,2,3), LocalTime.of(10,10));
//        bookingService.insert("10","1","3",LocalDate.of(2018,5,29), LocalTime.of(19,30));
//        bookingService.insert("11","12","6",LocalDate.of(2018,6,20), LocalTime.of(21,15));
//        bookingService.insert("12","12","7",LocalDate.of(2018,9,19), LocalTime.of(16,50));


        mainController.setServices(movieService, cardService, bookingService);

        primaryStage.setTitle("Cinema manager");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
