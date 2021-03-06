package week3

object rationals {
  val x = new Rational(1,3)                       //> x  : week3.Rational = 1/3
  val y = new Rational(5,7)                       //> y  : week3.Rational = 5/7
  val z = new Rational(3,2)                       //> z  : week3.Rational = 3/2
  val xx = new Rational(2)                        //> xx  : week3.Rational = 2/1
  x.sub(y).sub(z)                                 //> res0: week3.Rational = -79/42
  y.add(y)                                        //> res1: week3.Rational = 10/7
  x.less(y)                                       //> res2: Boolean = true
  x.max(y)                                        //> res3: week3.Rational = 5/7
  x.add(x)                                        //> res4: week3.Rational = 2/3
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