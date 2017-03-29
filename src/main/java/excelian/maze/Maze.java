package excelian.maze;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.Optional;

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

    public static MazeStructure from(char ch) {
        Optional<MazeStructure> structureFromChar = Arrays.stream(MazeStructure.values()).filter(ms -> ms.charRepresentation == ch).findFirst();
        return structureFromChar.orElseThrow(() -> new IllegalArgumentException(String.format("Maze structure not recognised from '%s'!", ch)));
    }

}

public final class Maze {

    private String[] mazeData;

    private final int dimensionX;

    private final int dimensionY;

    private final long numberOfWalls;
    private final long numberOfEmptySpaces;


    private long countStringContainsOfGivenChar(String str, char c) {
        return str.chars().filter(ch -> ch == c).count();
    }

    public Maze(String mazeStr) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mazeStr), "Maze can not be empty!");
        Preconditions.checkArgument(countStringContainsOfGivenChar(mazeStr, MazeStructure.START.charRepresentation()) == 1, "Maze should have exactly one starting point!");
        Preconditions.checkArgument(countStringContainsOfGivenChar(mazeStr, MazeStructure.EXIT.charRepresentation()) == 1, "Maze should have exactly one exit point!");

        this.mazeData = mazeStr.split(MazeStructure.NEWROW.representation());

        dimensionX = mazeData[0].length();
        dimensionY = mazeData.length;
        numberOfWalls = countStringContainsOfGivenChar(mazeStr, MazeStructure.WALL.charRepresentation());
        numberOfEmptySpaces = countStringContainsOfGivenChar(mazeStr, MazeStructure.SPACE.charRepresentation());
    }

    public long getNumberOfWalls() {
        return numberOfWalls;
    }

    public long getNumberOfEmptySpaces() {
        return numberOfEmptySpaces;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public MazeStructure whatsAt(int x, int y) {
        return MazeStructure.from(mazeData[y].charAt(x));
    }
}
