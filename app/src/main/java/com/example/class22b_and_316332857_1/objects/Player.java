package com.example.class22b_and_316332857_1.objects;

public class Player {

    private String PlayerName;
    private int score = 0;
    private final int startBotLocationX=0;
    private final int startBotLocationY=2;
    private final int startPlayerLocationX=6;
    private final int startPlayerLocationY=2;


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

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
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
