package cz.cvut.sep.vorislu1.service;

import cz.cvut.sep.vorislu1.model.ChangeRequest;
import cz.cvut.sep.vorislu1.repository.ChangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Service
public class ChangeRequestServiceImpl implements ChangeRequestService {
    @Autowired
    ChangeRequestRepository changeRequestRepository;

    @Override
    public List<ChangeRequest> getAll() {
        Iterable<ChangeRequest> chrr = changeRequestRepository.findAll();
        List<ChangeRequest> chr = new ArrayList<>();
        for(ChangeRequest ch : chrr) {
            chr.add(ch);
        }

        return chr;
    }

    @Override
    public ChangeRequest find(long id) {
        return changeRequestRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        ChangeRequest chr = changeRequestRepository.findOne(id);
        changeRequestRepository.delete(chr);
    }
}
