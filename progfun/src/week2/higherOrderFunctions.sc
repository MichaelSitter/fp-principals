package week2

object higherOrderFunctions {
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }
    loop(a, 0)
  }
  def sumOfSquares = sum(x => x*x)_
  def sumOfCubes = sum(x => x*x*x)_
  
  sumOfSquares(2,4)
  sumOfCubes(1,2)
  
  val thingy: Option<Int> = Option
}