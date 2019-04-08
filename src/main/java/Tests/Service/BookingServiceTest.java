package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.BookingService;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private IValidator<Movie> validatorMovie = new MovieValidator();
    private  IValidator<CustomerCard> validatorCard = new CustomerCardValidator();
    private IValidator<Booking> validatorTransaction = new BookingValidator();

    private IRepository<Movie> movieRepository = new JsonFileRepository<>(validatorMovie, "movies.json", Movie.class);
    private IRepository<CustomerCard> customerCardRepository = new JsonFileRepository<>(validatorCard, "cards.json", CustomerCard.class);
    private IRepository<Booking> bookingRepository = new JsonFileRepository<>(validatorTransaction, "cards.json", CustomerCard.class);


    private BookingService bookingService = new BookingService(bookingRepository, movieRepository, customerCardRepository);

    private Movie movie1 = new Movie("1", "batman", 2000, 32, true);
    private CustomerCard card1 = new CustomerCard("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
            LocalDate.of(2013,12,11), 15);

    @Test
    void insert() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));

        assertEquals(1, bookingService.getAll().size());
    }

    @Test
    void update() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));
        bookingService.update("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(12,0));


        assertEquals(LocalTime.of(12,0), bookingService.getAll().get(0).getTime());
    }

    @Test
    void remove() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));
        bookingService.remove("1");

        assertEquals(0,bookingService.getAll().size());
    }

    @Test
    void getAll() {

    }

    @Test
    void fullTextSearch() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));
        String text = "2012";

        bookingService.fullTextSearch(text);

        assertEquals(1,bookingService.fullTextSearch(text).size());
    }
}