package tankGame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class MyTank extends Tank {
    Direct direct = Direct.STOP;
    Direct pdirect = Direct.U;
    Music shootMusic=new Music(new File("music/shoot.mp3"));
    private int rank=0;
    private int life=3;
    private int bulletSize=1;
    private boolean isUnbeatable=false;
    private boolean isCreating=false;
    boolean isPause=false;
    public int getRank() {
        return rank;
    }

    public void setUnbeatable(boolean unbeatable) {
        isUnbeatable = unbeatable;
    }

    public boolean isUnbeatable() {
        return isUnbeatable;
    }

    public boolean isCreating() {
        return isCreating;
    }

    public int getLife() {
        return life;
    }

    @Override
    public boolean isDead() {
        return life==0;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void lifeUP(){
        life++;
    }
    public void lifeLoss(){
        if(life>=1)life--;
    }

    public void setCreating(boolean creating) {
        isCreating = creating;
    }

    public void rankUP(){
        if(rank<3)rank++;
        if(rank>=2)setBulletSize(2);
    }
    public void rankDOWN(){
        if(rank>0)rank--;
        if(rank<=1)setBulletSize(1);
    }
    public boolean isFather(){
        return rank==3;
    }
    public Controller c = new Controller();

    public int getBulletSize() {
        return bulletSize;
    }

    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
    }

    public MyTank(){
        super();
    }
    public MyTank(int x,int y,boolean isEnemy){
        super(x,y,isEnemy);
    }
    @Override
    public void draw(){
        if(isDead()){
            setTankImage(null);
            return;
        }
        switch (rank) {
            case 3:
                switch (pdirect) {
                    case D:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankDr3.png"));
                        break;
                    case L:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankLr3.png"));
                        break;
                    case R:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankRr3.png"));
                        break;
                    case U:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr3.png"));
                        break;
                    default:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr3.png"));
                        break;
                }
                break;
            case 2:
                switch (pdirect) {
                    case D:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankDr2.png"));
                        break;
                    case L:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankLr2.png"));
                        break;
                    case R:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankRr2.png"));
                        break;
                    case U:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr2.png"));
                        break;
                    default:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr2.png"));
                        break;
                }
                break;
            case 1:
                switch (pdirect) {
                    case D:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankDr1.png"));
                        break;
                    case L:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankLr1.png"));
                        break;
                    case R:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankRr1.png"));
                        break;
                    case U:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr1.png"));
                        break;
                    default:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr1.png"));
                        break;
                }
                break;
            case 0:
                switch (pdirect) {
                    case D:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankDr0.png"));
                        break;
                    case L:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankLr0.png"));
                        break;
                    case R:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankRr0.png"));
                        break;
                    case U:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr0.png"));
                        break;
                    default:
                        setTankImage(Toolkit.getDefaultToolkit().getImage("img/p1tankUr0.png"));
                        break;
                }
                break;
        }
    }
    @Override
    public void move(){
        int x=getX();
        int y=getY();
        int height=getHeight();
        int width=getWidth();
        int speed=getSpeed();
        if(direct==Direct.U){
            if(y-speed>=0)setY(y-speed);
        }
        else if(direct==Direct.D){
            if(y+speed<=RunGame.FrameWidth-2*height)setY(y+speed);
        }
        else if(direct==Direct.L){
            if(x-speed>=0)setX(x-speed);
        }
        else if(direct==Direct.R){
            if(x+speed<=RunGame.FrameWidth-2*width)setX(x+speed);
        }
    }
    @Override
    public void shoot(){
        if(this.getBullets().size()<getBulletSize()&&!isDead()){
            switch (rank){
                case 3:
                    bullets.add(new Bullet(getX()+8,getY()+8,pdirect,isEnemy(),13,true));
                    System.out.println("double quick super shoot");
                    break;
                case 2:
                    bullets.add(new Bullet(getX()+8,getY()+8,pdirect,isEnemy(),13,false));
                    System.out.println("double quick shoot");
                    break;
                case 1:
                    bullets.add(new Bullet(getX()+8,getY()+8,pdirect,isEnemy(),13,false));
                    System.out.println("quick shoot");
                    break;
                case 0:
                    bullets.add(new Bullet(getX()+8,getY()+8,pdirect,isEnemy()));
                    System.out.println("common shoot");
                    break;
            }
            new Thread(shootMusic).start();
        }
    }
    @Override
    public Rectangle getCurrentPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());

    }
    @Override
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
    @Override
    public boolean isCollisionBlocks(Map m){
        for(int i=0;i<m.getMap().size();i++){
            if((m.getMap().get(i).getBlocktype()==BLockstype.IRON||m.getMap().get(i).getBlocktype()==BLockstype.COB||m.getMap().get(i).getBlocktype()==BLockstype.SEA||m.getMap().get(i).getBlocktype()==BLockstype.EAGLE1||m.getMap().get(i).getBlocktype()==BLockstype.EAGLE2||m.getMap().get(i).getBlocktype()==BLockstype.EAGLE3||m.getMap().get(i).getBlocktype()==BLockstype.EAGLE4||m.getMap().get(i).getBlocktype()==BLockstype.DEADEAGLE1||m.getMap().get(i).getBlocktype()==BLockstype.DEADEAGLE2||m.getMap().get(i).getBlocktype()==BLockstype.DEADEAGLE3||m.getMap().get(i).getBlocktype()==BLockstype.DEADEAGLE4)&&this.getNextPosition().intersects(m.getMap().get(i).getPosition())){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isCollisionTank(Tank tank){
        if(tank.getNextPosition().intersects(getNextPosition())){
            return true;
        }
        return false;
    }
    public boolean isCollisionBonus(Bonus b){
        if(this.getCurrentPosition().intersects(b.getCurrentPosition())){
            b.setDead(true);
            return true;
        }

        return false;
    }
    public class Controller extends KeyAdapter{

        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int d = e.getKeyCode();
            if (d == KeyEvent.VK_SPACE) shoot();
            if (d == KeyEvent.VK_W) pdirect = direct = Direct.U;
            if (d == KeyEvent.VK_S) pdirect = direct = Direct.D;
            if (d == KeyEvent.VK_A) pdirect = direct = Direct.L;
            if (d == KeyEvent.VK_D) pdirect = direct = Direct.R;
            if(d==KeyEvent.VK_ESCAPE)isPause=!isPause;
        }
           /* switch (d){
                case KeyEvent.VK_SPACE:
                    shoot();
                    break;
                case KeyEvent.VK_W:
                    pdirect=direct=Direct.U;
                    break;
                case KeyEvent.VK_S:
                    pdirect=direct=Direct.D;
                    break;
                case KeyEvent.VK_A:
                    pdirect=direct=Direct.L;
                    break;
                case KeyEvent.VK_D:
                    pdirect=direct=Direct.R;
                    break;
                default:
                    direct=Direct.STOP;
                    break;
            }
        }*/

        public void keyReleased(KeyEvent e){
            super.keyReleased(e);
            int d=e.getKeyCode();
            if(d==KeyEvent.VK_W||d==KeyEvent.VK_A||d==KeyEvent.VK_S||d==KeyEvent.VK_D) {
                direct=Direct.STOP;
            }
        }
    }
}

