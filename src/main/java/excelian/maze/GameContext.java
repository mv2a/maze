package excelian.maze;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.List;
import java.util.Optional;

public class GameContext implements Explorer {
    private static final Explorer INACTIVE_EXPLORER = new InactiveExplorer();
    Explorer explorerState = INACTIVE_EXPLORER;

    public final void startExploring(Maze maze) {
        explorerState = new MazeExplorer(maze, ClockWiseDirection.UP);
    }

    public final void finishExploring() {
        explorerState = INACTIVE_EXPLORER;
    }

    @Override
    public void moveForward() {
        explorerState.moveForward();
    }

    @Override
    public void turnLeft() {
        explorerState.turnLeft();
    }

    @Override
    public void turnRight() {
        explorerState.turnRight();
    }

    @Override
    public List<ClockWiseDirection> getPossibleDirections() {
        return explorerState.getPossibleDirections();
    }

    @Override
    public Optional<MazeStructure> whatsInFront() {
        return explorerState.whatsInFront();
    }

    @Override
    public List<MazeCoordinate> getMovement() {
        return explorerState.getMovement();
    }

    @Override
    public ExplorerLocation getLocation() {
        return explorerState.getLocation();
    }
}
