import week4._

object nth {
  def nth[T](n: Int, list: List[T]): T = {
    if (list.isEmpty) throw new IndexOutOfBoundsException()
    if (n == 0) list.head
    else nth(n - 1, list.tail)
	}                                         //> nth: [T](n: Int, list: week4.List[T])T
	
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week4.Cons[Int] = week4.Cons@5af8592
	nth(0, list)                              //> res0: Int = 1
	
	//nth(-1, list)
	nth(99, list)                             //> java.lang.IndexOutOfBoundsException
                                                  //| 	at nth$$anonfun$main$1.nth$1(nth.scala:5)
                                                  //| 	at nth$$anonfun$main$1.apply$mcV$sp(nth.scala:14)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at nth$.main(nth.scala:3)
                                                  //| 	at nth.main(nth.scala)
}