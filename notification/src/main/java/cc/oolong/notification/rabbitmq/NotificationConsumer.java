package cc.oolong.notification.rabbitmq;

import cc.oolong.clients.notification.NotificationRequest;
import cc.oolong.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {
  private final NotificationService notificationService;

  @RabbitListener(queues = {"${rabbitmq.queues.internal}"})
  public void consume(NotificationRequest notificationRequest) {
      log.info("Consumed {} from queue", notificationRequest);
      notificationService.sendNotification(notificationRequest);
  }
}
