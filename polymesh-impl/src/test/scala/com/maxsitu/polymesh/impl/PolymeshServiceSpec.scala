package com.maxsitu.polymesh.impl

import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import com.maxsitu.polymesh.api._

class PolymeshServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest
    .startServer(
      ServiceTest
        .defaultSetup
        .withCassandra()
    ) { ctx =>
      new PolymeshApplication(ctx) with LocalServiceLocator
    }

  val client: PolymeshService = server
    .serviceClient
    .implement[PolymeshService]

  override protected def afterAll(): Unit = server
    .stop()

  "PolyMesh service" should {

    "say hello" in {
      client
        .hello("Alice")
        .invoke()
        .map { answer =>
          answer should ===("Hello, Alice!")
        }
    }

    "allow responding with a custom message" in {
      for {
        _ <- client
          .useGreeting("Bob")
          .invoke(GreetingMessage("Hi"))
        answer <- client
          .hello("Bob")
          .invoke()
      } yield {
        answer should ===("Hi, Bob!")
      }
    }
  }
}
