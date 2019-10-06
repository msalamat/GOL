//import javafx.scene.paint.Color;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * This class defines what kind of life-form a plant-eater should be.
// *
// * @author Mohammad Salamat
// */
//class Herbivore extends Lifeform {
//
//    private int hp;
//
//    Herbivore(Cell c) {
//        super(c);
//        color = Color.YELLOW;
//        type = Type.Herbivore;
//        replenish();
//    }
//
//    public void act() {
//
//        List<Cell> list = cell.w.listTheNeighbours(cell);
//
//        List<Cell> validNeighbours = new ArrayList<>();
//
//
//        for(Cell c : list)
//            if(c.getLifeform() == null || c.getLifeform().isPlant())
//                validNeighbours.add(c);
//
//
//        int randomSpotToMoveTo = RandomGenerator.nextNumber(validNeighbours.size());
//
//        Cell target = validNeighbours.get(randomSpotToMoveTo);
//
//        if(target.l == null) { // take the empty spot
//            this.move(target);
//            hp--;
//        } else if (target.l.isPlant()) {
//            this.eat( (Plant) target.l); // eat da plant
//            this.move(target);           //
//        } else if (target.l.isHerbivore()){ // can't move and lose hp
//            hp--;
//        }
//
//        if(hp == 0) {
//            starve();
//        }
//    }
//
//    private void starve() {
//        this.cell.l = null;
//    }
//
//    private void eat(Plant p) {
//        p.cell.l = null;
//        replenish();
//    }
//
//    private void move(Cell c) {
//        cell.l = null;
//        c.l = this;
//        cell = c;
//    }
//
//    private void replenish() {
//        this.hp = 5;
//    }
//
//    public boolean isPlant() {
//        return false;
//    }
//
//    public boolean isHerbivore() {
//        return true;
//    }
//
//}
