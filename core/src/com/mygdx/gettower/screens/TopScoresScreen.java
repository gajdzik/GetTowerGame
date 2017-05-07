package com.mygdx.gettower.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.gettower.GetTowerGameClass;
import com.mygdx.gettower.tables.Highscore;
import com.mygdx.gettower.tables.HighscoreArray;

public class TopScoresScreen extends AbstractScreen {

    private int first_place;
    private int second_place;
    private int third_place;
    private int fourth_place;
    private int fifth_place;
    private BitmapFont name_best_score;
    private BitmapFont name_first_place;
    private BitmapFont name_second_place;
    private BitmapFont name_third_place;
    private BitmapFont name_fourth_place;
    private BitmapFont name_fifth_place;
    private HighscoreArray highscore_array;

    public TopScoresScreen(final GetTowerGameClass game, HighscoreArray highscore_array)
    {
        super(game);
        this.highscore_array = highscore_array;
        search_best_scores();
    }

    @Override
    protected void init() {
        first_place = 0;
        second_place = 0;
        third_place = 0;
        fourth_place = 0;
        fifth_place = 0;

        name_best_score = new BitmapFont();
        name_best_score.getData().setScale(2, 2);
        name_first_place = new BitmapFont();
        name_first_place.getData().setScale(2, 2);
        name_second_place = new BitmapFont();
        name_second_place.getData().setScale(2, 2);
        name_third_place = new BitmapFont();
        name_third_place.getData().setScale(2, 2);
        name_fourth_place = new BitmapFont();
        name_fourth_place.getData().setScale(2, 2);
        name_fifth_place = new BitmapFont();
        name_fifth_place.getData().setScale(2, 2);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        name_best_score.draw(spriteBatch,"Best scores", 150, 450 );
        name_first_place.draw(spriteBatch, "First place: " + String.valueOf(first_place),150,390);
        name_first_place.draw(spriteBatch, "Second place: " + String.valueOf(second_place),150,340);
        name_first_place.draw(spriteBatch, "Third place: " + String.valueOf(third_place),150,290);
        name_first_place.draw(spriteBatch, "Fourth place: " + String.valueOf(fourth_place),150,240);
        name_first_place.draw(spriteBatch, "Fifth place: " + String.valueOf(fifth_place),150,190);
        spriteBatch.end();
    }
    
    private void search_best_scores()
    {
        for (Highscore hs : highscore_array.getArray_highscore())
        {
            if (hs.getScore() >= first_place)
            {
                fifth_place = fourth_place;
                fourth_place = third_place;
                third_place = second_place;
                second_place = first_place;
                first_place = hs.getScore();
            }
            else if ( hs.getScore() >= second_place )
            {
                fifth_place = fourth_place;
                fourth_place = third_place;
                third_place = second_place;
                second_place = hs.getScore();
            }
            else if ( hs.getScore() >= third_place )
            {
                fifth_place = fourth_place;
                fourth_place = third_place;
                third_place = hs.getScore();
            }
            else if ( hs.getScore() >= fourth_place )
            {
                fifth_place = fourth_place;
                fourth_place = hs.getScore();
            }
            else if ( hs.getScore() >= fifth_place )
            {
                fifth_place = hs.getScore();
            }
        }
    }

    @Override
    public void dispose()
    {
    }
}
