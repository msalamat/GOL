import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines what kind of life-form a carnivore should be.
 *
 * @author Mohammad Salamat
 */
public class Carnivore extends Lifeform {

    private int hp;

    Carnivore(Cell c) {
        super(c);
        typeID = CARNIVORE_SPECIFIER;
        replenish();
    }
    public void setColor(){
        color = Color.TOMATO;
    }

    public void act() {

        List<Cell> list = cell.w.listTheNeighbours(cell);
        List<Cell> validNeighbours = new ArrayList<>(); // herbivores + omnivores + empties
        List<Cell> emptyBros = new ArrayList<>();

        int freeNeighbours = 0;
        int amountOfFood = 0;
        int carnivoreNeighbours = 0;

        // querying its neighbours for goodies
        for (Cell c : list) {

            if (c.getLifeform() == null
                    || c.getLifeform().typeID.equals(HERBIVORE_SPECIFIER)
                    || c.getLifeform().typeID.equals(OMNIVORE_SPECIFIER)) {
                validNeighbours.add(c);
            }

            if (c.getLifeform() == null) {
                emptyBros.add(c);
                freeNeighbours++;
            } else if (c.getLifeform().typeID.equals(CARNIVORE_SPECIFIER)) {
                carnivoreNeighbours++;
            } else if (c.getLifeform().typeID.equals(HERBIVORE_SPECIFIER)
                    || c.getLifeform().typeID.equals(OMNIVORE_SPECIFIER)) {
                amountOfFood++;
            }
        }

        if (listSizeCheck(emptyBros.size())) {
            if (carnivoreNeighbours >= 1 && freeNeighbours >= 3 && amountOfFood >= 2) {

                int SpotToBirthTo = RandomGenerator.nextNumber(emptyBros.size());

                Cell target = emptyBros.get(SpotToBirthTo);
                target.setLifeform(new Carnivore(target));

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
            } else if (target.l.typeID.equals(OMNIVORE_SPECIFIER)) {
                this.eat((Omnivore) target.l);
                this.move(target);
                replenish();
            } else {
                this.hp--;
            }
            if (hp == 0) {
                starve();
            }
        } else { // there were no valid neighbours to make a move, so we don't move and lose hp
            this.hp--;
            if(this.hp == 0) {
                starve();
            }
        }
    }

    private void move(Cell c) {
        cell.l = null;
        c.l = this;
        cell = c;
    }
    private void eat(Herbivore p) {
        p.cell.l = null;
        replenish();
    }

    private void eat(Omnivore p) {
        p.cell.l = null;
        replenish();
    }

    private void replenish() {
        this.hp = 5;
    }

}
