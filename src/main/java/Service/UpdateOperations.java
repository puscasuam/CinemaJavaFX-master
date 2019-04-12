package Service;

import Domain.Entity;
import Repository.IRepository;

import java.util.List;

public class UpdateOperations<T extends Entity> extends UndoRedoOperation {
    private List<T> updatedEntities;
    private List<T> actualEntities;


    public UpdateOperations(IRepository<T> repository, List<T> updatedEntities, List<T> actualEntities) {
        super(repository);
        this.updatedEntities = updatedEntities;
        this.actualEntities = actualEntities;
    }

    @Override
    public void doUndo() {
        for (T updatedEntities : actualEntities) {
            repository.update(updatedEntities);
        }
    }

    @Override
    public void doRedo() {
        for (T updatedEntities : updatedEntities) {
            repository.update(updatedEntities);
        }
    }
}
