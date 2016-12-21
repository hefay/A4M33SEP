package cz.cvut.sep.vorislu1;

import cz.cvut.sep.vorislu1.service.ChangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Configuration
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    ChangeRequestService changeRequestService;

    @Scheduled(cron="30 2 * * * ?")
    public void scheduleFixedDelayTask() {
        try {
            changeRequestService.pushAll();
        } catch (Exception e) {
            // Tady by byl nejaky log nebo tak
        }
    }
}
