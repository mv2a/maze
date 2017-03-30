package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;

import java.util.List;

public class AutomaticMazeExplorer extends MazeExplorer implements AutomaticExplorer {

    public AutomaticMazeExplorer(Maze maze) {
        super(maze);
    }

    public AutomaticMazeExplorer(Maze maze, ClockWiseDirection startingDirection) {
        super(maze, startingDirection);
    }

    @Override
    public List<MazeCoordinate> findWayOut() {
        return getMovement();
    }
}
