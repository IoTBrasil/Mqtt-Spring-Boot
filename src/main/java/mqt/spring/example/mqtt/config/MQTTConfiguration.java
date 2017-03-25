package mqt.spring.example.mqtt.config;

import mqt.spring.example.mqtt.config.subscriber.MQTTSubscriber;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wahrons on 25/03/17.
 */
@Configuration
public class MQTTConfiguration {

    @Value("CLOUDMQTT_URL")
    private String mqqt_url;


    @Bean
    @Qualifier("cache")
    public List cacheList() {
        return new ArrayList();
    }

    @Bean
    public MQTTSubscriber crateMqttCallback(@Qualifier("cache") List cache) {
        return new MQTTSubscriber(cache);
    }

    @Bean
    @Autowired
    public MqttClient connect(MQTTSubscriber callback) throws MqttException, URISyntaxException {
        MqttConnectOptions connOpt = new MqttConnectOptions();

        URI url = new URI(mqqt_url);
        System.out.println("Test" + mqqt_url);
        String[] userAndPassword = url.getRawUserInfo().split(":");
        String broker_url = new StringBuilder()
                .append("tcp://")
                .append(url.getHost())
                .append(":")
                .append(url.getPort()).toString();


        connOpt.setCleanSession(true);
        connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(userAndPassword[0]);
        connOpt.setPassword(userAndPassword[1].toCharArray());

        MqttClient client = new MqttClient(broker_url, MqttClient.generateClientId());

        if (callback != null)
            client.setCallback(callback);
        client.connect(connOpt);

        callback.subscribe(client, "DOMAIN");

        return client;
    }
}
