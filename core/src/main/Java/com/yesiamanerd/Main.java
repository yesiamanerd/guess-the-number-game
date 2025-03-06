package com.yesiamanerd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        // create context (container)
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        // get number generator bean from context
        NumberGenerator numberGenerator
                = context.getBean("numberGenerator", NumberGenerator.class);

        // call method to get a random number
        int number = numberGenerator.next();

        // log generated number
        log.info("number = {}", number);

        // get the game bean from context
        Game game = context.getBean(Game.class);

        // close context (container)
        context.close();
    }
}
