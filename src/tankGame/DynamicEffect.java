package tankGame;

import java.awt.*;

public abstract class DynamicEffect {
    private int x,y;
    private int width;
    private int height;

    private boolean isDead;

    private Image DEImage;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getDEImage() {
        return DEImage;
    }

    public void setDEImage(Image DEImage) {
        this.DEImage = DEImage;
    }
    DynamicEffect(int x,int y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    abstract void draw();
    public Rectangle getCurrentPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());

    }
}
