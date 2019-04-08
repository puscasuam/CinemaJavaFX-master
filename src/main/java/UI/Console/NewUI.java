package UI.Console;

import Domain.CustomerCard;
import Domain.Movie;
import Domain.Booking;
import Service.CustomerCardService;
import Service.MovieService;
import Service.BookingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class NewUI {
    private MovieService movieService;
    private CustomerCardService cardService;
    private BookingService bookingService;

    private Scanner scanner;

    public NewUI(MovieService movieService, CustomerCardService cardService, BookingService bookingService) {
        this.movieService = movieService;
        this.cardService = cardService;
        this.bookingService = bookingService;

        this.scanner = new Scanner(System.in);
    }

    public void runMenu() {
        while (true) {
            System.out.println("Enter your option (x for exit)");
            String option = scanner.nextLine();
            String[] s = option.split(",");

            switch (s[0].toLowerCase()) {
                case "addmovie":
                    try {
                        addMovie(s[1], s[2], Integer.parseInt(s[3]), Double.parseDouble(s[4]), Boolean.parseBoolean(s[5]));
                    } catch (ConsoleException cex) {
                        System.out.println("Add movie error: " + cex.getMessage());
                    }
                    break;
                case "updatemovie":
                    try {
                        updateMovie(s[1], s[2], Integer.parseInt(s[3]), Double.parseDouble(s[4]), Boolean.parseBoolean(s[5]));
                    } catch (ConsoleException cex) {
                        System.out.println("Update movie error: " + cex.getMessage());
                    }
                    break;
                case "removemovie":
                    try {
                        removeMovie(s[1]);
                    } catch (ConsoleException cex) {
                        System.out.println("Remove movie error: " + cex.getMessage());
                    }
                    break;
                case "showallmovies":
                    showAllMovies();
                    break;
                case "addcard":
                    try {
                        LocalDate dateOfBirth = ScannerForMovieAndCardsInformationPlusDateConverter.stringToDateConverter(s[5]);
                        LocalDate registrationDate = ScannerForMovieAndCardsInformationPlusDateConverter.stringToDateConverter(s[6]);
                        addCard(s[1], s[2], s[3], s[4], dateOfBirth, registrationDate, Integer.parseInt(s[7]));
                    } catch (ConsoleException cex) {
                        System.out.println("Add card error: " + cex.getMessage());
                    }
                    break;
                case "updatecard":
                    try {
                        LocalDate dateOfBirth = ScannerForMovieAndCardsInformationPlusDateConverter.stringToDateConverter(s[5]);
                        LocalDate registrationDate = ScannerForMovieAndCardsInformationPlusDateConverter.stringToDateConverter(s[6]);
                        updateCard(s[1], s[2], s[3], s[4], dateOfBirth, registrationDate, Integer.parseInt(s[7]));
                    } catch (ConsoleException cex) {
                        System.out.println("Update card error: " + cex.getMessage());
                    }
                    break;
                case "removecard":
                    try {
                        removeCard(s[1]);
                    } catch (ConsoleException cex) {
                        System.out.println("Remove card error " + cex.getMessage());
                    }
                    break;
                case "showallcards":
                    showAllCards();
                    break;
                case "addbooking":
                    try {
                        LocalDate bookingDate = ScannerForMovieAndCardsInformationPlusDateConverter.stringToDateConverter(s[4]);
                        LocalTime bookingTime = LocalTime.of(Integer.parseInt(s[5].substring(0, 2)), Integer.parseInt(s[5].substring(3, 5)));
                        book(s[1], s[2], s[3], bookingDate, bookingTime);
                    } catch (ConsoleException cex) {
                        System.out.println("Add booking error: " + cex.getMessage());
                    }
                    break;
                case "updatebooking":
                    try {
                        LocalDate updateBookingDate = ScannerForMovieAndCardsInformationPlusDateConverter.stringToDateConverter(s[4]);
                        LocalTime updateBookingTime = LocalTime.of(Integer.parseInt(s[5].substring(0, 2)), Integer.parseInt(s[5].substring(3, 5)));
                        updateBooking(s[1], s[2], s[3], updateBookingDate, updateBookingTime);
                    } catch (ConsoleException cex) {
                        System.out.println("Update booking error: " + cex.getMessage());
                    }
                    break;
                case "removebooking":
                    try {
                        removeBooking(s[1]);
                    } catch (ConsoleException cex) {
                        System.out.println("Remove booking error " + cex.getMessage());
                    }
                    break;
                case "showallbookings":
                    showAllBookings();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void addMovie(String id, String name, int year, double price, boolean onScreens) {
        try {
            this.movieService.insert(id, name, year, price, onScreens);
            System.out.println("\nMovie successfully added\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an adding error " + cex.getMessage());
        }
    }

    private void updateMovie(String id, String name, int year, double price, boolean onScreens) {
        try {
            this.movieService.update(id, name, year, price, onScreens);
            System.out.println("\nMovie successfully updated\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an updating error " + cex.getMessage());
        }
    }

    private void removeMovie(String id) {
        try {
            movieService.remove(id);
            System.out.println("Movie successfully removed\n");
        } catch (ConsoleException cex) {
            System.out.println("We have a removing error " + cex.getMessage());
        }
    }

    private void showAllMovies() {
        for (Movie movie : movieService.getAll()) {
            System.out.println(movie);
        }
    }

    private void addCard(String id, String name, String surname, String CNP, LocalDate dateOfBirth, LocalDate registrationDate, int bonusPoints) {
        try {
            cardService.insert(id, name, surname, CNP, dateOfBirth, registrationDate, bonusPoints);
            System.out.println("Card successfully added!\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an card adding error " + cex.getMessage());
        }
    }

    private void updateCard(String id, String name, String surname, String CNP, LocalDate dateOfBirth, LocalDate registrationDate, int bonusPoints) {
        try {
            cardService.update(id, name, surname, CNP, dateOfBirth, registrationDate, bonusPoints);
            System.out.println("Card successfully updated!\n");
        } catch (ConsoleException cex) {
            System.out.println("We have an card updating error " + cex.getMessage());
        }
    }

    private void removeCard(String id) {
        try {
            cardService.remove(id);
            System.out.println("Card successfully removed");
        } catch (ConsoleException cex) {
            System.out.println("We have an removing error " + cex.getMessage());
        }
    }

    private void showAllCards() {
        for (CustomerCard card : cardService.getAll()) {
            System.out.println(card);
        }
    }

    private void book(String id, String idMovie, String idCard, LocalDate date, LocalTime time) {
        try {
            bookingService.insert(id, idMovie, idCard, date, time);
            if (cardService.getCardRepository().getById(idCard) != null) {
                System.out.println("\nBonus points :" + cardService.getCardRepository().getById(idCard).getBonusPoints());
            }
            System.out.println("Booking created!\n");
        } catch (ConsoleException cex) {
            System.out.println("Adding booking failed " + cex.getMessage());
        }
    }

    private void updateBooking(String id, String idMovie, String idCard, LocalDate date, LocalTime time) {
        try {
            bookingService.update(id, idMovie, idCard, date, time);
            System.out.println("Booking updated!\n");
        } catch (ConsoleException cex) {
            System.out.println("Updating booking failed " + cex.getMessage());
        }
    }

    private void removeBooking(String id) {
        try {
            bookingService.remove(id);
            System.out.println("Booking removed");
        } catch (ConsoleException cex) {
            System.out.println("Remove error " + cex.getMessage());
        }
    }

    private void showAllBookings() {
        for (Booking booking : bookingService.getAll()) {
            System.out.println(booking);
        }
    }
}