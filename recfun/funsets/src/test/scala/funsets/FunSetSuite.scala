package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val positiveNumbers: Set = (x: Int) => x > 0
    val negativeNumbers: Set = (x: Int) => x < 0
    val zero: Set = singletonSet(0)
    val oneTwoThree: Set = (x: Int) => x > 0 && x <= 3
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements belonging to both sets") {
    new TestSets {
      val intersection = intersect(s1, s1)
      val emptyIntersect = intersect(s1, s2)
      assert(contains(intersection, 1), "Intersection 1")
      assert(!contains(emptyIntersect, 1), "Intersection 2")
    }
  }
  
  test("diff contains difference of two tests") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
    }
  }

  test("filter returns subset of s for which p holds") {
    new TestSets {
      val f = filter(s1, (x: Int) => x == 1)
      assert(contains(f, 1), "Filter 1")
    }
  }
  
  test("forall evalulates wether bounded integers in s satisfy p") {
    new TestSets {
      assert(forall(s1, (x:Int) => x == 1), "Forall 1 - all items match")
      assert(!forall(s1, (x:Int) => x == 2), "Forall 2 - mismatch")
    }
  }
  
  test("exists determines if there exists a bounded int that satisfies a predicate") {
    new TestSets {
      assert(exists(positiveNumbers, (x: Int) => x == 10), "Exists 1")
      assert(exists(oneTwoThree, (x: Int) => x == 2), "Exists 2")
      assert(exists(oneTwoThree, (x: Int) => x == 3), "Exists 3")
    }
  }
  
  test("map applies f to each member of a set") {
    new TestSets {
      val square = (x: Int) => x * x
      val sm = map(oneTwoThree, square)
      assert(contains(sm, 1))
      assert(contains(sm, 4))
      assert(contains(sm, 9))
    }
  }
}
