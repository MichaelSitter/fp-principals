package week01

object session {
	def abs(x:Double) =
		if (x < 0) -x else x              //> abs: (x: Double)Double

	def sqrt(x: Double) = {

	  def sqrtIter(guess: Double, x:Double): Double =
	  	if (isGoodEnough(guess, x)) guess
	  	else sqrtIter(improve(guess, x), x)
	  	
		def isGoodEnough(guess:Double, x:Double) =
			abs(guess * guess - x) /x < 0.001
			
		def improve(guess:Double, x:Double) =
			(guess + x / guess) / 2
	
	 	sqrtIter(1.0, x)
	}                                         //> sqrt: (x: Double)Double
	
	sqrt(9)                                   //> res0: Double = 3.00009155413138
	sqrt(1e-6)                                //> res1: Double = 0.0010000001533016628
	sqrt(1.0e60)                              //> res2: Double = 1.0000788456669446E30

	def factorial(x:Int): Int = {
		if(x== 1)
			1
		else
			x * factorial(x - 1)
	}                                         //> factorial: (x: Int)Int
	factorial(5)                              //> res3: Int = 120
}