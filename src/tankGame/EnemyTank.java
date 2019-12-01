package tankGame;

import java.util.Random;
import java.awt.*;

public class EnemyTank extends Tank {
    Random r=new Random();
    int x=r.nextInt(3)*120;
    int y=0;

    int tankType=r.nextInt(3);
    int life;
    int shootflag=r.nextInt(15)+20;
    int moveflag=r.nextInt(15)+10;
    int directflag=r.nextInt(4);
    int bonusFlag=r.nextInt(100);
    private boolean isBounsTank;
    public void lifeLoss(){
        this.life--;
    }
    public boolean isDead(){
        return (this.life==0);
    }
    public void setDead(boolean a){
        life=a?1:0;
    }

    public void setBounsTank(boolean bounsTank) {
        isBounsTank = bounsTank;
    }

    public boolean isBounsTank() {
        return isBounsTank;
    }

    public EnemyTank(int x, int y){
        super();
        setX(x);
        setY(y);
        setEnemy(true);
        switch (directflag){
            case 0:
                this.setPdirect(Direct.D);
                //this.setDierct(Direct.D);
                break;
            case 1:
                this.setPdirect(Direct.U);
                //this.setDierct(Direct.U);
                break;
            case 2:
                this.setPdirect(Direct.L);
                //this.setDierct(Direct.L);
                break;
            case 3:
                this.setPdirect(Direct.R);
                //this.setDierct(Direct.R);
                break;
        }
        switch (tankType){
            case 0:this.setSpeed(3);break;
            case 2:this.setSpeed(3);break;
            case 1:this.setSpeed(6);break;
        }
        if(bonusFlag>=80)
            this.isBounsTank=true;
        switch (tankType){
            case 0:this.life=isBounsTank?2:1;break;
            case 2:this.life=r.nextInt(3)+1;break;
            case 1:this.life=isBounsTank?2:1;break;
        }

    }

    @Override
    public void draw(){
        switch (tankType){
            case 0:
                switch (this.pdirect){
                    case D:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy1DB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy1D.png"));
                        break;
                    case L:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy1LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy1L.png"));
                        break;
                    case R:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy1RB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy1R.png"));
                        break;
                    case U:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy1UB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy1U.png"));
                        break;
                    default:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy1DB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy1D.png"));
                        break;
                }break;
            case 2:
                switch (life){
                    case 3:
                        switch (this.pdirect){
                            case D:
                                setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy2D3LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy2D3L.png"));
                                break;
                            case L:
                                setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy2L3LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy2L3L.png"));
                                break;
                            case R:
                                setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy2R3LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy2R3L.png"));
                                break;
                            case U:
                                setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy2U3LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy2U3L.png"));
                                break;
                            default:
                                setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy2D3LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy2D3L.png"));
                                break;
                        }break;
                    case 2:
                        switch (this.pdirect){
                            case D:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2D2L.png"));
                                break;
                            case L:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2L2L.png"));
                                break;
                            case R:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2R2L.png"));
                                break;
                            case U:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2U2L.png"));
                                break;
                            default:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2D2L.png"));
                                break;
                        }break;
                    case 1:
                        switch (this.pdirect){
                            case D:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2D1L.png"));
                                break;
                            case L:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2L1L.png"));
                                break;
                            case R:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2R1L.png"));
                                break;
                            case U:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2U1L.png"));
                                break;
                            default:
                                setTankImage(Toolkit.getDefaultToolkit().getImage("img/enemy2D1L.png"));
                                break;
                        }break;
                }break;
            case 1:
                switch (this.pdirect){
                    case D:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy3DB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy3D.png"));
                        break;
                    case L:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy3LB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy3L.png"));
                        break;
                    case R:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy3RB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy3R.png"));
                        break;
                    case U:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy3UB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy3U.png"));
                        break;
                    default:
                        setTankImage(isBounsTank?Toolkit.getDefaultToolkit().getImage("img/enemy3DB.png"):Toolkit.getDefaultToolkit().getImage("img/enemy3D.png"));
                        break;
                }break;
        }
    }

    @Override
    public void shoot(){
        bullets.add(new Bullet(getX()+8,getY()+8,pdirect,isEnemy()));
    }

    @Override
    public void move(){
        int speed=getSpeed();
        int height=getHeight();
        int width=getWidth();
        int x=getX();
        int y=getY();
        switch (pdirect) {
            case U:
                if (y - speed >= 0)
                    setY(y - speed);
                break;
            case D:
                if (y+speed<=RunGame.FrameWidth-3*height)
                    setY(y + speed);
                break;
            case L:
                if (x - speed >= 0)
                    setX(x - speed);
                break;
            case R:
                if (x+speed<=RunGame.FrameWidth-2*width)
                    setX(x + speed);
                break;
            default:// do nothing
        }

        moveflag--;
        if(moveflag==0){
            moveflag=r.nextInt(10)+10;
            directflag=r.nextInt(4);
            switch (directflag){
                case 0:this.setPdirect(Direct.D);break;
                case 1:this.setPdirect(Direct.U);break;
                case 2:this.setPdirect(Direct.L);break;
                case 3:this.setPdirect(Direct.R);break;
            }
        }
        shootflag--;
        if(shootflag==0){
            shoot();
            shootflag=r.nextInt(50)+20;
        }
    }
    public void changeDirect(){
        moveflag--;
        shootflag--;
        if(moveflag==0){
            moveflag=r.nextInt(15)+10;
            directflag=r.nextInt(4);
            switch (directflag){
                case 0:this.setPdirect(Direct.D);break;
                case 1:this.setPdirect(Direct.U);break;
                case 2:this.setPdirect(Direct.L);break;
                case 3:this.setPdirect(Direct.R);break;
            }
        }
        if(shootflag==0){
            shoot();
            shootflag=r.nextInt(15)+10;
        }

    }
    @Override
    public Rectangle getCurrentPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }
    @Override
    public Rectangle getNextPosition(){
        switch(pdirect){
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
}
