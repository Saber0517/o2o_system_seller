package com.oocl.jyhon.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProducerFrame extends JFrame {
    private JTextField brokerUrlTf;
    private JTextField destinationUrlTf;
    private JTextArea msgContentTa;
    private JButton openButton;
    private JButton sendButton;
    private PtpProducter ptpProducter;

    private Connection con;
    private Queue queue;
    private Session sen;
    private MessageProducer producer;

    public ProducerFrame() {
        this.setSize(450, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("发送者");
        init();
        addEvent();
        this.setVisible(true);
    }

    private void init() {
        ptpProducter = new PtpProducter();
        brokerUrlTf = new JTextField("defualt");
        destinationUrlTf = new JTextField("defualt");
        msgContentTa = new JTextArea(5, 60);
        openButton = new JButton("打开");
        sendButton = new JButton("发送");

        this.setLayout(null);
        brokerUrlTf.setBounds(10, 10, 400, 40);
        destinationUrlTf.setBounds(10, 60, 400, 40);

        msgContentTa.setBounds(10, 110, 400, 200);

        openButton = new JButton("打开");
        openButton.setBounds(10, 320, 90, 35);
        sendButton.setBounds(150, 320, 90, 35);

        add(brokerUrlTf);
        add(destinationUrlTf);
        add(msgContentTa);
        add(openButton);
        add(sendButton);
    }

    private void addEvent() {
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ptpProducter.open();
                ((JButton) e.getSource()).setEnabled(false);
            }
        });
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msgText = msgContentTa.getText();
                try {
                    ptpProducter.sendMessage(msgText);
                    /**TextMessage msg=sen.createTextMessage(msgText);
                     producer.send(msg);
                     sen.commit();*/
                } catch (JMSException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(con);
                System.out.println("disconect....");
                try {
                    producer.close();
                    sen.close();
                    con.close();
                } catch (JMSException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) {
        ProducerFrame mf = new ProducerFrame();
    }
}
