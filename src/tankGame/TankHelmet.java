package tankGame;

import java.awt.*;

public class TankHelmet extends DynamicEffect{

    private int keyFrame=400;
    TankHelmet(int x,int y,int keyFrame){
        super(x,y,20,20);
        this.keyFrame=keyFrame;
    }

    public void setKeyFrame(int keyFrame) {
        this.keyFrame = keyFrame;
    }

    public int getKeyFrame() {
        return keyFrame;
    }

    @Override
    void draw(){
        if(keyFrame==0)
            setDEImage(null);
        else if(keyFrame%4==1||keyFrame%4==0){
            setDEImage(Toolkit.getDefaultToolkit().getImage("img/tank_helmet1.png"));
            keyFrame--;
        }
        else if(keyFrame%4==2||keyFrame%4==3) {
            setDEImage(Toolkit.getDefaultToolkit().getImage("img/tank_helmet2.png"));
            keyFrame--;
        }
    }

}
