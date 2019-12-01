package tankGame;

import java.awt.*;
import java.io.File;

public class Bullet {
    private int x;
    private int y;
    private static int width = 4;
    private static int height = 4;

    private int speed = 8;
    private Direct direct;
    private Image bulletImage;
    private boolean isDead=false;
    private boolean isFromEnemy;
    private boolean isSuperBullet;
    Music hit_cobMusic=new Music(new File("music/hit_cob.mp3"));
    Music hit_ironMusic=new Music(new File("music/hit_tank.mp3"));
    Music hit_eagleMusic=new Music(new File("music/eagle_die.mp3"));
    Music hit_tankMusic=new Music(new File("music/hit_iron.mp3"));
    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFromEnemy() {
        return isFromEnemy;
    }

    public Direct getDirect() {
        return direct;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public void setFromEnemy(boolean fromEnemy) {
        isFromEnemy = fromEnemy;
    }

    public Bullet(int x, int y, Direct direct, boolean isFromEnemy){
        this.x=x;
        this.y=y;
        this.direct=direct;
        this.isFromEnemy=isFromEnemy;
    }
    public Bullet(int x, int y, Direct direct, boolean isFromEnemy,int speed,boolean isSuperBullet){
        this.x=x;
        this.y=y;
        this.direct=direct;
        this.isFromEnemy=isFromEnemy;
        this.speed=speed;
        this.isSuperBullet=isSuperBullet;
    }
    public void draw(){
        switch (direct){
            case U:
                setBulletImage(Toolkit.getDefaultToolkit().getImage("img/bulletU.png"));
                break;
            case R:
                setBulletImage(Toolkit.getDefaultToolkit().getImage("img/bulletR.png"));
                break;
            case L:
                setBulletImage(Toolkit.getDefaultToolkit().getImage("img/bulletL.png"));
                break;
            case D:
                setBulletImage(Toolkit.getDefaultToolkit().getImage("img/bulletD.png"));
                break;
            default:
                break;
        }

    }
    public void move(){
        if(this.x<0||this.x>RunGame.FrameWidth||this.y<0||this.y>RunGame.FrameHeight)
            setDead(true);
        else{
            switch (direct){
                case D:setY(y+speed);break;
                case L:setX(x-speed);break;
                case R:setX(x+speed);break;
                case U:setY(y-speed);break;
                default:setY(y-speed);break;
            }
        }
    }

    public Rectangle getCurrentPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }
    public Rectangle getNextPosition(){
        switch(direct){
            case U:
                return new Rectangle(getX(),getY()-getSpeed(),getWidth(),getHeight());
            case R:
                return new Rectangle(getX()+getSpeed(),getY(),getWidth(),getHeight());
            case L:
                return new Rectangle(getX()-getSpeed(),getY(),getWidth(),getHeight());
            case D:
                return new Rectangle(getX(),getY()+getSpeed(),getWidth(),getHeight());
            default:
                return new Rectangle(getX(),getY(),getWidth(),getHeight());
        }
    }
    public boolean isCollisonTank(Tank tank){
        if(tank.getCurrentPosition().intersects(getCurrentPosition())&&(tank.isEnemy()!=isFromEnemy())){
            if(tank instanceof EnemyTank){
                if(((EnemyTank) tank).isBounsTank())
                    ((EnemyTank) tank).setBounsTank(false);
                if(((EnemyTank) tank).life>=2)
                    new Thread(hit_tankMusic).start();
                ((EnemyTank) tank).lifeLoss();
                setDead(true);
                return true;
            }else if(tank instanceof MyTank){

                setDead(true);
                return true;
            }
        }
        return false;
    }
    public boolean isCollisionBullet(Bullet b){

        Rectangle c,d;
        c=b.getNextPosition();
        d=this.getNextPosition();
        c.x=c.x-8;
        c.y=c.y-8;
        d.x=d.x-8;
        d.y=d.y-8;
        c.height=20;
        d.height=20;
        c.width=20;
        d.width=20;
        if(c.intersects(d)) {
            b.setDead(true);
            this.setDead(true);
            return true;
        }
        return false;
    }

    public boolean isCollisionBlocks(Map m){
        boolean state=false;
        for(int i=0;i<m.getMap().size();i++){
            Blocks b=m.getMap().get(i);
            if(this.getNextPosition().intersects(b.getPosition())){
                switch(b.getBlocktype()){
                    case COB:
                        this.setDead(true);
                        m.getMap().set(i,new Blocks(b.getX(),b.getY(),BLockstype.VOID));
                        new Thread(hit_cobMusic).start();
                        state=true;
                        break;
                    case IRON:
                        this.setDead(true);
                        if(this.isSuperBullet==true)
                            m.getMap().set(i,new Blocks(b.getX(),b.getY(),BLockstype.VOID));
                        new Thread(hit_ironMusic).start();
                        state=true;
                        break;
                    case EAGLE1:case EAGLE2:case EAGLE3:case EAGLE4:
                        this.setDead(true);
                        m.eagleDead();
                        new Thread(hit_eagleMusic).start();
                        state=true;
                        break;
                    case DEADEAGLE1:case DEADEAGLE2:case DEADEAGLE3:case DEADEAGLE4: case SEA: case FOREST: case VOID:
                        state=false;
                        break;
                }
            }
        }
        return state;
    }
}
