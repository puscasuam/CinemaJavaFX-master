package Domain;

import java.util.Calendar;

public class CustomerCardValidator implements IValidator<CustomerCard> {
    /**
     * validates a card
     * @param card to validate
     * @throws CardValidatorException if there are validation errors
     */
    public void validate(CustomerCard card) {
        String errors = "";

        int id = Integer.parseInt(card.getId()), reversedId = 0, remainder, originalId;
        originalId = id;

        while(id !=0){
            remainder = id % 10;
            reversedId = reversedId * 10 + remainder;
            id /= 10;
        }

        if(originalId != reversedId){
            throw new RuntimeException("This id is not a palindrome number");
        }


        if (card.getCNP().length() != 13) {
            errors += "The CNP must have 13 characters! \n";
        }

        if (card.getDateOfBirth().getYear() < 1900 || card.getDateOfBirth().getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors += "The year of birth must be less than " + Calendar.getInstance().get(Calendar.YEAR) +
                    " and greater than 1900\n";
        }

        if (card.getRegistrationDate().getYear() < 1900 || card.getRegistrationDate().getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors += "The year of registration must be less than " + Calendar.getInstance().get(Calendar.YEAR) +
                    " and greater than 1900\n";
        }

        if (card.getBonusPoints() < 0) {
            errors += "The bonus points must be >= 0 \n";
        }

        if (!errors.isEmpty()) {
            throw new CardValidatorException("\n" + errors);
        }
    }
}