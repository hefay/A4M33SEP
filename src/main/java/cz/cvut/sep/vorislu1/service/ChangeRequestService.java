package cz.cvut.sep.vorislu1.service;

import cz.cvut.sep.vorislu1.model.Change;
import cz.cvut.sep.vorislu1.model.ChangeRequest;

import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public interface ChangeRequestService {
    public List<ChangeRequest> getAll();
    public ChangeRequest find(long id);
    public void delete(long id);
    public void pushAll();
    public void push(ChangeRequest changeRequest);
}
