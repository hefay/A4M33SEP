package cz.cvut.sep.vorislu1.service;

import cz.cvut.sep.vorislu1.model.Client;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public interface ClientService {

    public List<Client> findAll();
    public List<Client> findAll(long from, long count);
    public List<Client> findAll(BigInteger from, BigInteger count);

    public Client find(BigInteger id);

}
