package com.mygdx.gettower.entities;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.scenes.scene2d.Action;
        import com.badlogic.gdx.scenes.scene2d.actions.Actions;
        import com.badlogic.gdx.scenes.scene2d.ui.Image;

        import com.mygdx.gettower.GetTowerGameClass;

public class Player extends Image
{
    private final static int WIDTH = 30;
    private final static int HEIGHT = 50;
    private final static int STARTING_X = 215;
    private final static int STARTING_Y = 60;
    public boolean canJump = true;
    public float jumpDelay;
    private Sound jumpSound;

    public Player(String avatar)
    {
        super(new Texture(avatar));
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);
        this.jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
    }

    public void goLeft()
    {
        if (this.getX() - 25 <= 0)
        {
            this.setPosition(0, getY());
        }
        else
        {
            Action goLeftAction = Actions.moveBy(-35,0,0.2f);
            this.addAction(goLeftAction);
        }
    }

    public void goRight()
    {
        if (this.getX() + 25 >= GetTowerGameClass.WIDTH - Player.WIDTH)
        {
            this.setPosition(450, getY());
        }
        else
        {
            Action goRightAction = Actions.moveBy(35,0, 0.2f);
            this.addAction(goRightAction);
        }
    }

    public void goUp()
    {
        if (canJump == true)
        {
            this.jumpDelay = 200;
            jumpSound.play();
            canJump = false;
        }
    }

}
