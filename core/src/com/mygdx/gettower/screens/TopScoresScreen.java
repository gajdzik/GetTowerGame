package com.mygdx.gettower.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
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
    private HighscoreArray highscore_array;
    private TextButton return_button;
    private String name;
    private Label label_best;
    private Label label_first_place;
    private Label label_second_place;
    private Label label_third_place;
    private Label label_fourth_place;
    private Label label_fifth_place;

    public TopScoresScreen(final GetTowerGameClass game, HighscoreArray highscore_array, String name)
    {
        super(game);
        this.highscore_array = highscore_array;
        search_best_scores(name);
        init_labels();
    }

    @Override
    protected void init() {
        first_place = 0;
        second_place = 0;
        third_place = 0;
        fourth_place = 0;
        fifth_place = 0;

        return_button = new TextButton("RETURN",skin);
        setButton(return_button, 300, 60, 100, 50);
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

    // TODO: use Label instead of BitMapfont in every screen
    private void init_labels()
    {
        if (name == "score")
            label_best = new Label("Best scores:",skin.get("title",LabelStyle.class));
        else if (name == "platform")
            label_best = new Label("Best platforms:",skin.get("title",LabelStyle.class));
        else
            label_best = new Label("Best times:",skin.get("title",LabelStyle.class));
        label_best.setPosition(100,450);
        stage.addActor(label_best);

        label_first_place = new Label("First place: " + String.valueOf(first_place),skin.get("title",LabelStyle.class));
        label_first_place.setPosition(100,390);
        stage.addActor(label_first_place);

        label_second_place = new Label("Second place: " + String.valueOf(second_place),skin.get("title",LabelStyle.class));
        label_second_place.setPosition(100,340);
        stage.addActor(label_second_place);

        label_third_place = new Label("Third place: " + String.valueOf(third_place),skin.get("title",LabelStyle.class));
        label_third_place.setPosition(100,290);
        stage.addActor(label_third_place);

        label_fourth_place = new Label("Fourth place: " + String.valueOf(fourth_place),skin.get("title",LabelStyle.class));
        label_fourth_place.setPosition(100,240);
        stage.addActor(label_fourth_place);

        label_fifth_place = new Label("Fifth place: " + String.valueOf(fifth_place),skin.get("title",LabelStyle.class));
        label_fifth_place.setPosition(100,190);
        stage.addActor(label_fifth_place);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        stage.draw();
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
    }
}
