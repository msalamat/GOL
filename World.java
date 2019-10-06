import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds the grid that consists of the universe in this game.
 *
 * @author Mohammad Salamat
 */
class World implements Serializable {

    private int width, height;
    Cell[][] grid;

    World(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        genesis();
    }

    /**
     * On the first day, God said "Fill up the grid!".
     */
    private void genesis() {
        //RandomGenerator.reset();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {

                Cell c = new Cell(i, j,this);
                this.grid[i][j] = c;

                int randomValue = RandomGenerator.nextNumber(99);

                if (randomValue >= 80) {
                    this.grid[i][j].l = new Herbivore(c);
                } else if (randomValue >= 60) {
                    this.grid[i][j].l = new Plant(c);
                } else if (randomValue >= 50) {
                    this.grid[i][j].l = new Carnivore(c);
                } else if (randomValue >= 45) {
                    this.grid[i][j].l = new Omnivore(c);
                }
            }
        }
    }

    /**
     * Gets a list of the neighbouring cells of a cell.
     *
     * @param c - A CELL!
     * @return a list (of Cells) of valid neighbours a cell has
     */
    ArrayList<Cell> listTheNeighbours(Cell c) {

        ArrayList<Cell> list = new ArrayList<>();

        for (Neighbours neighbour : Neighbours.values()) {
            if (neighbourValid(c.x + neighbour.dx, c.y + neighbour.dy)) {
                list.add(this.getCell(c.x + neighbour.dx, c.y + neighbour.dy));
            }
        }

        return list;
    }

    /**
     * Given a pair of coordinates, checks to see whether it would be in bounds of the grid.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if the neighbours are in bounds, false otherwise
     */
    private boolean neighbourValid(int x, int y) {
        return x >= 0 && y >= 0 // neg boundaries is a no-no
                && x < this.width && y < this.height; // beyond the boundaries is also a no-no
    }

    /**
     *
     * @return a list of all the lifeforms from the world
     */
    ArrayList<Lifeform> getLifeformsFromWorld() {

        ArrayList<Lifeform> list = new ArrayList<Lifeform>(); // list of non-empty cells, i.e. lifeforms 'alive?'

        for (int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++) {
                Cell cell = this.grid[i][j];

                if(cell.getLifeform() != null) {
                    list.add(cell.getLifeform());
                }
            }

        return list;
    }

    /**
     * This method peeks inside the cells and updates the colour of the Rectangle inside each cell
     */
    void render() {
        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                Cell cell = getCell(i,j);
                if(cell.getLifeform() == null) {
                    cell.r.setFill(Color.AZURE);
                } else {
                    cell.r.setFill(cell.getLifeform().getColor());
                }
            }
        }
    }

    /**
     *
     * This method is useless for this version of the game, but I like it so it stays.
     *
     * @param c - the cell in question
     * @return the number of valid neighbours the cell in question has
     */
    int countNeighbours(Cell c) {

        int count = 0;

        for (Neighbours neighbour : Neighbours.values())
            // if the neighbouring cells are valid cells
            if (neighbourValid(c.x + neighbour.dx, c.y + neighbour.dy)) count++;

        return count;
    }

    /**
     * Gets a specific cell.
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the cell in question
     */
    Cell getCell(int x,int y ) {
        return this.grid[x][y];
    }

    /**
     * Gets the width of the world's grid.
     * @return the width of the grid
     */
    int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the world's grid
     * @return the height of the grid
     */
    int getHeight() {
        return this.height;
    }
}

    /**
//     * Gets you the plants
//     * @return a list of the plants in the world
//     */
//    ArrayList<Lifeform> getSpecificLifeformFromWorld(Lifeform l, boolean type) {
//
//        ArrayList<Lifeform> list = new ArrayList<Lifeform>(); // list of non-empty cells, i.e. lifeforms 'alive?'
//
//        for (int i = 0; i < this.width; i++)
//            for (int j = 0; j < this.height; j++) {
//                Cell cell = this.grid[i][j];
//
//                if(cell.l != null && cell.l.typeID.equals())) {
//                    list.add(cell.getLifeform());
//                }
//            }
//
//        return list;
//    }
//
//    /**
//     * Gets you the plant-eaters
//     * @return a list of the plant-eaters in the world
//     */
//    ArrayList<Lifeform> getHerbivoresFromWorld() {
//
//        ArrayList<Lifeform> list = new ArrayList<Lifeform>(); // list of non-empty cells, i.e. lifeforms 'alive?'
//
//        for (int i = 0; i < this.width; i++)
//            for (int j = 0; j < this.height; j++) {
//                Cell cell = this.grid[i][j];
//
//                if(cell.getLifeform() != null && cell.getLifeform().isHerbivore()) {
//                    list.add(cell.getLifeform());
//                }
//            }
//
//        return list;
//    }
//
//    /**
//     * Gets you the plants
//     * @return a list of the plants in the world
//     */
//    ArrayList<Lifeform> getPlantsFromWorld() {
//
//        ArrayList<Lifeform> list = new ArrayList<Lifeform>(); // list of non-empty cells, i.e. lifeforms 'alive?'
//
//        for (int i = 0; i < this.width; i++)
//            for (int j = 0; j < this.height; j++) {
//                Cell cell = this.grid[i][j];
//
//                if(cell.getLifeform() != null && cell.getLifeform().isPlant()) {
//                    list.add(cell.getLifeform());
//                }
//            }
//
//        return list;
//    }
//
//    /**
//     * Gets you the Carnivores
//     * @return a list of the carnivores in the world
//     */
//    ArrayList<Lifeform> getCarnivoresFromWorld() {
//
//        ArrayList<Lifeform> list = new ArrayList<Lifeform>(); // list of non-empty cells, i.e. lifeforms 'alive?'
//
//        for (int i = 0; i < this.width; i++)
//            for (int j = 0; j < this.height; j++) {
//                Cell cell = this.grid[i][j];
//
//                if(cell.getLifeform() != null && cell.getLifeform().isCarnivore()) {
//                    list.add(cell.getLifeform());
//                }
//            }
//
//        return list;
//    }
//
//    /**
//     * Gets you the plants
//     * @return a list of the omnivores in the world
//     */
//    ArrayList<Lifeform> getOmnivoresFromWorld() {
//
//        ArrayList<Lifeform> list = new ArrayList<Lifeform>(); // list of non-empty cells, i.e. lifeforms 'alive?'
//
//        for (int i = 0; i < this.width; i++)
//            for (int j = 0; j < this.height; j++) {
//                Cell cell = this.grid[i][j];
//
//                if(cell.getLifeform() != null && cell.getLifeform().isOmnivore()) {
//                    list.add(cell.getLifeform());
//                }
//            }
//
//        return list;
//    }
