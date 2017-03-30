import excelian.maze.explorer.ClockWiseDirection
import excelian.maze.explorer.ExplorerLocation
import excelian.maze.game.GameContext
import excelian.maze.model.MazeCoordinate
import excelian.maze.model.MazeImpl
import excelian.maze.model.MazeStructure
import spock.lang.Unroll

@Unroll
class Story2ExplorerNavigatesInAMazeSpec extends spock.lang.Specification {

    /*
        Given a maze the explorer should be able to drop in to the Start point.
        An explorer in a maze must be able to:
        Move forward;
        Turn left and right;
        Understand what is in front of them;
        Understand all movement options from their given location;
        Have a record of where they have been.

        Maze used:
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

        GameContext game = new GameContext()
        game.startExploring(maze)

        expect: "The explorer should be in the starting location facing up"
        game.location == new ExplorerLocation(new MazeCoordinate(3, 3), ClockWiseDirection.UP)
        game.whereAmI() == MazeStructure.START

        and: "The explorer should follow the way out"
        game.turnRight()
        game.moveForward(8)
        game.turnRight()
        game.moveForward(3)
        game.turnRight()
        game.moveForward(5)
        game.turnLeft()
        game.moveForward(3)
        game.turnRight()
        game.moveForward(3)
        game.turnLeft()
        game.moveForward(3)
        game.turnLeft()
        game.moveForward(8)
        game.turnRight()
        game.moveForward(1)
        game.turnLeft()
        game.moveForward(2)
        game.turnLeft()
        game.moveForward(12)
        game.turnLeft()
        game.moveForward(12)
        game.turnLeft()
        game.moveForward(13)

        game.whereAmI() == MazeStructure.EXIT

        game.movement.size() == 74
    }


}
