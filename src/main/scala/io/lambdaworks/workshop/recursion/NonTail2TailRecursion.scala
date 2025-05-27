package io.lambdaworks.workshop.recursion

import scala.annotation.tailrec

/**
 * Rewrite below non tail-recursive functions to tail-recursive one.
 * Add @tailrec annotation to prove it.
 */
object NonTail2TailRecursion {

  def factorial(n: Int): Int = {
    @tailrec
    def loop(n: Int, accumulator : Int = 1) : Int =
      if(n == 1) accumulator else loop(n - 1, accumulator * n)

    loop(n)
  }

  def cubesOfEvens(numbers: List[Double]): List[Double] = {
    @tailrec
    def loop(numbers: List[Double], res: List[Double]): List[Double] = {
      numbers match {
        case x :: xs if x % 2 == 0 => loop(xs, Math.pow(x, 3) :: res)
        case _ :: xs => loop(xs, res)
        case Nil => List.empty
      }
    }
    loop(numbers, List.empty).reverse
  }
}