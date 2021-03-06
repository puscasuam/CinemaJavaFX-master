package Domain;

import java.util.Calendar;

public class BookingValidator implements IValidator<Booking> {
    /**
     * validates a booking
     *
     * @param booking to validate
     * @throws BookingValidatorException if there are validation errors
     */

    public void validate(Booking booking) {
        String errors = "";

        int id = Integer.parseInt(booking.getId()), reversedId = 0, remainder, originalId;
        originalId = id;

        while(id !=0){
            remainder = id % 10;
            reversedId = reversedId * 10 + remainder;
            id /= 10;
        }

        if(originalId != reversedId){
            throw new RuntimeException("This id is not a palindrome number");
        }


        if (booking.getDate().getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors += "The year of booking must be less than " + Calendar.getInstance().get(Calendar.YEAR);
        }

        if (!errors.isEmpty()) {
            throw new BookingValidatorException("\n" + errors);
        }
    }
}