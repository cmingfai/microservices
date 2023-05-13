package cc.oolong.customer;

import cc.oolong.amqp.RabbitMQMessageProducer;
import cc.oolong.clients.fraud.FraudCheckResponse;
import cc.oolong.clients.fraud.FraudClient;
import cc.oolong.clients.notification.NotificationClient;
import cc.oolong.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {
     private final CustomerRepository customerRepository;
     private final FraudClient fraudClient;
     private final RabbitMQMessageProducer rabbitMQMessageProducer;
     private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer=Customer
                .builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        // todo: check if email valid
        // todo: check if email no taken

        // check if fraudster

        FraudCheckResponse fraudCheckResponse=fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // send notification
        String message = "Hi %s, your account is now registered."
                .formatted(customer.getFirstName());
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                message);

        this.notificationClient.sendNotification(notificationRequest);
//         this.rabbitMQMessageProducer.publish(
//                 notificationRequest,
//                 "internal.exchange",
//                 "internal.notification.routing-key");

    }
}
