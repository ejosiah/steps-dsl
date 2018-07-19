package example

import example.Step.StringStep
import shapeless.{HList, HNil}

import scala.concurrent.Future
import scala.util.Random

trait Step[ A <: HList, R] { self =>


  def happly(f: A => Unit => R): Unit => R

  def hmap[B <: HList](f: A => B): Step[B, R] = (f1: B => Unit => R) => self.happly { a => f1(f(a)) }

  def hflatMap[B <: HList](f: A => Step[B, R]): Step[B, R] = (f1: B => Unit => R) => self.happly { a => f(a).happly(f1) }
}


object Step{

  type StringStep[A] = Step[A :: HNil, String]

  type FutureStep[A, B] = Step[A :: HNil, Future[B]]

  type FutureEitherStep[A, L, R] = Step[A :: HNil, Future[Either[L, R]]]
  
  implicit class StepApply[T](step: StringStep[T]){

    def apply(f: T => Unit => String): Unit => String = step.happly{ t => f(t.head) }
  }

  def provide[T](t : T): StringStep[T] = (f: T :: HNil => Unit => String) => f(t)

  def end[T](t: T): Unit => String = _ => t.toString
  

}

import Step._

trait RandomInt{

  def randomInt: StringStep[Int] = {
    provide(Random.nextInt())
  }
}

trait PI{
  def pi: StringStep[Float] = {
    provide(22.0f/7.0f)
  }
}

object Compose extends RandomInt with PI {

  def mix = randomInt{ i =>
    pi { p =>
      end(p * i)
    }
  }

}

object Test extends App{
  Compose.mix("")
}