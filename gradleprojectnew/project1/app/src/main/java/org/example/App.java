package org.example;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        final Logger logger = LogManager.getLogger(App.class);
        logger.debug("Logging tests");
        System.out.println("Hello World");
    }
}
