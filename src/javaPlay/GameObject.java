/*
 * GameObject
 */
package javaPlay;

import java.awt.Graphics;

/**
 * @author VisionLab/PUC-Rio
 */
public abstract class GameObject {

    protected int x;
    protected int y;

    public abstract void step(long timeElapsed);
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
