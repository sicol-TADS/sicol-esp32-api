package com.ifpe.gpads.sicolespapi.handlers;

import com.ifpe.gpads.sicolespapi.config.Config;
import com.ifpe.gpads.sicolespapi.dao.ConnectionManager;
import org.eclipse.paho.client.mqttv3.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MqttHandler {

    static String jsonId = Config.get("JSON_ID");
    public static IMqttClient connectionHandler(String host, String username) throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        IMqttClient client = new MqttClient(host, username);
        client.connect(options);

        return client;
    }

    public static void subscribeHandler(IMqttClient client, String topic) throws MqttException {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                String sql = "UPDATE trash_can SET isFull = true WHERE id = ?";

                try {
                    PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
                    pstm.setString(1, JSONHandler.getValuebyKey(mqttMessage, jsonId));

                    pstm.execute();
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

        client.subscribe(topic);
    }
}
