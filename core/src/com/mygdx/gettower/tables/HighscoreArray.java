package com.mygdx.gettower.tables;

import com.badlogic.gdx.utils.Array;

public class HighscoreArray {
    private Array<Highscore> array_highscore;

    public HighscoreArray() {
        array_highscore = new Array<Highscore>();
    }

    public HighscoreArray(Array<Highscore> array_highscore) {
        this.array_highscore = array_highscore;
    }

    public Array<Highscore> getArray_highscore() {
        return array_highscore;
    }

    public void setArray_highscore(Array<Highscore> array_highscore) {
        this.array_highscore = array_highscore;
    }

 }
