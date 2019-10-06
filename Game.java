import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class controls the turns of an instance of the world, and also has the ability to
 * create the contents of a world.
 *
 * @author Mohammad Salamat
 */
class Game implements Serializable {

    private static final int GRID_SIZE_X_1 = 50;
    private static final int GRID_SIZE_Y_1 = 50;
    int turn = 0;

    private World world;

    transient GridPane gp;

    Game() {
        this.world = new World(GRID_SIZE_X_1, GRID_SIZE_Y_1);
    }

    /**
     * Creates our cells, our lovely cells, and sets them on our lovely grid.
     */
    void generateBoard() {
        gp = new GridPane();
        for(int i = 0; i < getWorld().getWidth(); i++) {
            for(int j = 0; j < getWorld().getHeight(); j++) {



                Cell cell = getWorld().getCell(i,j);
                cell.setRectangle();
                cell.r.setWidth(15);
                cell.r.setHeight(15);

                if(cell.getLifeform() != null){
                    cell.getLifeform().setColor();
                }


                getWorld().grid[i][j].r.setStroke(Color.BLACK);

                this.gp.add(cell.r, i, j);
            }
        }
        world.render(); // julian won't let this down
    }

    /**
     * Since we can have more than one game, this method gives us
     * the current game's World
     *
     * @return the current game's world
     */
    private World getWorld() {
        return this.world;
    }

    /**
     * Computes and produces the next generation of the lifeforms.
     */
    void nextState() { // so we can have multiple worlds and in the multiple worlds have an ability to make turns
        System.out.println("current turn: " + ++turn);
        ArrayList<Lifeform> listOfLifeforms = world.getLifeformsFromWorld();
        //RandomGenerator.reset();
        for (Lifeform listOfLifeform : listOfLifeforms)
            listOfLifeform.act();

        world.render();
    }
}


