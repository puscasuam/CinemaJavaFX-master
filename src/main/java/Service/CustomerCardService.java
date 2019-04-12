package Service;

import Domain.CustomerCard;
import Repository.IRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomerCardService {
    private IRepository<CustomerCard> cardRepository;
    private Stack<UndoRedoOperation<CustomerCard>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<CustomerCard>> redoableOperations = new Stack<>();


    public CustomerCardService(IRepository<CustomerCard> cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * adds a customer card
     *
     * @param id               the id for the card
     * @param name             the customer's name
     * @param surname          the customer's surname
     * @param CNP              the customer's CNP
     * @param dateOfBirth      the customer's birthday
     * @param registrationDate the registration date of the card (today) or other day if lost
     * @param bonusPoints      the customer's bonus points
     * @throws CardServiceException if a card with that CNP already exists
     */
    public void insert(String id, String name, String surname, String CNP, LocalDate dateOfBirth, LocalDate registrationDate, int bonusPoints) {
        CustomerCard card = new CustomerCard(id, name, surname, CNP, dateOfBirth, registrationDate, bonusPoints);
        List<CustomerCard> all = new ArrayList<>(cardRepository.getAll());
        for (CustomerCard cardToTestCNP : all) {
            if (CNP.equals(cardToTestCNP.getCNP())) {
                throw new CardServiceException(String.format("The %s CNP already exists", CNP));
            }
        }
        cardRepository.insert(card);
        undoableOperations.add(new AddOperation<>(cardRepository, card));
        redoableOperations.clear();
    }

    /**
     * updates a customer card
     *
     * @param id               the id of the customer card we want to update
     * @param name             the customer's name
     * @param surname          the customer's surname
     * @param CNP              the customer's CNP
     * @param dateOfBirth      the customer's birthday
     * @param registrationDate the registration date of the card (today) or other day if lost
     * @param bonusPoints      the customer's bonus points
     * @throws RuntimeException if a card with that CNP already exists and isn't the current card CNP
     */
    public void update(String id, String name, String surname, String CNP, LocalDate dateOfBirth, LocalDate registrationDate, int bonusPoints) {
        CustomerCard actualCard = cardRepository.getById(id);
        CustomerCard updatedCard = new CustomerCard(id, name, surname, CNP, dateOfBirth, registrationDate, bonusPoints);
        List<CustomerCard> all = new ArrayList<>(cardRepository.getAll());
        for (CustomerCard cardToTestCNP : all) {
            if (CNP.equals(cardToTestCNP.getCNP()) && !CNP.equals(updatedCard.getCNP())) {
                throw new CardServiceException(String.format("The %s CNP already exists", CNP));
            }
        }
        cardRepository.update(updatedCard);
        undoableOperations.add(new UpdateOperation(cardRepository, updatedCard, actualCard));
        redoableOperations.clear();
    }

    /**
     * removes a customer card
     *
     * @param id the id of the card we want to remove
     */
    public void remove(String id) {
        CustomerCard card = cardRepository.getById(id);
        cardRepository.remove(id);
        undoableOperations.add(new DeleteOperation<>(cardRepository, card));
        redoableOperations.clear();
    }

    /**
     * list of all customer cards
     *
     * @return an ArrayList list with all cards
     */
    public List<CustomerCard> getAll() {
        return cardRepository.getAll();
    }

    public IRepository<CustomerCard> getCardRepository() {
        return cardRepository;
    }

    /**
     * searches a text in all customer cards
     *
     * @param text the text to find
     * @return a list with all cards where text was found
     */
    public List<CustomerCard> fullTextSearch(String text) {
        List<CustomerCard> found = new ArrayList<>();
        for (CustomerCard c : cardRepository.getAll()) {
            if ((c.getId().contains(text) || c.getName().contains(text) || c.getSurname().contains(text) ||
                    c.getCNP().contains(text) || c.getDateOfBirth().toString().contains(text) || c.getRegistrationDate().toString().contains(text) ||
                    Integer.toString(c.getBonusPoints()).contains(text)) && !found.contains(c)) {
                found.add(c);
            }
        }
        return found;
    }

    /**
     * increments bonus points for the cards which has birthday between two dates
     *
     * @param birthday1 the begining date
     * @param birthday2 the ending date
     * @param bonus     the amount of bonus points
     */
    public void luckyBonusPoints(LocalDate birthday1, LocalDate birthday2, int bonus) {
        List<CustomerCard> actualCards = new ArrayList<>();
        List<CustomerCard> updatedCards = new ArrayList<>();

        for (CustomerCard c : cardRepository.getAll()) {
//            if (c.getDateOfBirth().isAfter(birthday1) && c.getDateOfBirth().isBefore(birthday2)) {
            if (c.getDateOfBirth().getDayOfYear() > birthday1.getDayOfYear() && c.getDateOfBirth().getDayOfYear() < birthday2.getDayOfYear()) {
                actualCards.add(c);
                CustomerCard updatedCard = new CustomerCard(c.getId(), c.getName(), c.getSurname(), c.getCNP(), c.getDateOfBirth(), c.getRegistrationDate(), c.getBonusPoints());
                updatedCard.setBonusPoints(updatedCard.getBonusPoints() + bonus);
                cardRepository.update(updatedCard);
                updatedCards.add(updatedCard);
            }
        }
        undoableOperations.add(new UpdateOperations(cardRepository, updatedCards, actualCards));
        redoableOperations.clear();
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<CustomerCard> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<CustomerCard> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }


}
