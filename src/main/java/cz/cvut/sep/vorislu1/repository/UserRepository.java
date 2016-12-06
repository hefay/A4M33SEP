package cz.cvut.sep.vorislu1.repository;

import org.springframework.data.repository.CrudRepository;
import cz.cvut.sep.vorislu1.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Component
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
