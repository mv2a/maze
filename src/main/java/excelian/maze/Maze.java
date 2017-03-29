package excelian.maze;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class Maze {

    private String[] mazeData;

    private final int dimensionX;

    private final int dimensionY;

    private static final String LINEBREAK = "\n";

    public Maze(String mazeStr) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mazeStr), "Maze can not be empty!");

        this.mazeData = mazeStr.split(LINEBREAK);
        dimensionX = mazeData[0].length();
        dimensionY = mazeData.length;
    }

    public int getNumberOfWalls() {
        return 0;
    }

    public int getNumberOfEmpytSpaces() {
        return 0;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }
}
