package com.maxsitu.polymesh.impl

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEventTag, EventStreamElement, ReadSideProcessor}
import com.lightbend.lagom.scaladsl.persistence.slick.SlickReadSide
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

class IdentityEventProcessor(
  readSide: SlickReadSide,
  identityRepository: IdentityRepository
)(implicit ec: ExecutionContext) extends ReadSideProcessor[IdentityEvent] {
  override def buildHandler(): ReadSideProcessor.ReadSideHandler[IdentityEvent] = {
    readSide.builder[IdentityEvent]("identityEventOffset")
      .setEventHandler[AuthPermissionCreated](insertAuthPermission)
      .build()
  }

  override def aggregateTags: Set[AggregateEventTag[IdentityEvent]] = IdentityEvent.Tag.allTags

  private def insertAuthPermission(perm: EventStreamElement[AuthPermissionCreated]): DBIO[Done] = {
    import identityRepository.profile.api._
    import identityRepository.AuthPermissionRow
    identityRepository.AuthPermission.insertOrUpdate(AuthPermissionRow(0, perm.event.name, perm.event.codeName))
      .map(_ => Done)
  }
}
