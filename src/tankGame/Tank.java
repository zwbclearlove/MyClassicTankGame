package tankGame;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank {
    private int x;
    private int y;

    private static int width = 20;
    private static int height = 20;

    private int speed = 5;
    Direct dierct;
    Direct pdirect;
    private Image tankImage;
    private boolean isDead;
    private boolean isEnemy;
    public ArrayList<Bullet> bullets=new ArrayList<>();

    public Tank(){}

    public Tank(int x,int y,boolean isEnemy){
        this.x=x;
        this.y=y;
        this.isEnemy=isEnemy;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
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

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public Direct getDierct() {
        return dierct;
    }

    public Direct getPdirect() {
        return pdirect;
    }

    public Image getTankImage() {
        return tankImage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDierct(Direct dierct) {
        this.dierct = dierct;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public void setPdirect(Direct pdirect) {
        this.pdirect = pdirect;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setTankImage(Image tankImage) {
        this.tankImage = tankImage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



    public abstract void draw();
    public abstract void move();
    public abstract void shoot();
    public abstract Rectangle getCurrentPosition();
    public abstract Rectangle getNextPosition();
    public abstract boolean isCollisionBlocks(Map m);
    public abstract boolean isCollisionTank(Tank tank);
}
