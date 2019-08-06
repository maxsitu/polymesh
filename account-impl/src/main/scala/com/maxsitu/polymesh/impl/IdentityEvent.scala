package com.maxsitu.polymesh.impl

import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventShards, AggregateEventTag}
import play.api.libs.json.{Format, Json}

object IdentityEvent {
  val NumShards = 5
  val Tag = AggregateEventTag.sharded[IdentityEvent]("IdentityEvent", NumShards)
}

sealed trait IdentityEvent extends AggregateEvent[IdentityEvent] {
  override def aggregateTag(): AggregateEventShards[IdentityEvent] = IdentityEvent.Tag
}

case class AuthPermissionCreated(name: String, codeName: String) extends IdentityEvent
object AuthPermissionCreated {
  implicit val format: Format[AuthPermissionCreated] = Json.format
}