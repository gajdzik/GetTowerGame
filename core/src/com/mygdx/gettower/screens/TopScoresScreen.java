package com.mygdx.gettower.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private BitmapFont name_return;
    private HighscoreArray highscore_array;
    private Button return_button;
    private String name;

    public TopScoresScreen(final GetTowerGameClass game, HighscoreArray highscore_array, String name)
    {
        super(game);
        this.highscore_array = highscore_array;
        search_best_scores(name);
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
        name_return = new BitmapFont();
        name_return.getData().setScale(2, 2);


        return_button = new Button(new Button.ButtonStyle());
        return_button.setWidth(160);
        return_button.setHeight(60);
        return_button.setX(150);
        return_button.setY(50);
        return_button.setDebug(true);
        stage.addActor(return_button);
        return_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new RankingMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        if (name == "score")
            name_best_score.draw(spriteBatch,"Best scores", 150, 450 );
        else if (name == "platform")
            name_best_score.draw(spriteBatch,"Best platforms", 150, 450 );
        else
            name_best_score.draw(spriteBatch,"Best times", 150, 450 );

        name_first_place.draw(spriteBatch, "First place: " + String.valueOf(first_place),150,390);
        name_first_place.draw(spriteBatch, "Second place: " + String.valueOf(second_place),150,340);
        name_first_place.draw(spriteBatch, "Third place: " + String.valueOf(third_place),150,290);
        name_first_place.draw(spriteBatch, "Fourth place: " + String.valueOf(fourth_place),150,240);
        name_first_place.draw(spriteBatch, "Fifth place: " + String.valueOf(fifth_place),150,190);
        name_return.draw(spriteBatch, "Return",150,80);
       // stage.draw();
        spriteBatch.end();
    }
    
    private void search_best_scores(String name)
    {
        this.name = name;
        int i;
        for (Highscore hs : highscore_array.getArray_highscore())
        {
            if (name == "score")
                i = hs.getScore();
            else if (name == "platform")
                i = hs.getPlatform();
            else
                i = hs.getTime();

            if (i >= first_place)
            {
                fifth_place = fourth_place;
                fourth_place = third_place;
                third_place = second_place;
                second_place = first_place;
                first_place = i;
            }
            else if (i >= second_place )
            {
                fifth_place = fourth_place;
                fourth_place = third_place;
                third_place = second_place;
                second_place = i;
            }
            else if ( i >= third_place )
            {
                fifth_place = fourth_place;
                fourth_place = third_place;
                third_place = i;
            }
            else if ( i >= fourth_place )
            {
                fifth_place = fourth_place;
                fourth_place = i;
            }
            else if ( i >= fifth_place )
            {
                fifth_place = i;
            }
        }
    }

    @Override
    public void dispose()
    {
        name_best_score.dispose();
        name_first_place.dispose();
        name_second_place.dispose();
        name_third_place.dispose();
        name_fourth_place.dispose();
        name_fifth_place.dispose();
        name_return.dispose();
    }
}
