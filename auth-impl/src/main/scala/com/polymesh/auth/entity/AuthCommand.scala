package com.polymesh.auth.entity

import akka.Done
import play.api.libs.json.{Format, Json}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType

sealed trait AuthCommand

case object GetAuthPermission extends AuthCommand with ReplyType[Option[AuthPermission]] {
  implicit val format: Format[GetAuthPermission.type] = Json.format
}

case class CreateAuthPermission(authPermission: AuthPermission) extends AuthCommand with ReplyType[Done]
object CreateAuthPermission {
  implicit val format: Format[CreateAuthPermission] = Json.format
}

case class RemoveAuthPermission(authPermission: AuthPermission)  extends AuthCommand with ReplyType[Done]
object RemoveAuthPermission {
  implicit val format: Format[RemoveAuthPermission] = Json.format
}
