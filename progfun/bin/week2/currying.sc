package week2

object currying {
	
	def product(f: Int => Int)(a: Int, b: Int): Int = {
		if (a > b) 1
		else f(a) * product(f)(a + 1, b)
	}
	product(x=> x*x)(3,7)
	
	def fact(n: Int):Int = product(x => x)(1, n)
	fact(4)
	
	def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int): Int = {
		
	}
}