package week3

object intsets {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  val t1 = new NonEmpty(3, Empty, Empty);System.out.println("""t1  : week3.NonEmpty = """ + $show(t1 ));$skip(22); 
  val t2 = t1 incl 10;System.out.println("""t2  : week3.IntSet = """ + $show(t2 ));$skip(21); 
  val t3 = t2 incl 5;System.out.println("""t3  : week3.IntSet = """ + $show(t3 ));$skip(65); 
  val t4 = new NonEmpty(7, new NonEmpty(6, Empty, Empty), Empty);System.out.println("""t4  : week3.NonEmpty = """ + $show(t4 ));$skip(14); val res$0 = 
  t3 union t4;System.out.println("""res0: week3.IntSet = """ + $show(res$0))}
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
