package org.vaslabs.akka.hackcyprus

import akka.actor.Actor

class Grandson extends Actor{
  import org.vaslabs.akka.hackcyprus.Grandma.Milk

  import org.vaslabs.akka.hackcyprus.Grandson._

  override def receive = {
    case BuyMilk =>
      sender() ! Milk
  }
}

object Grandson {
  case object BuyMilk
}
