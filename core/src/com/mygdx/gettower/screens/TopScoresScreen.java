package com.mygdx.gettower.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private HighscoreArray highscore_array;
    private TextButton return_button;
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

        // TODO: Create class "TextActor" and with Bitmapfont name and int score
        // TODO: probably change bitmapfonts for TextActor
        name_best_score = skin.getFont("title");
        name_first_place = skin.getFont("title");
        name_second_place = skin.getFont("title");
        name_third_place = skin.getFont("title");
        name_fourth_place = skin.getFont("title");
        name_fifth_place = skin.getFont("title");
        name_best_score.getData().setScale(0.8f, 0.8f);


        return_button = new TextButton("RETURN",skin);
        setButton(return_button, 160, 60, 150, 50);
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
        //stage.draw();
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
    }
}
