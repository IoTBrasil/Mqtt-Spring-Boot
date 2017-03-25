package mqt.spring.example.mqtt.config.subscriber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

/**
 * Created by wahrons on 25/03/17.
 */
@Builder
@AllArgsConstructor
public class MQTTSubscriber implements MqttCallback {

    private List<String> messages;

    public void connectionLost(Throwable throwable) {
        throw new RuntimeException("Connection Lost.", throwable);
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Topic:" + mqttMessage.getId());
        String message = new String(mqttMessage.getPayload());
        System.out.println("Message Receive: " + message);
        messages.add(message);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        throw new RuntimeException("Should not receive any message.");
    }

    public void subscribe(MqttClient mqttClient, String topicDescription) throws MqttException {
        int subQoS = 0;
        mqttClient.subscribe(topicDescription, subQoS);
    }


}

