package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = oneOf(
    const(empty),
    for {
      a <- arbitrary[A]
      h <- oneOf(const(empty), genHeap)
    } yield insert(a, h)
  )

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("adding minimal element and finding it should return element") = forAll { (h: H) =>
    val smallest = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(smallest, h)) == smallest
  }

  property("smaller of two") = forAll { (a1: A, a2: A) =>
    val h = insert(a1, insert(a2, empty))
    if (a1 < a2) findMin(h) == a1
    else findMin(h) == a2
  }

  property("delete single element heap") = forAll { (a: A) =>
    val h = insert(a, empty)
    deleteMin(h) == empty
  }

  def toSeq(h: H): Seq[A] = {
    def step(h: H, as: Seq[A]): Seq[A] = {
      if (isEmpty(h)) as
      else step(deleteMin(h), as :+ findMin(h))
    }
    step(h, Seq.empty)
  }

  property("sorted sequence of elements") = forAll { (h: H) =>
    val seq = toSeq(h)
    seq == seq.sorted
  }

  property("minimum of meld is min of either") = forAll { (h1: H, h2: H) =>
    val m = meld(h1, h2)
    if (isEmpty(m)) true
    else if (isEmpty(h1)) findMin(h2) == findMin(m)
    else if (isEmpty(h2)) findMin(h1) == findMin(m)
    else Math.min(findMin(h1), findMin(h2)) == findMin(m)
  }

  property("meld of heaps is commutative") = forAll { (h1: H, h2: H) =>
    meld(h1, h2) == meld(h2, h1)
  }

  property("delete min should produce a smaller queue") = forAll { (h: H) =>
    if (isEmpty(h)) true
    else toSeq(deleteMin(h)) == toSeq(h).tail
  }

  property("inserts produce correctly ordered heap") = forAll { (as: Seq[A]) =>
    val heap = as.foldLeft(empty)((h, a) => insert(a, h))
    toSeq(heap) == as.sorted
  }
}
