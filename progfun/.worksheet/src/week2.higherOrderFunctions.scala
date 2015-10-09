package week2

object higherOrderFunctions {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(215); 
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }
    loop(a, 0)
  };System.out.println("""sum: (f: Int => Int)(a: Int, b: Int)Int""");$skip(36); 
  def sumOfSquares = sum(x => x*x)_;System.out.println("""sumOfSquares: => (Int, Int) => Int""");$skip(36); 
  def sumOfCubes = sum(x => x*x*x)_;System.out.println("""sumOfCubes: => (Int, Int) => Int""");$skip(23); val res$0 = 
  
  sumOfSquares(2,4);System.out.println("""res0: Int = """ + $show(res$0));$skip(18); val res$1 = 
  sumOfCubes(1,2);System.out.println("""res1: Int = """ + $show(res$1));$skip(38); 
  
  val thingy: Option<Int> = Option;System.out.println("""thingy: => <error> = """ + $show(thingy))}
}
