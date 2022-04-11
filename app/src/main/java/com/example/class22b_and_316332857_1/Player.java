package com.example.class22b_and_316332857_1;

public class Player {

    private String PlayerName;
    private int score = 0;
    private final int startBotLocationX=0;
    private final int startBotLocationY=1;
    private final int startPlayerLocationX=4;
    private final int startPlayerLocationY=1;


    private int locationX;
    private int locationY;
    private String direction;


    public Player() {
    }


    public void initializationPlayer() {
        locationX =startPlayerLocationX;
        locationY =startPlayerLocationY;
        direction="";
    }

    public void initializationBot() {
        locationX =startBotLocationX;
        locationY =startBotLocationY;
    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public Player setPlayerName(String playerName) {
        PlayerName = playerName;
        return this;
    }


    public String getDirection() {
        return direction;
    }

    public Player setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public int getLocationX() {
        return locationX;
    }

    public Player setLocationX(int locationX) {
        this.locationX = locationX;
        return this;
    }

    public int getLocationY() {
        return locationY;
    }

    public Player setLocationY(int locationY) {
        this.locationY = locationY;
        return this;
    }



    public int getStartBotLocationX() {
        return startBotLocationX;
    }

    public int getStartBotLocationY() {
        return startBotLocationY;
    }

    public int getStartPlayerLocationX() {
        return startPlayerLocationX;
    }

    public int getStartPlayerLocationY() {
        return startPlayerLocationY;
    }

}
