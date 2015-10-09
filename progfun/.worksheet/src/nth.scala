import week4._

object nth {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(191); 
  def nth[T](n: Int, list: List[T]): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException()
    if (n == 0) list.head
    else nth(n - 1, list.tail)
	};System.out.println("""nth: [T](n: Int, list: week4.List[T])T""");$skip(61); 
	
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)));System.out.println("""list  : week4.Cons[Int] = """ + $show(list ));$skip(14); val res$0 = 
	nth(0, list);System.out.println("""res0: Int = """ + $show(res$0));$skip(34); val res$1 = 
	
	//nth(-1, list)
	nth(99, list);System.out.println("""res1: Int = """ + $show(res$1))}
}
