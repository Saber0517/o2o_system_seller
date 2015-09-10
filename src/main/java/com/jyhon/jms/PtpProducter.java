package com.jyhon.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PtpProducter {

    private static Map<String, String> configMap = new HashMap<String, String>();
    private Connection connection;
    private Queue queue;
    private Session session;
    private MessageProducer producer;

    static {
        InputStream inputStream = PtpProducter.class.getClassLoader().getResourceAsStream("jms.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            for (Object keyName : p.keySet()) {
                configMap.put((String) keyName, (String) p.get(keyName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        String brokerUrl = configMap.get("serveraddress");
        String destinationUrl = configMap.get("queuename");
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            queue = new ActiveMQQueue(destinationUrl);
            connection = factory.createConnection();
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            producer = session.createProducer(queue);
            connection.start();
            System.out.println("connect: " + brokerUrl + "......'s " + destinationUrl);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getConfigMap() {
        Map<String, String> mirrorMap = new HashMap<String, String>(configMap);
        return mirrorMap;
    }

    public Integer sendMessage(String json) throws JMSException {
        TextMessage msg = session.createTextMessage(json);
        producer.send(msg);
        session.commit();
        return 0;
    }

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
        Queue queue = new ActiveMQQueue("john_seq");
        Connection con = factory.createConnection();
        Session sen = con.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        MessageProducer producer = sen.createProducer(queue);
        con.start();


        TextMessage msg = sen.createTextMessage("Hello,i am john!!!");
        producer.send(msg);
        producer.send(msg);
        sen.commit();
        producer.close();
        sen.close();
        con.close();
    }

}
