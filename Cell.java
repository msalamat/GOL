import javafx.scene.shape.Rectangle;
import java.io.Serializable;

/**
 * Cells can be plants, plant-eaters, or nothing.
 * Cells are represented as a square in the world.
 */
public class Cell implements Serializable {

    World w;
    Lifeform l;
    int x, y;
    transient Rectangle r;

    Cell(int x, int y, World w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    void setRectangle() {
        r = new Rectangle();
    }

    /**
     * Tells you what lifeform this cell is containing.
     * @return this cell's lifeform
     */
    Lifeform getLifeform() {
        return this.l;
    }

    /**
     * Returns this cell in memory.
     * @return this cell
     */
    Cell getCell() {
        return this;
    }

    /**
     * Gives this cell a lifeform
     * @param l the lifeform
     */
    void setLifeform(Lifeform l) {
        this.l = l;
    }

    /**
     * Cleans this cell by removing its lifeform inside.
     */
    public void unsetLifeform() {
        this.l = null;
    }

    /**
     * Gets you this cell's world.
     * @return the world of this cell
     */
    public World getWorld() {
        return this.w;
    }
}
