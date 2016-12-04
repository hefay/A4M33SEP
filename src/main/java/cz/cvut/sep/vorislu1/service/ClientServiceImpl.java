package cz.cvut.sep.vorislu1.service;

import cz.cvut.sep.service.customer.CustomerDatabaseWSDL;
import cz.cvut.sep.service.customer.CustomerDetailType;
import cz.cvut.sep.service.customer.CustomerType;
import cz.cvut.sep.vorislu1.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    CustomerDatabaseWSDL cd;

    @Override
    public List<Client> findAll() {
        return findAll(null, null);
    }

    @Override
    public List<Client> findAll(long from, long count) {
        return findAll(new BigInteger(String.valueOf(from)), new BigInteger(String.valueOf(count)));
    }

    @Override
    public List<Client> findAll(BigInteger from, BigInteger count) {
        List<CustomerType> clients = cd.readAllCustomers(BigInteger.ZERO, new BigInteger("500"));
        List<Client> ret = new ArrayList<>(clients.size());

        for(CustomerType c : clients) {
            Client cc = new Client();
            cc.setId(c.getId());
            cc.setFirstName(c.getFirstName());
            cc.setLastName(c.getSurname());
            ret.add(cc);
        }
        return ret;
    }

    @Override
    public Client find(BigInteger id) {
        Holder<BigInteger> idHolder = new Holder<>(id);
        Holder<String> statusHolder = new Holder<>();
        Holder<CustomerDetailType> detailHolder = new Holder<>();

        cd.readCustomerDetails(idHolder, statusHolder, detailHolder);
        Client client = new Client();

        return client;
    }

}
