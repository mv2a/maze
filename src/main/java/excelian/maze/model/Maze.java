package excelian.maze.model;

public interface Maze {
    long getNumberOfWalls();

    long getNumberOfEmptySpaces();

    int getDimensionX();

    int getDimensionY();

    MazeCoordinate getStartLocation();

    MazeCoordinate getExitLocation();

    MazeStructure whatsAt(MazeCoordinate coord);
}
