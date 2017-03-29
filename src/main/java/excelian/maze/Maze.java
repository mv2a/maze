package excelian.maze;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;


enum MazeStructure {
    WALL('X'),
    SPACE(' '),
    START('S'),
    EXIT('F'),
    NEWROW('\n');

    private char charRepresentation;

    MazeStructure(char charRepresentation) {
        this.charRepresentation = charRepresentation;
    }

    public char charRepresentation() {
        return charRepresentation;
    }

    public String representation() {
        return Character.toString(charRepresentation);
    }

}

public class Maze {

    private String[] mazeData;

    private final int dimensionX;

    private final int dimensionY;

    private boolean stringContainsExactlyOneOfGivenChar(String str, char c) {
        return str.chars().filter(ch -> ch == c).count() == 1;
    }

    public Maze(String mazeStr) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mazeStr), "Maze can not be empty!");
        Preconditions.checkArgument(mazeStr.contains(MazeStructure.START.representation()), "Maze should have a starting point!");
        Preconditions.checkArgument(mazeStr.contains(MazeStructure.EXIT.representation()), "Maze should have an exit point!");
        Preconditions.checkArgument(stringContainsExactlyOneOfGivenChar(mazeStr, MazeStructure.START.charRepresentation()), "Maze should have exactly one starting point!");

        this.mazeData = mazeStr.split(MazeStructure.NEWROW.representation());

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
