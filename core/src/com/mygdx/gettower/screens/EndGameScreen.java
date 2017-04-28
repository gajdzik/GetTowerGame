package com.mygdx.gettower.screens;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.ui.Button;
        import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

        import com.mygdx.gettower.GetTowerGameClass;

public class EndGameScreen extends AbstractScreen
{
    private Button quitButton;
    private Button restartButton;
    private Sound end_game_music;
    private Sound new_record_music;
    private boolean isBeatBestScore;
    private BitmapFont name_score;
    private BitmapFont name_end_game;
    private BitmapFont name_quit_game;
    private BitmapFont name_restart;
    private int player_score;

    public EndGameScreen(GetTowerGameClass game, boolean isBeatBestScore, int playerScore)
    {
        super(game);
        this.isBeatBestScore = isBeatBestScore;
        this.player_score = playerScore;
    }

    @Override
    protected void init()
    {
        initButtons();
        initSounds();
        soundPlay();
        name_score = new BitmapFont();
        name_score.getData().setScale(1.5f, 1.5f);
        name_end_game = new BitmapFont();
        name_end_game.getData().setScale(1.5f, 1.5f);
        name_quit_game = new BitmapFont();
        name_quit_game.getData().setScale(1.5f, 1.5f);
        name_restart = new BitmapFont();
        name_restart.getData().setScale(1.5f, 1.5f);
    }

    private void soundPlay()
    {
        if (isBeatBestScore)
        {
            new_record_music.play();
        }
        else
        {
            end_game_music.play();
        }
    }

    private void initSounds()
    {
        end_game_music = Gdx.audio.newSound(Gdx.files.internal("attack.ogg"));
        new_record_music = Gdx.audio.newSound(Gdx.files.internal("cheer.ogg"));
    }

    private void initButtons()
    {
        quitButton = new Button(new ButtonStyle());
        setButton(quitButton, 160, 120, 50, 100);
        stage.addActor(quitButton);
        quitButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                System.exit(0);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        restartButton = new Button(new ButtonStyle());
        setButton(restartButton, 200, 120, 250, 100);
        stage.addActor(restartButton);
        restartButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new GameplayScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void setButton(Button button, int w, int h, int x, int y)
    {
        button.setWidth(w);
        button.setHeight(h);
        button.setX(x);
        button.setY(y);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
        spriteBatch.begin();
        if (isBeatBestScore)
        {
            name_end_game.draw(spriteBatch, "Congratulactions, you beat best score!",80,400);
        }
        else
        {
            name_end_game.draw(spriteBatch, "Sorry, you fail...",180,400);

        }
        name_score.draw(spriteBatch, "Your score: " + String.valueOf(player_score),190,350);

        name_quit_game.draw(spriteBatch, "QUIT GAME",60,170);
        name_restart.draw(spriteBatch, "RESTART GAME",260,170);

        spriteBatch.end();
    }
}
