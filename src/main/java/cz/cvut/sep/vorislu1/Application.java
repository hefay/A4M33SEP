package cz.cvut.sep.vorislu1;

import cz.cvut.sep.service.customer.CustomerDatabase;
import cz.cvut.sep.service.customer.CustomerDatabaseWSDL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] beans = ctx.getBeanDefinitionNames();
        for(String bean : beans) {
            System.out.println(bean);
        }
    }

    @Bean
    public CustomerDatabase customerDatabaseInternal() throws MalformedURLException {
        URL url = new URL("http://Helios:8088/mockCustomerDatabaseSOAP?wsdl");
        return new CustomerDatabase(url);
    }

    @Bean
    public CustomerDatabaseWSDL customerDatabase(CustomerDatabase cd) {
        return cd.getCustomerDatabaseSOAP();
    }
}
