package week3

object intsets {
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : week3.NonEmpty = {.3.}
  val t2 = t1 incl 10                             //> t2  : week3.IntSet = {.3{.10.}}
  val t3 = t2 incl 5                              //> t3  : week3.IntSet = {.3{{.5.}10.}}
  val t4 = new NonEmpty(7, new NonEmpty(6, Empty, Empty), Empty)
                                                  //> t4  : week3.NonEmpty = {{.6.}7.}
  t3 union t4                                     //> res0: week3.IntSet = {{{{.3.}5.}6.}7{.10.}}
}

abstract class IntSet {
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(other: IntSet): IntSet
}

object Empty extends IntSet {
	def contains(x: Int):Boolean = false
	def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
	override def toString = "."
	override def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
	def contains(x: Int): Boolean = {
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true
	}
	
	def incl(x: Int): IntSet = {
		if (x < elem) new NonEmpty(elem, left incl x, right)
		else if (x > elem) new NonEmpty(elem, left, right incl x)
		else this
	}
	
	override def union(other: IntSet): IntSet = {
		((left union right) union other) incl elem
	}
	
	override def toString = "{" + left + elem + right + "}"
}