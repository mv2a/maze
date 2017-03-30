import excelian.maze.explorer.AutomaticExplorer
import excelian.maze.explorer.AutomaticMazeExplorer
import excelian.maze.explorer.ClockWiseDirection
import excelian.maze.explorer.Explorer
import excelian.maze.explorer.ExplorerLocation
import excelian.maze.explorer.MazeExplorer
import excelian.maze.model.MazeCoordinate
import excelian.maze.model.MazeImpl
import excelian.maze.model.MazeStructure
import spock.lang.Unroll

@Unroll
class Story3ExplorerAutomaticallyExploresAcceptanceSpec extends spock.lang.Specification {

    /*
         * An explorer must be able to automatically explore a maze and find the exit,
         * and on exit they must be able to state the route they took in an understandable fashion.

        Maze used:
        XXXX
        XS X
        XX X
        XXFX
     */

    def "The example maze in file ExampleTestMaze2.txt should be automatically explored"() {
        def fileName = "/ExampleTestMaze2.txt"

        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        AutomaticExplorer explorer = new AutomaticMazeExplorer(maze);

        expect: "The explorer should find the way out automatically"

        def route = explorer.findWayOut()

        route.size() == [
                new MazeCoordinate(1, 1),
                new MazeCoordinate(2, 1),
                new MazeCoordinate(2, 2),
                new MazeCoordinate(2, 3)
        ]

    }


}