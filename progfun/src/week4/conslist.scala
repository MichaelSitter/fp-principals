package week4

trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def length: Int
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
  def length = 1 + (tail.length)
}

class Nil[T]() extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def length = 0
}

object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T]() = new Nil
}