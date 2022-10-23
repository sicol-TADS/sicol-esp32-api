package com.ifpe.gpads.sicolespapi;

import com.ifpe.gpads.sicolespapi.config.Config;
import com.ifpe.gpads.sicolespapi.espsimulator.EngineTemperatureSensor;
import com.ifpe.gpads.sicolespapi.handlers.MqttHandler;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SicolEsp32ApiApplication {

	static String host = Config.get("MQTT_HOST");
	static String username = Config.get("MQTT_CLIENT_USERNAME");
	static String topic = Config.get("MQTT_TOPIC");

	public static void main(String[] args) throws Exception {
		IMqttClient client = MqttHandler.connectionHandler(host, username);

		MqttHandler.subscribeHandler(client, topic);
	}

}
