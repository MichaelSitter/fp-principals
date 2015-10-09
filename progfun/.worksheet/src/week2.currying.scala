package week2

object currying {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(140); 
	
	def product(f: Int => Int)(a: Int, b: Int): Int = {
		if (a > b) 1
		else f(a) * product(f)(a + 1, b)
	};System.out.println("""product: (f: Int => Int)(a: Int, b: Int)Int""");$skip(23); val res$0 = 
	product(x=> x*x)(3,7);System.out.println("""res0: Int = """ + $show(res$0));$skip(48); 
	
	def fact(n: Int):Int = product(x => x)(1, n);System.out.println("""fact: (n: Int)Int""");$skip(9); val res$1 = 
	fact(4);System.out.println("""res1: Int = """ + $show(res$1));$skip(86); 
	
	def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int): Int = {
		
	};System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)Int""")}
}
