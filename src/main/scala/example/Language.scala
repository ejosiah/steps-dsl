package example

import scala.language.higherKinds

trait Language[Wrapper[_]] {
  def number(v: Int): Wrapper[_]

  def increment(a: Wrapper[Int]): Wrapper[Int]

  def add(a: Wrapper[Int], b: Wrapper[Int]): Wrapper[Int]

  def text(v: String): Wrapper[String]

  def toUpper(a: Wrapper[String]): Wrapper[String]

  def concat(a: Wrapper[String], b: Wrapper[String]): Wrapper[String]

  def toString(v: Wrapper[Int]): Wrapper[String]
}

trait ScalaToLanguagueBridge[ScalaValue]{
  def apply[Wrapper[_]](implicit l: Language[Wrapper]): Wrapper[ScalaValue]
}


trait SignIn[Wrapper[_]]{
  case class Journey()

  def getJourney: Wrapper[Journey]

  def link(profileId: String, journey: Wrapper[Journey]): Wrapper[Unit]

  def complete(journey: Wrapper[Journey]): Wrapper[_]

}