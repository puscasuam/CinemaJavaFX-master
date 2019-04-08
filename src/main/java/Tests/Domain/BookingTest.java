package Tests.Domain;

import Domain.*;
import Repository.IRepository;

import Repository.JsonFileRepository;
import Service.BookingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private IValidator<Movie> validatorMovie = new MovieValidator();
    private IValidator<CustomerCard> validatorCard = new CustomerCardValidator();
    private IValidator<Booking> validatorTransaction = new BookingValidator();

    private IRepository<Movie> movieRepository = new JsonFileRepository<>(validatorMovie, "movies.json", Movie.class);
    private IRepository<CustomerCard> customerCardRepository = new JsonFileRepository<>(validatorCard, "cards.json", CustomerCard.class);
    private IRepository<Booking> bookingRepository = new JsonFileRepository<>(validatorTransaction, "bookings.json", Booking.class);


    private BookingService bookingService = new BookingService(bookingRepository, movieRepository, customerCardRepository);

    private Movie movie1 = new Movie("1", "batman", 2000, 32, true);
    private CustomerCard card1 = new CustomerCard("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
            LocalDate.of(2013,12,11), 15);


    @Test
    void getIdMovie() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("5", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));

        assertEquals("1", bookingService.getAll().get(0).getIdMovie());
    }

    @Test
    void setIdMovieIdCardDateTimeEqualsAndToString() {
        movieRepository.insert(movie1);
        customerCardRepository.insert(card1);
        bookingService.insert("5", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));

        bookingService.getAll().get(0).setIdMovie("6");
        assertEquals("6", bookingService.getAll().get(0).getIdMovie());

        bookingService.getAll().get(0).setIdCard("3");
        assertEquals("3", bookingService.getAll().get(0).getIdCard());

        bookingService.getAll().get(0).setDate(LocalDate.of(2000,11,20));
        assertEquals(LocalDate.of(2000,11,20), bookingService.getAll().get(0).getDate());

        bookingService.getAll().get(0).setTime(LocalTime.of(13,37));
        assertEquals(LocalTime.of(13,37), bookingService.getAll().get(0).getTime());

        assertEquals(bookingService.getAll().get(0), new Booking("5", "1", "1", LocalDate.of(2012, 11, 10), LocalTime.of(20, 0)));

        assertTrue(bookingService.getAll().get(0).toString().contains("5"));

    }
}