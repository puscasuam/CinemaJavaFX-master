package Tests.Domain;

import Domain.CustomerCard;
import Domain.CustomerCardValidator;
import Domain.IValidator;
import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.CustomerCardService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCardTest {
    private IValidator<CustomerCard> validatorCard = new CustomerCardValidator();
    private IRepository<CustomerCard> customerCardRepository = new JsonFileRepository<>(validatorCard, "cards.json", CustomerCard.class);
    private CustomerCardService customerCardService = new CustomerCardService(customerCardRepository);

    @Test
    void setNameSurnameCNPDateOfBirthRegistrationDateBonusPoints() {
        customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);

        customerCardService.getAll().get(0).setName("Name");
        assertEquals("Name",customerCardService.getAll().get(0).getName());

        customerCardService.getAll().get(0).setSurname("Surname");
        assertEquals("Surname",customerCardService.getAll().get(0).getSurname());

        customerCardService.getAll().get(0).setCNP("0000000000000");
        assertEquals("0000000000000",customerCardService.getAll().get(0).getCNP());

        customerCardService.getAll().get(0).setDateOfBirth(LocalDate.of(1990,4,10));
        assertEquals(LocalDate.of(1990,4,10),customerCardService.getAll().get(0).getDateOfBirth());

        customerCardService.getAll().get(0).setRegistrationDate(LocalDate.of(2019,2,21));
        assertEquals(LocalDate.of(2019,2,21),customerCardService.getAll().get(0).getRegistrationDate());

        customerCardService.getAll().get(0).setBonusPoints(100);
        assertEquals(100,customerCardService.getAll().get(0).getBonusPoints());
    }
}