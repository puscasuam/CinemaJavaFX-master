package Service;

import Domain.Booking;
import Domain.CustomerCard;
import Domain.Movie;
import Repository.IRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private IRepository<Booking> bookingRepository;
    private IRepository<Movie> movieRepository;
    private IRepository<CustomerCard> cardRepository;

    public BookingService(IRepository<Booking> bookingRepository, IRepository<Movie> movieRepository, IRepository<CustomerCard> cardRepository) {
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
        this.cardRepository = cardRepository;
    }


    /**
     * adds a booking if movie is on screens
     * adds 10% bonus points from movie price to customer card if a card exists
     *
     * @param id      the booking id
     * @param idMovie the movie id for booking
     * @param idCard  the customer's card id
     * @param date    the date for movie booking
     * @param time    the time for movie booking
     * @throws BookingServiceException if movie isn't on screens or movie id doesn't exists
     */
    public void insert(String id, String idMovie, String idCard, LocalDate date, LocalTime time) {
        Movie movieSold = movieRepository.getById(idMovie);
        if (movieSold == null) {
            throw new BookingServiceException("There is no movie with the given id!");
        }

        if (!movieSold.isOnScreens()) {
            throw new BookingServiceException("The movie isn't on the screen");
        }

        Booking booking = new Booking(id, idMovie, idCard, date, time);
        bookingRepository.insert(booking);
        movieRepository.getById(idMovie).setBookings(movieRepository.getById(idMovie).getBookings() + 1);

        CustomerCard card = cardRepository.getById(idCard);

        if (card != null) {
            card.setBonusPoints((int) (card.getBonusPoints() + (movieSold.getPrice() / 10)));
        }
    }


    /**
     * updates a booking
     *
     * @param id      the id of booking we want to update
     * @param idMovie the id of the movie for booking update
     * @param idCard  the id of customer card booking update
     * @param date    the date booking update
     * @param time    the time booking update
     */
    public void update(String id, String idMovie, String idCard, LocalDate date, LocalTime time) {
        Booking booking = new Booking(id, idMovie, idCard, date, time);
        bookingRepository.update(booking);
    }

    /**
     * removes a booking by id
     *
     * @param id the id of the booking we want to remove
     */
    public void remove(String id) {
        bookingRepository.remove(id);
    }

    /**
     * list of all bookings
     *
     * @return an ArrayList list with all bookings
     */
    public List<Booking> getAll() {
        return bookingRepository.getAll();
    }

    /**
     * searches a text in all bookings
     *
     * @param text the text to find
     * @return a list with all bookings where text was found
     */
    public List<Booking> fullTextSearch(String text) {
        List<Booking> found = new ArrayList<>();
        for (Booking b : bookingRepository.getAll()) { //id, idMovie, idCard, date, time
            if ((b.getId().contains(text) || b.getIdMovie().contains(text) || b.getIdCard().contains(text) ||
                    b.getDate().toString().contains(text) || b.getTime().toString().contains(text)) && !found.contains(b)) {
                found.add(b);
            }
        }
        return found;
    }

    /**
     * searches all bokings in a specified time period
     *
     * @param begin the begining time
     * @param end   the ending time
     * @return a list with all bookings
     */
    public List<Booking> bookingsByPeriod(LocalTime begin, LocalTime end) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking b : bookingRepository.getAll()) {
            if (b.getTime().isAfter(begin) && b.getTime().isBefore(end)) {
                bookings.add(b);
            }
        }
        return bookings;
    }

    /**
     * removes bookings between two dates
     * @param begin the begining date
     * @param end the ending date
     */
    public void removeBookingsByPeriod(LocalDate begin, LocalDate end) {
        for (Booking b : bookingRepository.getAll()) {
            if(b.getDate().isAfter(begin) && b.getDate().isBefore(end)){
                bookingRepository.remove(b.getId());
            }
        }
    }
}
