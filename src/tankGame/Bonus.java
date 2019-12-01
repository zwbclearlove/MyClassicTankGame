package tankGame;

import java.awt.*;
import java.util.Random;

public class Bonus {
    private int x,y,width=20,height=20;
    private int lastTime;
    private Image bounsImage;
    private boolean isDead=false;
    private BonusType bonusType;
    public Bonus(){ }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public BonusType getBonusType() {
        return bonusType;
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

    public Image getBounsImage() {
        return bounsImage;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setBounsImage(Image bounsImage) {
        this.bounsImage = bounsImage;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    Bonus(int x,int y,int lastTime){
        Random r=new Random();
        this.x=x;
        this.y=y;
        this.lastTime=lastTime;
        int t=r.nextInt(7);
        switch (t){
            case 0:
                bonusType=BonusType.BOOM;
                break;
            case 1:
                bonusType=BonusType.GUN;
                break;
            case 2:
                bonusType=BonusType.LIFE;
                break;
            case 3:
                bonusType=BonusType.SHOVEL;
                break;
            case 4:
                bonusType=BonusType.HELMET;
                break;
            case 5:
                bonusType=BonusType.TIME;
                break;
            case 6:
                bonusType=BonusType.STAR;
                break;
        }
    }
    public Rectangle getCurrentPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());

    }
    public void draw(){
        if(lastTime==0)
            setDead(true);
        else if(lastTime%8==0||lastTime%8==1||lastTime%8==2||lastTime%8==3){
            setBounsImage(null);
        }else if(lastTime%8==4||lastTime%8==5||lastTime%8==6||lastTime%8==7){
            switch (bonusType){
                case GUN:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_gun.png"));
                    break;
                case BOOM:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_boom.png"));
                    break;
                case LIFE:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_life.png"));
                    break;
                case STAR:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_star.png"));
                    break;
                case TIME:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_time.png"));
                    break;
                case HELMET:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_helmet.png"));
                    break;
                case SHOVEL:
                    setBounsImage(Toolkit.getDefaultToolkit().getImage("img/bonus_shovel.png"));
                    break;
            }
        }
        lastTime--;
    }
}
