package cz.cvut.sep.vorislu1;

import cz.cvut.sep.service.customer.CustomerDatabase;
import cz.cvut.sep.service.customer.CustomerDatabaseWSDL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@SpringBootApplication
public class Application {

    @Value("${customer-database.wsdl}")
    private String customerDatabaseWsdlUrl;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] beans = ctx.getBeanDefinitionNames();
        for(String bean : beans) {
            System.out.println(bean);
        }
    }

    @Bean
    public CustomerDatabase customerDatabaseInternal() throws MalformedURLException {
        URL url = new URL(customerDatabaseWsdlUrl);
        return new CustomerDatabase(url);
    }

    @Bean
    public CustomerDatabaseWSDL customerDatabase(CustomerDatabase cd) {
        return cd.getCustomerDatabaseSOAP();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.addBasenames("locale\\messages");
        return rb;
    }
}
