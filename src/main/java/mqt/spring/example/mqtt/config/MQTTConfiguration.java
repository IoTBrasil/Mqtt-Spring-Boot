package mqt.spring.example.mqtt.config;

import mqt.spring.example.mqtt.config.subscriber.MQTTSubscriber;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wahrons on 25/03/17.
 */
@Configuration
public class MQTTConfiguration {

    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String DOMAIN = "<Insert m2m.io domain here>";
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";


    @Bean
    public MQTTSubscriber crateMqttCallback() {
        return MQTTSubscriber.builder().build();
    }

    @Bean
    @Autowired
    public MqttClient connect(MQTTSubscriber callback) throws MqttException {
        MqttConnectOptions connOpt = new MqttConnectOptions();

        connOpt.setCleanSession(true);
        connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(USERNAME);
        connOpt.setPassword(PASSWORD.toCharArray());

        MqttClient client = new MqttClient(BROKER_URL, MqttClient.generateClientId());

        if (callback != null)
            client.setCallback(callback);
        client.connect(connOpt);

        callback.subscribe(client, DOMAIN);

        return client;
    }
}
