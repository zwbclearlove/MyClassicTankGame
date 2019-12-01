package tankGame;

import java.awt.*;

public class Blocks {
    private int x,y,width=10,height=10;
    private boolean isDead;
    private BLockstype blocktype;
    private boolean canBeAcrossed;
    private boolean canBeRemoved;
    private Image blocksImage;

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
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

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean alive) {
        isDead = alive;
    }

    public boolean isCanBeAcrossed() {
        return canBeAcrossed;
    }

    public boolean isCanBeRemoved() {
        return canBeRemoved;
    }

    public BLockstype getBlocktype() {
        return blocktype;
    }

    public void setBlocktype(BLockstype blocktype) {
        this.blocktype = blocktype;
    }

    public Image getBlocksImage() {
        return blocksImage;
    }

    public void setBlocksImage(Image blocksImage) {
        this.blocksImage = blocksImage;
    }

    public void setCanBeAcrossed(boolean canBeAcrossed) {
        this.canBeAcrossed = canBeAcrossed;
    }

    public void setCanBeRemoved(boolean canBeRemoved) {
        this.canBeRemoved = canBeRemoved;
    }

    public Blocks(){ }
    public Blocks(int x, int y, BLockstype type) {
        this.x=x;
        this.y=y;
        this.blocktype=type;
    }
    public void draw(){
        switch (this.blocktype){
            case BLACK:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/small_void.png"));
                break;
            case GREY:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/blocks_grey.png"));
                break;
            case COB:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/small_cob.png"));
                break;
            case SEA:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/small_sea.png"));
                break;
            case IRON:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/small_iron.png"));
                break;
            case FOREST:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/small_forest.png"));
                break;
            case EAGLE1:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_eagle1.png"));
                break;
            case EAGLE2:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_eagle2.png"));
                break;
            case EAGLE3:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_eagle3.png"));
                break;
            case EAGLE4:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_eagle4.png"));
                break;
            case DEADEAGLE1:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_deadeagle1.png"));
                break;
            case DEADEAGLE2:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_deadeagle2.png"));
                break;
            case DEADEAGLE3:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_deadeagle3.png"));
                break;
            case DEADEAGLE4:
                setBlocksImage(Toolkit.getDefaultToolkit().getImage("img/block_deadeagle4.png"));
                break;

        }
    }
    public Rectangle getPosition(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

}
