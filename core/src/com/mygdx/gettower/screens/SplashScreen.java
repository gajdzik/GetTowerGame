package com.mygdx.gettower.screens;

        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.utils.Timer;
        import com.badlogic.gdx.utils.Timer.Task;
        import com.mygdx.gettower.GetTowerGameClass;

public class SplashScreen extends AbstractScreen
{
    private Label label_game_name;
    private Label label_made_by;
    private Label label_creator_name;

    public SplashScreen(final GetTowerGameClass game)
    {
        super(game);
        Timer.schedule(new Task()
                       {
                           @Override
                           public void run()
                           {
                               game.setScreen(new MenuScreen(game));
                           }

                       }
                , 3);
    }

    @Override
    protected void init()
    {
        label_game_name = new Label("Get Tower",skin.get("title",Label.LabelStyle.class));
        label_game_name.setPosition(160,400);
        label_game_name.setFontScale(0.8f);
        stage.addActor(label_game_name);

        label_made_by = new Label("made by",skin.get("title",Label.LabelStyle.class));
        label_made_by.setPosition(175,300);
        label_made_by.setFontScale(0.8f);
        stage.addActor(label_made_by);

        label_creator_name = new Label("Przemyslaw Gajdzinski",skin.get("title",Label.LabelStyle.class));
        label_creator_name.setPosition(50,200);
        label_creator_name.setFontScale(0.8f);
        stage.addActor(label_creator_name);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.draw();
    }

    @Override
    public void dispose()
    {
    }
}
