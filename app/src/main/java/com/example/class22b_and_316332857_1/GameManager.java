package com.example.class22b_and_316332857_1;

public class GameManager {
    private final int MAX_LIVES = 3;
    private int lives = MAX_LIVES;

    private Player player;
    private Player bot;

    private String DIRECTION[]={
            "UP",
            "DOWN",
            "LEFT",
            "RIGHT"
    };


    private Boolean isCrash=false;

    public GameManager() {
        player = new Player();
        bot = new Player();
        player.initializationPlayer();
        bot.initializationBot();
    }

    public Player getPlayer() {
        return player;
    }

    public Player getBot() {
        return bot;
    }


    public Boolean getCrash() {
        return isCrash;
    }

    public GameManager setCrash(Boolean crash) {
        isCrash = crash;
        return this;
    }

    public int getLives() {
        return lives;
    }


    public void reduceLives() {
        lives--;
    }

    public void bootLocations() {
        player.initializationPlayer();
        bot.initializationBot();
    }

    public void runLogic() {
        randomBotDirectionMove();
        playerMove();
        checkCrash();
    }


    public void randomBotDirectionMove() {

        bot.setDirection(DIRECTION[(int)(Math.random() * 4)]);
        switch (bot.getDirection()) {
            case "UP":
                if(bot.getLocationX()>0) {
                    bot.setLocationX(bot.getLocationX()-1);
                }
                break;
            case "DOWN":
                if(bot.getLocationX()<4) {
                    bot.setLocationX(bot.getLocationX()+1);
                }
                else{
                    bot.setLocationX(bot.getStartBotLocationX());
                    bot.setLocationY(bot.getStartPlayerLocationY());
                }
                break;
            case "LEFT":
                if(bot.getLocationY()>0) {
                    bot.setLocationY(bot.getLocationY()-1);
                }
                break;
            case "RIGHT":
                if(bot.getLocationY()<2) {
                    bot.setLocationY(bot.getLocationY()+1);
                }
                break;
        }
    }

    public void playerMove() {
        switch (player.getDirection()) {
            case "":
                break;
            case "UP":
                if(player.getLocationX()>0) {
                    player.setLocationX(player.getLocationX()-1);
                }
                break;
            case "DOWN":
                if(player.getLocationX()<6) {
                    player.setLocationX(player.getLocationX()+1);
                }
                break;
            case "LEFT":
                if(player.getLocationY()>0) {
                    player.setLocationY(player.getLocationY()-1);
                }
                break;
            case "RIGHT":
                if(player.getLocationY()<4) {
                    player.setLocationY(player.getLocationY()+1);
                }
                break;
        }

    }

    public void checkCrash() {
        if (player.getLocationX()==bot.getLocationX() && player.getLocationY()==bot.getLocationY()) {
            isCrash=true;
            if(lives>0){
                reduceLives();
            }
        }
    }







}










