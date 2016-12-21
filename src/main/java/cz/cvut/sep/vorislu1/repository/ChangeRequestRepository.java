package cz.cvut.sep.vorislu1.repository;

import cz.cvut.sep.vorislu1.model.ChangeRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public interface ChangeRequestRepository extends CrudRepository<ChangeRequest, Long> {
    public List<ChangeRequest> findBySync(boolean sync);
}
