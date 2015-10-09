package week3

object rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(61); 
  val x = new Rational(1,3);System.out.println("""x  : week3.Rational = """ + $show(x ));$skip(28); 
  val y = new Rational(5,7);System.out.println("""y  : week3.Rational = """ + $show(y ));$skip(28); 
  val z = new Rational(3,2);System.out.println("""z  : week3.Rational = """ + $show(z ));$skip(27); 
  val xx = new Rational(2);System.out.println("""xx  : week3.Rational = """ + $show(xx ));$skip(18); val res$0 = 
  x.sub(y).sub(z);System.out.println("""res0: week3.Rational = """ + $show(res$0));$skip(11); val res$1 = 
  y.add(y);System.out.println("""res1: week3.Rational = """ + $show(res$1));$skip(12); val res$2 = 
  x.less(y);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(11); val res$3 = 
  x.max(y);System.out.println("""res3: week3.Rational = """ + $show(res$3));$skip(11); val res$4 = 
  x.add(x);System.out.println("""res4: week3.Rational = """ + $show(res$4))}
}

class Rational(x: Int, y:Int) {
	require(y != 0, "denominator must be non-zero")
	
	def this(x:Int) = this(x, 1)

	private def gcd(a: Int, b:Int): Int = if (b == 0) a else gcd(b, a % b)
	val numer = x
	val denom = y
	
	def neg: Rational = new Rational(-numer, denom)
	
	def add(that: Rational) =
		new Rational(
			numer * that.denom + that.numer * denom,
			denom * that.denom)
			
	def sub(that: Rational) =
		this.add(that.neg)
		
	def multiply(that: Rational) =
		new Rational(
			numer * that.numer,
			denom * that.denom)
			
  def less(that: Rational) = this.numer * that.denom < that.numer * this.denom
  
  def max(that: Rational) = if (this.less(that)) that else this

	override def toString: String = {
		val g = gcd(numer, denom)
		numer / g + "/" + denom / g
	}
}
