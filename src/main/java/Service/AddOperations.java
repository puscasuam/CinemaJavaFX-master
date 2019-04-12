package Service;

import Domain.Entity;
import Repository.IRepository;

import java.util.List;

public class AddOperations<T extends Entity> extends UndoRedoOperation {
    private List<T> addedEntities;


    public AddOperations(IRepository repository, List<T> addedEntities) {
        super(repository);
        this.addedEntities = addedEntities;
    }


    @Override
    public void doUndo() {
        for (T addedEntity : addedEntities)
            repository.remove(addedEntity.getId());
    }


    @Override
    public void doRedo() {
        for (T addedEntity : addedEntities) {
            repository.insert(addedEntity);
        }
    }
}
