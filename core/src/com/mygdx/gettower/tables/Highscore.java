package com.mygdx.gettower.tables;

public class Highscore {
    private int platform;
    private int score;
    private int time;

    public Highscore() {
        this.platform = 0;
        this.score = 0;
        this.time = 0;
    }
    public Highscore(int platform, int score, int time) {
        this.platform = platform;
        this.score = score;
        this.time = time;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
