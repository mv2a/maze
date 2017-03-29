package excelian.maze;

import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.List;

public interface Explorer {

	void moveForward();

	void turnLeft();

	void turnRight();

	List<ClockWiseDirection> getPossibleDirections();

	MazeStructure whatsInFront();

	List<MazeCoordinate> getMovements();

    ExplorerLocation getLocation();


}
