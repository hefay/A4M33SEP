package cz.cvut.sep.vorislu1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

}
