package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;

import java.util.List;

public interface AutomaticExplorer {

    List<MazeCoordinate> findWayOut();
}
