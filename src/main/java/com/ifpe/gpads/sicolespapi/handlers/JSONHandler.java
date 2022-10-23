package com.ifpe.gpads.sicolespapi.handlers;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {
    static JSONObject jsonObject = new JSONObject();
    static JSONParser jsonParser = new JSONParser();

    public static String getValuebyKey(MqttMessage mqttMessage, String key) throws ParseException {
        jsonObject = (JSONObject) jsonParser.parse(mqttMessage.toString());
        return (String) jsonObject.get(key);
    }
}
