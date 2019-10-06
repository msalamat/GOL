import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines what kind of life-form a plant-eater should be.
 *
 * @author Mohammad Salamat
 */
class Herbivore extends Lifeform {

    private int hp;

    Herbivore(Cell c) {
        super(c);
        typeID = Lifeform.HERBIVORE_SPECIFIER;
        replenish();
    }

    public void setColor(){
        color = Color.GOLD;
    }

    public void act() {
        List<Cell> list = cell.w.listTheNeighbours(cell);
        List<Cell> emptyBros = new ArrayList<>();

        int freeNeighbours = 0;
        int amountOfFood = 0;
        int herbivoreNeighburs = 0;

        // querying my neighbours
        for (Cell c : list) {
            if (c.getLifeform() == null) {
                emptyBros.add(c);
                freeNeighbours++;
            } else if (c.getLifeform().typeID.equals(HERBIVORE_SPECIFIER)) {
                herbivoreNeighburs++;
            } else if (c.getLifeform().typeID.equals(PLANT_SPECIFIER)) {
                amountOfFood++;
            }
        }

        if (listSizeCheck(emptyBros.size())) {
            if (herbivoreNeighburs >= 1 && freeNeighbours >= 2 && amountOfFood >= 2) {
                // do it like they do in the discovery channel
                int SpotToBirthTo = RandomGenerator.nextNumber(emptyBros.size());

                Cell target = emptyBros.get(SpotToBirthTo);
                target.setLifeform(new Herbivore(target));

                list.remove(target); // hank hank hank
                emptyBros.remove(target); // hank hank hank
            }
        }

        List<Cell> food = new ArrayList<>();

        for (Cell c : list) {
            if (c.getLifeform() != null && c.getLifeform().typeID.equals(PLANT_SPECIFIER)) {
                food.add(c);
            }
        }

        if (listSizeCheck(food.size())) {
            int randomSpotToMoveTo = RandomGenerator.nextNumber(food.size());
            Cell target = food.get(randomSpotToMoveTo);
            this.eat((Plant) target.l);
            replenish();
            this.move(target);
        } else if (listSizeCheck(emptyBros.size())) {
            int randomSpotToMoveTo = RandomGenerator.nextNumber(emptyBros.size());
            Cell target = emptyBros.get(randomSpotToMoveTo);
            this.move(target);
            this.hp--;
        } else {
            this.hp--;
        }

        if (this.hp == 0) {
            starve();
        }

    }

    private void eat(Plant p) {
        p.cell.l = null;
        replenish();
    }

    private void move(Cell c) {
        cell.l = null;
        c.l = this;
        cell = c;
    }

    private void replenish() {
        this.hp = 5;
    }
}
