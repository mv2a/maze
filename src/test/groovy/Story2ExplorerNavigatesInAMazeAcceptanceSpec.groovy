import excelian.maze.explorer.ClockWiseDirection
import excelian.maze.explorer.Explorer
import excelian.maze.explorer.ExplorerLocation
import excelian.maze.explorer.MazeExplorer
import excelian.maze.model.MazeCoordinate
import excelian.maze.model.MazeImpl
import excelian.maze.model.MazeStructure
import spock.lang.Unroll

@Unroll
class Story2ExplorerNavigatesInAMazeAcceptanceSpec extends spock.lang.Specification {

    /*
        Given a maze the explorer should be able to drop in to the Start point.
        An explorer in a maze must be able to:
        Move forward;
        Turn left and right;
        Understand what is in front of them;
        Understand all movement options from their given location;
        Have a record of where they have been.

        Maze used:
        XXXX
        XS X
        XX X
        XXFX
     */

    def "The example maze in file ExampleTestMaze2.txt should be explored"() {
        def fileName = "/ExampleTestMaze2.txt"

        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        Explorer explorer = new MazeExplorer(maze);

        expect: "The explorer should be in the starting location facing up"
        explorer.location == new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.UP)
        explorer.whereAmI() == MazeStructure.START

        and: "The explorer should follow the way out"
        explorer.turnRight()
        explorer.moveForward(1)
        explorer.turnRight()
        explorer.moveForward(2)
        explorer.whereAmI() == MazeStructure.EXIT

        explorer.movement == [
                new MazeCoordinate(1, 1),
                new MazeCoordinate(2, 1),
                new MazeCoordinate(2, 2),
                new MazeCoordinate(2, 3)
        ]
    }

    /* Maze used:
        XXXXXXXXXXXXXXX
        X             X
        X XXXXXXXXXXX X
        X XS        X X
        X XXXXXXXXX X X
        X XXXXXXXXX X X
        X XXXX      X X
        X XXXX XXXX X X
        X XXXX XXXX X X
        X X    XXXXXX X
        X X XXXXXXXXX X
        X X XXXXXXXXX X
        X X         X X
        X XXXXXXXXX   X
        XFXXXXXXXXXXXXX
    */

    def "The example maze in file ExampleTestMaze1.txt should be explored"() {
        def fileName = "/ExampleTestMaze1.txt"

        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        Explorer explorer = new MazeExplorer(maze);

        expect: "The explorer should be in the starting location facing up"
        explorer.location == new ExplorerLocation(new MazeCoordinate(3, 3), ClockWiseDirection.UP)
        explorer.whereAmI() == MazeStructure.START

        and: "The explorer should follow the way out"
        explorer.turnRight()
        explorer.moveForward(8)
        explorer.turnRight()
        explorer.moveForward(3)
        explorer.turnRight()
        explorer.moveForward(5)
        explorer.turnLeft()
        explorer.moveForward(3)
        explorer.turnRight()
        explorer.moveForward(3)
        explorer.turnLeft()
        explorer.moveForward(3)
        explorer.turnLeft()
        explorer.moveForward(8)
        explorer.turnRight()
        explorer.moveForward(1)
        explorer.turnLeft()
        explorer.moveForward(2)
        explorer.turnLeft()
        explorer.moveForward(12)
        explorer.turnLeft()
        explorer.moveForward(12)
        explorer.turnLeft()
        explorer.moveForward(13)

        explorer.whereAmI() == MazeStructure.EXIT

        explorer.movement.size() == 74
    }


}
