package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;

import java.util.List;
import java.util.Optional;

public interface AutomaticExplorer {

    Optional<List<MazeCoordinate>> searchWayOut();
}
