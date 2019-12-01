package tankGame;

import java.awt.*;
import java.awt.event.*;

public class FriendTank extends Tank {
    Direct direct = Direct.STOP;
    Direct pdirect = Direct.U;

    public Controller c = new Controller();

    public FriendTank(int x,int y,boolean isEnemy){
        super(x,y,isEnemy);
    }
    @Override
    public void draw(){
        switch (pdirect){
            case D:
                setTankImage(Toolkit.getDefaultToolkit().getImage("img/p2tankD.gif"));
                break;
            case L:
                setTankImage(Toolkit.getDefaultToolkit().getImage("img/p2tankL.gif"));
                break;
            case R:
                setTankImage(Toolkit.getDefaultToolkit().getImage("img/p2tankR.gif"));
                break;
            case U:
                setTankImage(Toolkit.getDefaultToolkit().getImage("img/p2tankU.gif"));
                break;
            default:
                setTankImage(Toolkit.getDefaultToolkit().getImage("img/p2tankU.gif"));
                break;
        }

    }
    @Override
    public void shoot(){
        switch (direct){
            case U:

            case R:
            case L:
            case D:
            default:
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
            if(y+speed<=RunGame.FrameWidth-3*height)setY(y+speed);
        }
        else if(direct==Direct.L){
            if(x-speed>=0)setX(x-speed);
        }
        else if(direct==Direct.R){
            if(x+speed<=RunGame.FrameWidth-2*width)setX(x+speed);
        }
        else{ }
    }
    @Override
    public Rectangle getCurrentPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }
    @Override
    public Rectangle getNextPosition(){
        return null;
    }
    public class Controller extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            super.keyPressed(e);
            int d = e.getKeyCode();
            switch (d){
                case KeyEvent.VK_UP:
                    pdirect=direct=Direct.U;
                    break;
                case KeyEvent.VK_DOWN:
                    pdirect=direct=Direct.D;
                    break;
                case KeyEvent.VK_LEFT:
                    pdirect=direct=Direct.L;
                    break;
                case KeyEvent.VK_RIGHT:
                    pdirect=direct=Direct.R;
                    break;
                default:
                    direct=Direct.STOP;
                    break;
            }
        }

        public void keyReleased(KeyEvent e){
            super.keyReleased(e);
            int d=e.getKeyCode();
            if(d==KeyEvent.VK_UP||d==KeyEvent.VK_DOWN||d==KeyEvent.VK_LEFT||d==KeyEvent.VK_RIGHT) {
                direct=Direct.STOP;
            }
        }
    }
    @Override
    public boolean isCollisionBlocks(Map m){
        for(int i=0;i<m.getMap().size();i++){
            if(this.getNextPosition().intersects(m.getMap().get(i).getPosition())){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isCollisionTank(Tank tank){
        if(tank.getCurrentPosition().intersects(getCurrentPosition())){
            return true;
        }
        return false;
    }
}
