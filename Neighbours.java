/**
 * This class holds information about the neighbours of a cell in the grid.
 * From -1 to 1, we can go through all our immediate neighbours.
 */
public enum Neighbours {

    /*
         ------------------
        |[NW]    [N]   [NE]|
        |                  |
        |[W]     Cell   [E]|
        |                  |
        |[SW]    [S]   [SE]|
         ------------------
     */

    NORTHWEST(-1, -1),
    NORTH(0, -1),
    NORTHEAST(1, -1),
    WEST(-1, 0),
    EAST(1,0),
    SOUTHWEST(-1,1),
    SOUTH(0, 1),
    SOUTHEAST(1,1);

    // the direction offsets
    final int dx;
    final int dy;

    Neighbours(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
