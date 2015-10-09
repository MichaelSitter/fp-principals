package week4

object nth {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(197); 
  def nth[T](n: Int, list: List[T]): T = {
    if (n < 0 || n > list.length) throw new IndexOutOfBoundsException()
    if (n == 0) list.head
    nth(n - 1, list.tail)
	};System.out.println("""nth: [T](n: Int, list: week4.List[T])T""")}
}
