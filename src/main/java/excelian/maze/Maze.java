package excelian.maze;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class Maze {

    private String[] mazeData;

    private final int dimensionX;

    private final int dimensionY;

    private static final String LINEBREAK = "\n";

    private boolean stringContainsExactlyOneOfGivenChar(String str, char c) {
        return str.chars().filter(ch -> ch == c).count() == 1;
    }

    public Maze(String mazeStr) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mazeStr), "Maze can not be empty!");
        Preconditions.checkArgument(mazeStr.contains("S"), "Maze should have a starting point!");
        Preconditions.checkArgument(mazeStr.contains("F"), "Maze should have an exit point!");
        Preconditions.checkArgument(stringContainsExactlyOneOfGivenChar(mazeStr, 'S'), "Maze should have exactly one starting point!");

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
