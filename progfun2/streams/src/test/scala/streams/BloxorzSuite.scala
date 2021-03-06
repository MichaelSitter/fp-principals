package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) =>
        require(block.isLegal) // The solution must always lead to legal blocks
        move match {
          case Left => block.left
          case Right => block.right
          case Up => block.up
          case Down => block.down
        }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }


	test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(terrain(Pos(1,1)), "1,1") // start
      assert(terrain(Pos(4,7)), "4,7") // goal
      assert(terrain(Pos(5,8)), "5,8")
      assert(!terrain(Pos(5,9)), "5,9")
      assert(terrain(Pos(4,9)), "4,9")
      assert(!terrain(Pos(6,8)), "6,8")
      assert(!terrain(Pos(4,11)), "4,11")
      assert(!terrain(Pos(-1,0)), "-1,0")
      assert(!terrain(Pos(0,-1)), "0,-1")
    }
  }

	test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }

  test("block is standing") {
    new Level1 {
      assert(Block(Pos(1, 1), Pos(1, 1)).isStanding)
      assert(!Block(Pos(1, 1), Pos(2, 1)).isStanding)
    }
  }

  test("block is legal") {
    new Level1 {
      assert(Block(Pos(1, 1), Pos(1, 1)).isLegal)
      assert(Block(Pos(1, 1), Pos(1, 2)).isLegal)
      assert(!Block(Pos(1, 7), Pos(1, 7)).isLegal)
      assert(!Block(Pos(1, 7), Pos(1, 8)).isLegal)
    }
  }

  test("start block") {
    new Level1 {
      assert(startBlock === Block(Pos(1,1), Pos(1,1)))
      assert(startBlock !== Block(Pos(2,2), Pos(2,2)))
    }
  }

  test("neighbours") {
    new Level1 {
      assert(startBlock.neighbors.contains((startBlock.left, Left)))
      assert(startBlock.neighbors.contains((startBlock.right, Right)))
      assert(startBlock.neighbors.contains((startBlock.up, Up)))
      assert(startBlock.neighbors.contains((startBlock.down, Down)))
    }
  }

  test("legal neighbours") {
    new Level1 {
      val b = Block(Pos(0,0), Pos(0,0))
      assert(b.legalNeighbors.contains((b.down, Down)))
      assert(b.legalNeighbors.contains((b.right, Right)))
      assert(!b.legalNeighbors.contains((b.up, Up)))
    }
  }

  test("done") {
    new Level1 {
      val goalBlock = Block(Pos(4,7), Pos(4,7))
      val tippedBlock = Block(Pos(4,7), Pos(4,8))
      assert(done(goalBlock))
      assert(!done(tippedBlock))
    }
  }

  test("neighbours with history") {
    new Level1 {
      val result = neighborsWithHistory(Block(Pos(1,1),Pos(1,1)), List(Left,Up))
      val target = Set(
        (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
        (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
      )
      assert(result.toSet == target)
    }
  }

  test("new neighbours only") {
    new Level1 {
      val result = newNeighborsOnly(
        Set(
          (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
          (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
        ).toStream,

        Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
      )
      val target = Set(
        (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
      ).toStream

      assert(result.toList == target.toList)
    }
  }

	test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }


	test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }

}
