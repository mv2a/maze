package excelian.maze.game;

import excelian.maze.explorer.ClockWiseDirection;
import excelian.maze.explorer.Explorer;
import excelian.maze.explorer.ExplorerLocation;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.List;
import java.util.Optional;

public class InactiveExplorer implements Explorer {
    @Override
    public void moveForward() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public void turnLeft() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public void turnRight() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public List<ClockWiseDirection> getPossibleDirections() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public Optional<MazeStructure> whatsInFront() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public List<MazeCoordinate> getMovement() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public ExplorerLocation getLocation() {
        throw new InvalidExplorerStateException();
    }

    @Override
    public MazeStructure whereAmI() {
        throw new InvalidExplorerStateException();
    }
}
