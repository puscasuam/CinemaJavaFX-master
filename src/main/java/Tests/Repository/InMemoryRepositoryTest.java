package Tests.Repository;

import Domain.*;
import Repository.IRepository;
import Repository.JsonFileRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {
    private IValidator<Movie> validatorMovie = new MovieValidator();
    private IValidator<CustomerCard> validatorCard = new CustomerCardValidator();
    private IValidator<Booking> validatorTransaction = new BookingValidator();

    private IRepository<Movie> movieRepository = new JsonFileRepository<>(validatorMovie, "movies.json", Movie.class);
    private IRepository<CustomerCard> customerCardRepository = new JsonFileRepository<>(validatorCard, "cards.json", CustomerCard.class);
    private IRepository<Booking> bookingRepository = new JsonFileRepository<>(validatorTransaction, "bookings.json", Booking.class);

    private Movie movie1 = new Movie("1", "batman", 2000, 32, true);
    private Movie movie1Dupe = new Movie("1", "batman", 2000, 32, true);

    private CustomerCard card1 = new CustomerCard("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
            LocalDate.of(2013,12,11), 15);
    private CustomerCard card1update = new CustomerCard("1", "ccc", "bbb", "2222222222222", LocalDate.of(2012,11,22),
            LocalDate.of(2013,12,11), 15);

    private Booking booking1 = new Booking("1", "1", "1", LocalDate.of(2012,11,10), LocalTime.of(20,0));

    @org.junit.jupiter.api.Test
    void insert() {

        movieRepository.insert(movie1);
        List<Movie> allMovies = movieRepository.getAll();

        customerCardRepository.insert(card1);
        List<CustomerCard> allCards = customerCardRepository.getAll();

        bookingRepository.insert(booking1);
        List<Booking> allBookings = bookingRepository.getAll();

        assertEquals(1, allMovies.size());
        assertEquals(movie1, allMovies.get(0));

        assertEquals(1, allCards.size());
        assertEquals(card1, allCards.get(0));

        assertEquals(1, allBookings.size());
        assertEquals(booking1, allBookings.get(0));

        try {
            movieRepository.insert(movie1Dupe);
            fail("Exception not thrown for duplicate movie id!");
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }

    @org.junit.jupiter.api.Test
    void update() {
        customerCardRepository.insert(card1);
        customerCardRepository.update(card1update);
        List<CustomerCard> allCards = customerCardRepository.getAll();
        assertEquals(card1update, allCards.get(0));
        assertEquals(card1update, customerCardRepository.getById("1"));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        customerCardRepository.insert(card1);
        customerCardRepository.remove("1");
        assertEquals(0, customerCardRepository.getAll().size());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        movieRepository.insert(movie1);
        assertEquals(1,movieRepository.getAll().size());
    }
}