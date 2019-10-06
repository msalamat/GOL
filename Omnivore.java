import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines what kind of life-form a omnivore should be.
 *
 * @author Mohammad Salamat
 */
public class Omnivore extends Lifeform {

    private int hp;

    Omnivore(Cell c) {
        super(c);
        typeID = OMNIVORE_SPECIFIER;
        this.hp = 5;
    }

    public void setColor(){
        color = Color.DEEPSKYBLUE;
    }

    public void act() {

        List<Cell> list = cell.w.listTheNeighbours(cell);
        List<Cell> validNeighbours = new ArrayList<>(); // herbivores + carnivores + plants + empties
        List<Cell> emptyBros = new ArrayList<>(); // empty brothers

        int freeNeighbours = 0;
        int amountOfFood = 0;
        int omnivoreNeighbours = 0;

        for (Cell c : list) {
            if (c.getLifeform() == null || c.getLifeform().typeID.equals(HERBIVORE_SPECIFIER)
                    || c.getLifeform().typeID.equals(CARNIVORE_SPECIFIER) || c.getLifeform().typeID.equals(PLANT_SPECIFIER))
                validNeighbours.add(c);
            if (c.getLifeform() == null) {
                emptyBros.add(c);
                freeNeighbours++;
            } else if (c.getLifeform().typeID.equals(OMNIVORE_SPECIFIER)) {
                omnivoreNeighbours++;
            } else if (c.getLifeform().typeID.equals(HERBIVORE_SPECIFIER) || c.getLifeform().typeID.equals(CARNIVORE_SPECIFIER) || c.getLifeform().typeID.equals(PLANT_SPECIFIER)) {
                amountOfFood++;
            }
        }

        if (listSizeCheck(emptyBros.size())) { // guarding against Dennis's RandomGenerator
            if (omnivoreNeighbours >= 1 && freeNeighbours >= 3 && amountOfFood >= 1) {

                int spotToBirthTo = RandomGenerator.nextNumber(emptyBros.size());

                Cell target = emptyBros.get(spotToBirthTo);
                target.setLifeform(new Omnivore(target));

                list.remove(target);
                validNeighbours.remove(target);
            }
        }

        if (listSizeCheck(validNeighbours.size())) {
            int spotToMoveTo = RandomGenerator.nextNumber(validNeighbours.size());

            Cell target = validNeighbours.get(spotToMoveTo);

            if (target.l == null) {
                this.move(target);
                this.hp--;
            } else if (target.l.typeID.equals(HERBIVORE_SPECIFIER)) {
                this.eat((Herbivore) target.l);
                this.move(target);
                replenish();
            } else if (target.l.typeID.equals(CARNIVORE_SPECIFIER)) {
                this.eat((Carnivore) target.l);
                this.move(target);
                replenish();
            } else if (target.l.typeID.equals(PLANT_SPECIFIER)) {
                this.eat((Plant) target.l);
                this.move(target);
                replenish();
            } else {
                this.hp--;
            }

            if (this.hp == 0) {
                starve();
            }
        } else {
            this.hp--;
            if (this.hp == 0) {
                starve();
            }
        }
    }

    private void move(Cell c) {
        cell.l = null;
        c.l = this;
        cell = c;
    }

    private void eat(Herbivore h) {
        h.cell.l = null;
        replenish();
    }

    private void eat(Carnivore c) {
        c.cell.l = null;
        replenish();
    }

    private void eat(Plant p ) {
        p.cell.l = null;
        replenish();
    }

    private void replenish() {
        this.hp = 5;
    }
}
