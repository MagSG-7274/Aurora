package com.stsf.aurora.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class AfterInitComponent implements ApplicationListener<ContextRefreshedEvent> {

    //private static final Logger logger = LoggerFactory.getLogger(ContextRefreshedEvent.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        String ip;
        ip = getLocalIP();

        if (ip.contains("unable")) {
            ip = "localhost";
        }

//        logger.info("Setup Complete!");
//        logger.info("Go to " + ip + ":8080 for more information");
        System.out.println("Yaay Setup Complete!");
        System.out.printf("Go to %s:8080 for more information!\n", getLocalIP());


    }

    private String getLocalIP() {

        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException ignored) {}

        return "Unable to obtain IP";
    }
}
