package cc.oolong.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics="amigoscode",
            groupId="groupId",
    containerFactory="messageFactory")
    void listener(Message message) {
        System.out.println("Listener received: "+message);
    }
}
