package com.mygdx.gettower.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Platform extends Image
{

    public Platform(String platform_texture, float width, float height )
    {
        super(new Texture(platform_texture));
        this.setSize(width, height);
    }


}