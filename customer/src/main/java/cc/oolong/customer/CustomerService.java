package cc.oolong.customer;

import cc.oolong.clients.fraud.FraudCheckResponse;
import cc.oolong.clients.fraud.FraudClient;
import cc.oolong.clients.notification.NotificationClient;
import cc.oolong.clients.notification.NotificationRequest;
import cc.oolong.clients.notification.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
     private final FraudClient fraudClient;
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
        String message = "Hi %s, Your account is now registered."
                .formatted(customer.getFirstName());
        NotificationResponse notificationResponse=notificationClient.sendNotification(
            new NotificationRequest(
                    customer.getId(),
                    customer.getEmail(),
                    message)
        );

        log.info("notification response: {} ",notificationResponse);
    }
}
