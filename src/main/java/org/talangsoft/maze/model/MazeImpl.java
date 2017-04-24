package org.talangsoft.maze.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public final class MazeImpl implements Maze {

    private final String[] mazeData;

    private final int dimensionX;

    private final int dimensionY;

    private final long numberOfWalls;
    private final long numberOfEmptySpaces;

    private final MazeCoordinate startLocation;

    private final MazeCoordinate exitLocation;

    private final String LINEBREAK = "\\r?\\n";

    public MazeImpl(String mazeStr) {
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

    private final long countStringContainsOfGivenChar(String str, char c) {
        return str.chars().filter(ch -> ch == c).count();
    }

    private final MazeCoordinate findLocation(String mazeStr, MazeStructure mazeStructure) {
        int indexOfLocation = mazeStr.replaceAll(LINEBREAK, "").indexOf(mazeStructure.charRepresentation());
        return new MazeCoordinate(indexOfLocation % dimensionX, indexOfLocation / dimensionX);
    }

    private final boolean allRowsHasTheSameLength(String[] mazeData) {
        for (String mazeRow : mazeData) {
            if (mazeRow.length() != mazeData[0].length()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long getNumberOfWalls() {
        return numberOfWalls;
    }

    @Override
    public long getNumberOfEmptySpaces() {
        return numberOfEmptySpaces;
    }

    @Override
    public int getDimensionX() {
        return dimensionX;
    }

    @Override
    public int getDimensionY() {
        return dimensionY;
    }

    @Override
    public MazeCoordinate getStartLocation() {
        return startLocation;
    }

    @Override
    public MazeCoordinate getExitLocation() {
        return exitLocation;
    }

    @Override
    public MazeStructure whatsAt(MazeCoordinate coord) {
        return MazeStructure.from(mazeData[coord.getY()].charAt(coord.getX()));
    }
}
