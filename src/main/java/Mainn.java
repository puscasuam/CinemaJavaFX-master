//import Domain.*;
//
//import Repository.IRepository;
//import Repository.InMemoryRepository;
//import Service.CustomerCardService;
//import Service.MovieService;
//import Service.BookingService;
//import UI.Console.Console;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
////import UI.Console.NewUI;
//
//public class Mainn {
//
//    public static void main(String[] args) {
//
//        IValidator<Movie> validatorMovie = new MovieValidator();
//        IValidator<CustomerCard> validatorCard = new CustomerCardValidator();
//        IValidator<Booking> validatorTransaction = new BookingValidator();
//
//        IRepository<Movie> movieRepository = new InMemoryRepository<>(validatorMovie);
//        IRepository<CustomerCard> customerCardRepository = new InMemoryRepository<>(validatorCard);
//        IRepository<Booking> bookingRepository = new InMemoryRepository<>(validatorTransaction);
//
//        MovieService movieService = new MovieService(movieRepository);
//        CustomerCardService customerCardService = new CustomerCardService(customerCardRepository);
//        BookingService bookingService = new BookingService(bookingRepository,movieRepository,customerCardRepository);
//
//
//        movieService.insert("1", "Batman", 2000, 200, true);
//        movieService.insert("2", "Superman", 2001, 400, true);
//        movieService.insert("3", "Robin Hood", 2002, 700, true);
//
//        customerCardService.insert("1","Marcel","Ionel","7374859605948", LocalDate.of(2000,3,4),LocalDate.of(2003,5,4),0);
//        customerCardService.insert("2","Vasilica","Ionel","7374819605948", LocalDate.of(2001,3,4),LocalDate.of(2003,5,4),0);
//        customerCardService.insert("3","Ionel","Ionel","7374869605948", LocalDate.of(2002,3,4),LocalDate.of(2003,5,4),0);
//        customerCardService.insert("4","Ionel","Ionel","7374869605943", LocalDate.of(2003,3,4),LocalDate.of(2003,5,4),0);
//        customerCardService.insert("5","Ionel","Ionel","7374869605944", LocalDate.of(2004,3,4),LocalDate.of(2003,5,4),0);
//
//        bookingService.insert("1","1","1",LocalDate.of(2001,7,3), LocalTime.of(11,23));
//        bookingService.insert("2","2","2",LocalDate.of(2002,7,3), LocalTime.of(12,23));
//        bookingService.insert("3","1","3",LocalDate.of(2003,7,3), LocalTime.of(13,23));
//        bookingService.insert("4","3","2",LocalDate.of(2004,7,3), LocalTime.of(14,23));
//        bookingService.insert("5","3","3",LocalDate.of(2005,7,3), LocalTime.of(15,23));
//        bookingService.insert("6","3","3",LocalDate.of(2006,7,3), LocalTime.of(15,23));
//        bookingService.insert("7","3","3",LocalDate.of(2007,7,3), LocalTime.of(15,23));
//
//        Console console = new Console(movieService, customerCardService, bookingService);
//        console.runMenu();
//
////        NewUI console = new NewUI( movieService,customerCardService,bookingService);
////        console.runMenu();
//
//    }
//}
