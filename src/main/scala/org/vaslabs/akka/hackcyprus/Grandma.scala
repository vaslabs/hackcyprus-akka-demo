package org.vaslabs.akka.hackcyprus

import akka.actor.{Actor, ActorLogging, Props}

class Grandma private () extends Actor with ActorLogging{
  import Grandma._
  import Grandson.BuyMilk
  var milk: Option[Milk] = None

  def makeLatte(): Unit =
    milk.fold{
      context.become(cantMakeCoffeeWithMilk)
      sender() ! BuyMilk
    }(m => sender() ! Latte())

  def makeCappuccino(): Unit =
    milk.fold{
      context.become(cantMakeCoffeeWithMilk)
      sender() ! BuyMilk
    }(m => sender() ! Cappuccino())

  override def receive() = {
    case CoffeeRequest(coffee: Latte) =>
      makeLatte()
    case CoffeeRequest(coffee: Cappuccino) =>
      makeCappuccino()
    case CoffeeRequest(coffee: GreekCoffee) =>
      sender() ! GreekCoffee()
  }

  private def cantMakeCoffeeWithMilk: Receive = {
    case CoffeeRequest(coffee: Latte) =>
      sender() ! NoMilk
    case CoffeeRequest(coffee: Cappuccino) =>
      sender() ! NoMilk
    case CoffeeRequest(coffee: GreekCoffee) =>
      sender() ! GreekCoffee()
    case m: Milk =>
      milk = Some(m)
      context.become(receive)
  }
}

object Grandma {
  def props(): Props = Props[Grandma]

  case class CoffeeRequest(coffee: Coffee)
  case class Milk()
  sealed trait Coffee

  case class Latte() extends Coffee
  case class Cappuccino() extends Coffee
  case class GreekCoffee() extends Coffee

  case object NoMilk
}
