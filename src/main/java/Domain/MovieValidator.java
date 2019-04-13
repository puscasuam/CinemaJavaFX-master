package Domain;

import java.util.Calendar;

public class MovieValidator implements IValidator<Movie> {
    /**
     * validates a movie
     * @param movie to validate
     * @throws MovieValidatorException if there are validation errors
     */
    public void validate(Movie movie) {

        String errors = "";

        int id = Integer.parseInt(movie.getId()), reversedId = 0, remainder, originalId;
        originalId = id;

        while(id !=0){
            remainder = id % 10;
            reversedId = reversedId * 10 + remainder;
            id /= 10;
        }

        if(originalId != reversedId){
            throw new RuntimeException("This id is not a palindrome number");
        }



        if (movie.getPrice() <= 0) {
            errors += "The price must be greater than 0!\n";
        }

        if (movie.getYear() < 0 || movie.getYear() > Calendar.getInstance().get(Calendar.YEAR)+1) {
            errors += "The movie's year must be valid! \n";
        }

        if (!errors.isEmpty()) {
            throw new MovieValidatorException(errors);
        }
    }
}