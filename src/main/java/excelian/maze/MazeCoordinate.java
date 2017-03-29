package excelian.maze;

import com.google.common.base.Preconditions;

public class MazeCoordinate {
    private final int x;

    private final int y;

    public MazeCoordinate(int x, int y) {
        Preconditions.checkArgument(x >= 0 && y >= 0, "Coordinate should not be negative!");

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
