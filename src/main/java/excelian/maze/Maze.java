package excelian.maze;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.Optional;


enum MazeStructure {
    WALL('X'),
    SPACE(' '),
    START('S'),
    EXIT('F');

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

public class Maze {

    private String[] mazeData;

    private final int dimensionX;

    private final int dimensionY;

    private final long numberOfWalls;
    private final long numberOfEmptySpaces;

    private final MazeCoordinate startLocation;

    private final MazeCoordinate exitLocation;

    private final String LINEBREAK = "\\r?\\n";


    private final long countStringContainsOfGivenChar(String str, char c) {
        return str.chars().filter(ch -> ch == c).count();
    }

    public Maze(String mazeStr) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mazeStr), "Maze can not be empty!");
        Preconditions.checkArgument(countStringContainsOfGivenChar(mazeStr, MazeStructure.START.charRepresentation()) == 1, "Maze should have exactly one starting point!");
        Preconditions.checkArgument(countStringContainsOfGivenChar(mazeStr, MazeStructure.EXIT.charRepresentation()) == 1, "Maze should have exactly one exit point!");

        this.mazeData = mazeStr.split(LINEBREAK);

        Preconditions.checkArgument(allRowsHasTheSameLength(this.mazeData), "Maze rows should consist of the same number of blocks!");

        dimensionX = mazeData[0].length();
        dimensionY = mazeData.length;

        startLocation = findLocation(mazeStr, MazeStructure.START);
        exitLocation = findLocation(mazeStr, MazeStructure.EXIT);

        numberOfWalls = countStringContainsOfGivenChar(mazeStr, MazeStructure.WALL.charRepresentation());
        numberOfEmptySpaces = countStringContainsOfGivenChar(mazeStr, MazeStructure.SPACE.charRepresentation());
    }

    private final MazeCoordinate findLocation(String mazeStr, MazeStructure mazeStructure) {
        int indexOfLocation = mazeStr.indexOf(mazeStructure.charRepresentation());
        return new MazeCoordinate(indexOfLocation % (dimensionX + 1), indexOfLocation / (dimensionX + 1));
    }

    private final boolean allRowsHasTheSameLength(String[] mazeData) {
        for (String mazeRow : mazeData) {
            if (mazeRow.length() != mazeData[0].length()) {
                return false;
            }
        }
        return true;
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

    public MazeCoordinate getStartLocation() {
        return startLocation;
    }

    public MazeCoordinate getExitLocation() {
        return exitLocation;
    }

    public MazeStructure whatsAt(MazeCoordinate coord) {
        return MazeStructure.from(mazeData[coord.getY()].charAt(coord.getX()));
    }
}
