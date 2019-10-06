import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines what kind of life-form a plant should be.
 *
 * @author Mohammad Salamat
 */
class Plant extends Lifeform {

    private final int PLANT_NEIGHBOUR_REQ = 2;
    private final int EMPTY_NEIGHBOUR_REQ = 3;

    Plant(Cell c) {
        super(c);
        typeID = Lifeform.PLANT_SPECIFIER;
    }

    public void setColor(){
        color = Color.YELLOWGREEN;
    }

    /**
     * This method is used for the plant to make (its only possible) move.
     *
     * If the determined requirements to pollinate is met, the plant seeds and creates
     * a new plant in a random and empty neighbouring cell.
     */
    public void act() {
        ArrayList<Cell> list = cell.w.listTheNeighbours(cell);
        List<Cell> emptyBros = new ArrayList<>();

        int plantNeighbours = 0;

        for(Cell c : list) {
            if(c.l == null)
                emptyBros.add(c);
            else if(c.l.typeID.equals(PLANT_SPECIFIER))
                plantNeighbours++;
        }

        if (emptyBros.size() <= 0) return; // no space to do anything

        // POLLINATE!
        if((plantNeighbours >= PLANT_NEIGHBOUR_REQ) && emptyBros.size() >= EMPTY_NEIGHBOUR_REQ) {
            int random = RandomGenerator.nextNumber(emptyBros.size());
            Cell c = emptyBros.get(random); // randomly choose one
            c.l = new Plant(c);             // and pollinate
        }
    }

    /**
     * This method is for future implementations of the GOL where plants get super powers and are able to move (scary I know)
     */
    public void move(Herbivore pe){}
}
