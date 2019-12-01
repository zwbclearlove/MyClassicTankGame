package tankGame;

import java.awt.*;

public class NewTankCreating extends DynamicEffect {
    private int keyFrame=19;

    NewTankCreating(int x,int y){
        super(x,y,20,20);
    }

    public int getKeyFrame() {
        return keyFrame;
    }

    public void setKeyFrame(int keyFrame) {
        this.keyFrame = keyFrame;
    }

    @Override
    public void draw() {
        switch (this.keyFrame) {
            case 1: case 7: case 13: case 19:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/tank_creating1.png"));
                break;
            case 2: case 6: case 8: case 12: case 14: case 18:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/tank_creating2.png"));
                break;
            case 3: case 5: case 9: case 11: case 15: case 17:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/tank_creating3.png"));
                break;
            case 4: case 10: case 16:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/tank_creating4.png"));
                break;
            default:
                setDead(true);
                setDEImage(null);
                break;
        }
        if(keyFrame>=1)keyFrame--;
        System.out.println("newtankcreating" + keyFrame);
    }
}
