package excelian.maze;

import java.util.List;

public interface Explorer {

	void moveForward();

	void turnLeft();

	void turnRight();

	List<Direction> getPossibleDirections();

	MazeStructure whatsInFront();

	List<MazeCoordinate> getMovements();

    ExplorerLocation getLocation();


}
