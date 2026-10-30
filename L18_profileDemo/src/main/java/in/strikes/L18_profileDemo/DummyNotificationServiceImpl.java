package in.strikes.L18_profileDemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"})
public class DummyNotificationServiceImpl implements NotificationService{

    @Override
    public String send() {
        return "Here is a dummy notification; which means no real notification send";
    }
}
