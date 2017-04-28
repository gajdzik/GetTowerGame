package com.mygdx.gettower.screens;

        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.utils.Timer;
        import com.badlogic.gdx.utils.Timer.Task;

        import com.mygdx.gettower.GetTowerGameClass;

public class SplashScreen extends AbstractScreen
{
    private BitmapFont game_name;
    private BitmapFont made_by;
    private BitmapFont creator_name;

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
        game_name = new BitmapFont();
        game_name.getData().setScale(2, 2);

        made_by = new BitmapFont();
        made_by.getData().setScale(1.5f, 1.5f);

        creator_name = new BitmapFont();
        creator_name.getData().setScale(2, 2);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        game_name.draw(spriteBatch,"Get Tower", 175, 500 );
        made_by.draw(spriteBatch,"made by", 200, 400 );
        creator_name.draw(spriteBatch,"Przemyslaw  Gajdzinski", 100, 300 );
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        game_name.dispose();
        made_by.dispose();
        creator_name.dispose();
    }
}
