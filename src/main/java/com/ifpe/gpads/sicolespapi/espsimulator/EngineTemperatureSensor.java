package com.ifpe.gpads.sicolespapi.espsimulator;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;

import java.util.Random;
import java.util.concurrent.Callable;

public class EngineTemperatureSensor implements Callable<Void> {
    public static final String TOPIC = "esp/distancia";
    private IMqttClient client;
    private Random rnd = new Random();

    public EngineTemperatureSensor(IMqttClient client){
        this.client = client;
    }

    public Void call() throws Exception {
        MqttMessage msg = readEngineTemp();
        msg.setQos(0);
        client.publish(TOPIC, msg);

        return null;
    }

    private MqttMessage readEngineTemp(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", "ESP32-HCSR04-1");
        jsonObject.put("distance", "139.34");


        String x = jsonObject.toJSONString();
        byte[] payload = x.getBytes();
        MqttMessage msg = new MqttMessage(payload);

        return msg;
    }
}
