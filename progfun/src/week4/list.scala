import week4._

object List {
  def List() = new Nil[Int]
  def List(x: Int) = new Cons(x, new Nil)
  def List(x: Int, y: Int) = new Cons(x, new Cons(y, new Nil))
}

object test {
  List(1,2)
}