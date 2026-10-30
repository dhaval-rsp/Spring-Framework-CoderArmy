package in.strikes.L18_profileDemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NoitificationServiceImpl implements  NotificationService {

    @Override
    public String send() {
        return "Here is a Notification";
    }
}
