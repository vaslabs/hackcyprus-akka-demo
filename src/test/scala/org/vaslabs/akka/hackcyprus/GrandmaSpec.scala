package org.vaslabs.akka.hackcyprus

import akka.actor.{ActorRef, ActorSystem}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.FlatSpecLike
import org.vaslabs.akka.hackcyprus.Grandma.{Cappuccino, Latte, Milk, NoMilk}
import org.vaslabs.akka.hackcyprus.Grandson.BuyMilk

class GrandmaSpec extends TestKit(ActorSystem("TestSystem")) with FlatSpecLike with ImplicitSender{

  "when sending a request for latte to grandma we" should "get buy milk, then no milk" in {
    val grandma = TestActorRef(Grandma.props(), "grandma")

    grandma ! Grandma.CoffeeRequest(Latte())
    expectMsg(BuyMilk)

    grandma ! Grandma.CoffeeRequest(Latte())
    expectMsg(NoMilk)

    grandma ! Grandma.CoffeeRequest(Cappuccino())
    expectMsg(NoMilk)

    grandma ! Milk()
    grandma ! Grandma.CoffeeRequest(Latte())

    expectMsg(Latte())
  }

}
