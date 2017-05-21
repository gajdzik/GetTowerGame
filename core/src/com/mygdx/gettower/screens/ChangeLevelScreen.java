package com.mygdx.gettower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.gettower.GetTowerGameClass;

public class ChangeLevelScreen extends AbstractScreen {

    private SelectBox select_box;
    private TextButton return_button;

    public ChangeLevelScreen(final GetTowerGameClass game) {
        super(game);
    }

    @Override
    protected void init() {

        return_button = new TextButton("RETURN", skin);
        setButton(return_button, 300, 60, 100, 50);
        return_button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        select_box = new SelectBox(skin.get("big", SelectBox.SelectBoxStyle.class));
        stage.addActor(select_box);
        select_box.setPosition(100, 300);
        select_box.setSize(300, 50);
        select_box.setItems("Easy", "Medium", "Hard");

        if (prefs.getString(PREF_LEVEL) == "Easy")
        {
            select_box.setSelected("Easy");
        } else if (prefs.getString(PREF_LEVEL) == "Medium")
        {
            select_box.setSelected("Medium");
        } else
        {
            select_box.setSelected("Hard");
            prefs.putString(PREF_LEVEL,"Hard");
            prefs.flush();
        }

        select_box.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (select_box.getSelected() == "Easy") {
                    prefs.putString(PREF_LEVEL, "Easy");
                } else if (select_box.getSelected() == "Medium") {
                    prefs.putString(PREF_LEVEL, "Medium");
                } else {
                    prefs.putString(PREF_LEVEL, "Hard");
                }
                prefs.flush();
            }
        });

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
    }
}