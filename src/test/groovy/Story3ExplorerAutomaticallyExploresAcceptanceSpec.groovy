import org.talangsoft.maze.explorer.AutomaticExplorer
import org.talangsoft.maze.explorer.AutomaticMazeExplorer
import org.talangsoft.maze.model.MazeCoordinate
import org.talangsoft.maze.model.MazeImpl
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

    def "The example maze in file TestMaze2.txt should be automatically explored"() {
        def fileName = "/TestMaze2.txt"

        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        AutomaticExplorer explorer = new AutomaticMazeExplorer(maze)

        expect: "The explorer should find the way out automatically"
        def route = explorer.searchWayOut()
        route.get() == [
                new MazeCoordinate(1, 1),
                new MazeCoordinate(2, 1),
                new MazeCoordinate(2, 2),
                new MazeCoordinate(2, 3)
        ]

    }

    /*
    XXXXXXX
    XS    X
    XXX X X
    X   X X
    X XXX X
    XFXXXXX
     */

    def "The example maze in file TestMaze3.txt should be automatically explored"() {
        def fileName = "/TestMaze3.txt"

        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        AutomaticExplorer explorer = new AutomaticMazeExplorer(maze)

        expect: "The explorer should find the way out automatically"
        def route = explorer.searchWayOut()
        route.get() == [
                new MazeCoordinate(1, 1),
                new MazeCoordinate(2, 1),
                new MazeCoordinate(3, 1),
                new MazeCoordinate(4, 1),
                new MazeCoordinate(5, 1),
                new MazeCoordinate(5, 2),
                new MazeCoordinate(5, 3),
                new MazeCoordinate(5, 4),
                new MazeCoordinate(5, 3),
                new MazeCoordinate(5, 2),
                new MazeCoordinate(5, 1),
                new MazeCoordinate(4, 1),
                new MazeCoordinate(3, 1),
                new MazeCoordinate(3, 2),
                new MazeCoordinate(3, 3),
                new MazeCoordinate(2, 3),
                new MazeCoordinate(1, 3),
                new MazeCoordinate(1, 4),
                new MazeCoordinate(1, 5)
        ]

    }

    def "The example maze in file #fileName should be automatically explored"() {
        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        AutomaticExplorer explorer = new AutomaticMazeExplorer(maze)

        expect: "The explorer should find the way out automatically"
        def route = explorer.searchWayOut()

        route.get().size() == nrOfSteps
        route.get()[0] == startLocation
        route.get()[nrOfSteps - 1] == exitLocation

        where:
        fileName          | nrOfSteps | startLocation            | exitLocation
        "/TestMaze1.txt"  | 78        | new MazeCoordinate(3, 3) | new MazeCoordinate(1, 14)
        "/TestMaze2.txt"  | 4         | new MazeCoordinate(1, 1) | new MazeCoordinate(2, 3)
        "/TestMaze3.txt"  | 19        | new MazeCoordinate(1, 1) | new MazeCoordinate(1, 5)
        "/TestMaze4.txt"  | 9         | new MazeCoordinate(1, 1) | new MazeCoordinate(5, 5)
        "/TestMaze5.txt"  | 21        | new MazeCoordinate(1, 1) | new MazeCoordinate(1, 5)
        "/TestMaze6.txt"  | 21        | new MazeCoordinate(5, 4) | new MazeCoordinate(0, 1)
        "/TestMaze7.txt"  | 5         | new MazeCoordinate(5, 4) | new MazeCoordinate(6, 1)
        "/TestMaze8.txt"  | 6         | new MazeCoordinate(2, 2) | new MazeCoordinate(0, 1)
        "/TestMaze9.txt"  | 4         | new MazeCoordinate(1, 0) | new MazeCoordinate(1, 3)
        "/TestMaze10.txt" | 4         | new MazeCoordinate(1, 3) | new MazeCoordinate(1, 0)
        "/TestMaze11.txt" | 4         | new MazeCoordinate(0, 1) | new MazeCoordinate(3, 1)
        "/TestMaze12.txt" | 4         | new MazeCoordinate(3, 1) | new MazeCoordinate(0, 1)
        "/TestMaze13.txt" | 276       | new MazeCoordinate(11,13)| new MazeCoordinate(0, 1)

    }


    def "The example maze in file #fileName should be automatically explored and left unresolved"() {
        given:
        "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        AutomaticExplorer explorer = new AutomaticMazeExplorer(maze)

        expect: "The explorer should not find the way out automatically"
        def route = explorer.searchWayOut()

        route == Optional.empty()

        where:
        fileName                 | _
        "/NoWayOutTestMaze1.txt" | _
        "/NoWayOutTestMaze2.txt" | _
        "/NoWayOutTestMaze3.txt" | _

    }

}