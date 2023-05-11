package cc.oolong.notification;

import cc.oolong.clients.notification.NotificationRequest;
import cc.oolong.clients.notification.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping()
    public NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest) {
      log.info("new notification request {}", notificationRequest);
      notificationService.sendNotification(notificationRequest);
      return new NotificationResponse(true);
    }
}
