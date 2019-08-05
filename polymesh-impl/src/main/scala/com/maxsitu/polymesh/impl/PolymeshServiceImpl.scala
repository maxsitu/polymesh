package com.maxsitu.polymesh.impl

import com.maxsitu.polymesh.api
import com.maxsitu.polymesh.api.PolymeshService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the PolymeshService.
  */
class PolymeshServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends PolymeshService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the PolyMesh entity for the given ID.
    val ref = persistentEntityRegistry
      .refFor[PolymeshEntity](id)

    // Ask the entity the Hello command.
    ref
      .ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the PolyMesh entity for the given ID.
    val ref = persistentEntityRegistry
      .refFor[PolymeshEntity](id)

    // Tell the entity to use the greeting message specified.
    ref
      .ask(
        UseGreetingMessage(
          request
            .message
        )
      )
  }


  override def greetingsTopic(): Topic[api.GreetingMessageChanged] =
    TopicProducer
      .singleStreamWithOffset {
        fromOffset =>
          persistentEntityRegistry
            .eventStream(
              PolymeshEvent
                .Tag, fromOffset
            )
            .map(
              ev => (convertEvent(ev), ev
                .offset)
            )
      }

  private def convertEvent(helloEvent: EventStreamElement[PolymeshEvent]): api.GreetingMessageChanged = {
    helloEvent
      .event match {
      case GreetingMessageChanged(msg) => api
        .GreetingMessageChanged(
          helloEvent
            .entityId, msg
        )
    }
  }
}
