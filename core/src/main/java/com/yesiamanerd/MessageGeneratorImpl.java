package com.yesiamanerd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    @Autowired
    private Game game;
    private int guessCount = 10;

    // == init ==
    @PostConstruct
    public void init() {
        log.info("game= {}", game.getNumber());
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() +
                " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return "You guessed it! The number was " + game.getNumber();
        } else if(game.isGameLost()){
            return "You lost! The number was " + game.getNumber();
        } else if (!game.isValidNumberRange()){
            return "Invalid number range";
        } else if(game.getRemainingGuesses() == guessCount){
            return "What is your first guess?";
        } else  {
            String direction = "lower";
            if(game.getGuess() < game.getNumber()){
                direction = "higher";
            }

            return direction + "! You have " + game.getRemainingGuesses() + " guesses left";
        }
    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy called: {}", game.getNumber());
    }
}
