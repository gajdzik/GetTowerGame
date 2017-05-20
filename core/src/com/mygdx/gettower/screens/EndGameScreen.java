package com.mygdx.gettower.screens;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

        import com.mygdx.gettower.GetTowerGameClass;

public class EndGameScreen extends AbstractScreen
{
    private TextButton quitButton;
    private TextButton restartButton;
    private TextButton return_button;
    private Sound end_game_music;
    private Sound new_record_music;
    private boolean isBeatBestScore;
    private Label label_score;
    private Label label_end_game;
    private int player_score;

    public EndGameScreen(GetTowerGameClass game, boolean isBeatBestScore, int playerScore)
    {
        super(game);
        this.isBeatBestScore = isBeatBestScore;
        this.player_score = playerScore;
        init_labels();
    }

    @Override
    protected void init()
    {
        initButtons();
        initSounds();
        soundPlay();

    }

    private void init_labels()
    {
        if (isBeatBestScore)
        {
            label_end_game = new Label("Congratulactions, you beat best score!",skin.get("title",Label.LabelStyle.class));
            label_end_game.setPosition(20,500);
        }
        else
        {
            label_end_game = new Label("Sorry, you fail...",skin.get("title",Label.LabelStyle.class));
            label_end_game.setPosition(80,500);
        }
        stage.addActor(label_end_game);
        label_score = new Label("Your score: " + String.valueOf(player_score),skin.get("title",Label.LabelStyle.class));
        label_score.setPosition(90,400);
        stage.addActor(label_score);
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
        quitButton = new TextButton("QUIT GAME",skin);
        setButton(quitButton, 300, 60, 100, 50);
        quitButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                System.exit(0);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        restartButton = new TextButton("RESTART GAME",skin);
        setButton(restartButton, 300, 60, 100, 250);
        restartButton.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new GameplayScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        return_button = new TextButton("RETURN",skin);
        setButton(return_button, 300, 60, 100, 150);
        return_button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new MenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
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
        end_game_music.dispose();
        new_record_music.dispose();
    }
}
