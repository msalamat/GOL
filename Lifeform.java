import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Abstract class for lifeforms.
 */
public abstract class Lifeform implements Serializable {

    static final String PLANT_SPECIFIER     = "0001";
    static final String HERBIVORE_SPECIFIER = "0002";
    static final String CARNIVORE_SPECIFIER = "0003";
    static final String OMNIVORE_SPECIFIER  = "0004";

    Cell cell;
    transient Color color;

    String typeID;

    Lifeform(Cell c) {
        this.cell = c;
        setColor();
    }

    Color getColor() {
        return color;
    }

//    public abstract boolean isHerbivore();
//
//    public abstract boolean isCarnivore();
//
//    public abstract boolean isOmnivore();
//
//    public abstract boolean isPlant();

    public abstract void setColor();

    public abstract void act();

    void starve() {
        this.cell.l = null;
    }

    boolean listSizeCheck(int size1) {
        return size1 > 0;
    }
}
