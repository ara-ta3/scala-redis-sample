package sample.util

import scala.language.reflectiveCalls
import scala.reflect.Selectable.reflectiveSelectable

case class Loan[T <: { def close(): Unit }](value: T) {
  def using[U](f: T => U): U =
    try f(value)
    finally value.close()
}
