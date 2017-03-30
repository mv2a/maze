package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;

import java.util.List;
import java.util.Optional;

public interface AutomaticExplorer {

    /**
     * Search the way out from the maze and returning the movement history
     *
     * @return the movement history that was made to find the way out or empty if no exit found
     */
    Optional<List<MazeCoordinate>> searchWayOut();
}
