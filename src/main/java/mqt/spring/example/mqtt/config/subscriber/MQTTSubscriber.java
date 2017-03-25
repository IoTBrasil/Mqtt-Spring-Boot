package mqt.spring.example.mqtt.config.subscriber;

import lombok.Builder;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by wahrons on 25/03/17.
 */
@Builder
public class MQTTSubscriber implements MqttCallback {

    public void connectionLost(Throwable throwable) {
        throw new RuntimeException("Connection Lost.", throwable);
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Topic:" + mqttMessage.getId());
        System.out.println("Message Receive: " + new String(mqttMessage.getPayload()));
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        throw new RuntimeException("Should not receive any message.");
    }

    public void subscribe(MqttClient mqttClient, String topicDescription) throws MqttException {
        int subQoS = 0;
        mqttClient.subscribe(topicDescription, subQoS);
    }
}

