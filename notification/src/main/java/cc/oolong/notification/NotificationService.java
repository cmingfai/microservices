package cc.oolong.notification;

import cc.oolong.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Slf4j
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
         log.info("notification request {} sent",notificationRequest);
         notificationRepository.save(
                 Notification.builder()
                         .toCustomerEmail(notificationRequest.toCustomerEmail())
                         .toCustomerId(notificationRequest.toCustomerId())
                         .message(notificationRequest.message())
                         .sender("do-not-reply@oolong.cc")
                         .sentAt(LocalDateTime.now())
                         .build()
         );
    }
}
