package excelian.maze.game;

import excelian.maze.explorer.ClockWiseDirection;
import excelian.maze.explorer.Explorer;
import excelian.maze.explorer.ExplorerLocation;
import excelian.maze.explorer.MazeExplorer;
import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.List;
import java.util.Optional;

public class GameContext implements Explorer {
    private static final Explorer INACTIVE_EXPLORER = new InactiveExplorer();
    Explorer explorer = INACTIVE_EXPLORER;

    public final void startExploring(Maze maze) {
        explorer = new MazeExplorer(maze, ClockWiseDirection.UP);
    }

    public final void finishExploring() {
        explorer = INACTIVE_EXPLORER;
    }

    @Override
    public void moveForward() {
        explorer.moveForward();
    }

    public void moveForward(int nrOfFields) {
        for (int i = 0; i < nrOfFields; i++) {
            explorer.moveForward();
        }
    }

    @Override
    public void turnLeft() {
        explorer.turnLeft();
    }

    @Override
    public void turnRight() {
        explorer.turnRight();
    }

    @Override
    public MazeStructure whereAmI() {
        return explorer.whereAmI();
    }

    @Override
    public List<ClockWiseDirection> getPossibleDirections() {
        return explorer.getPossibleDirections();
    }

    @Override
    public Optional<MazeStructure> whatsInFront() {
        return explorer.whatsInFront();
    }

    @Override
    public List<MazeCoordinate> getMovement() {
        return explorer.getMovement();
    }

    @Override
    public ExplorerLocation getLocation() {
        return explorer.getLocation();
    }
}
