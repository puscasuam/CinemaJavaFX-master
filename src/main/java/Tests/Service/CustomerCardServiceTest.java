package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.CustomerCardService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCardServiceTest {
    private IValidator<CustomerCard> validatorCard = new CustomerCardValidator();
    private IRepository<CustomerCard> customerCardRepository = new JsonFileRepository<>(validatorCard, "cards.json", CustomerCard.class);
    private CustomerCardService customerCardService = new CustomerCardService(customerCardRepository);



    @Test
    void insert() {
        customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);
        assertEquals(1,customerCardService.getAll().size());
        try {
            customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                    LocalDate.of(2013,12,11), 15);
            fail("Exception not thrown for duplicate card id!");
        } catch(RuntimeException rex) {
            assertTrue(true);
        }
    }

    @Test
    void update() {
        customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);
        customerCardService.update("1", "ccc", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);
        assertEquals("ccc",customerCardService.getAll().get(0).getName());
    }

    @Test
    void remove() {
        customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);
        customerCardService.remove("1");
        assertEquals(0,customerCardService.getAll().size());
    }

    @Test
    void fullTextSearch() {
        customerCardService.insert("1", "aaa", "bbb", "2222222222222", LocalDate.of(2012,11,22),
                LocalDate.of(2013,12,11), 15);
        assertEquals(1,customerCardService.fullTextSearch("aa").size());
        assertEquals(0,customerCardService.fullTextSearch("aahgs").size());
    }
}