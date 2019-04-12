package Service;

import Domain.Entity;
import Repository.IRepository;

import java.util.List;

public class DeleteOperations<T extends Entity> extends UndoRedoOperation {
    private List<T> deletedEntities;

    public DeleteOperations(IRepository repository, List<T> deletedEntities) {
        super(repository);
        this.deletedEntities = deletedEntities;
    }

    @Override
    public void doUndo() {
        for (T deletedEntity : deletedEntities) {
            repository.insert(deletedEntity);
        }
    }

    @Override
    public void doRedo() {

        for (T deletedEntity : deletedEntities) {
            repository.remove(deletedEntity.getId());
        }

    }
}
