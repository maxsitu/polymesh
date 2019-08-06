package com.maxsitu.polymesh.impl

import java.util.UUID

import play.api.libs.json.{Format, Json}

case class IdentityState(users: Seq[User] = Seq.empty)

object IdentityState {
  implicit val format: Format[IdentityState] = Json.format
}

case class User(
  id: UUID,
  firstName: String,
  lastName: String,
  email: String,
  username: String,
  password: String
)
object User {
  implicit val format: Format[User] = Json.format
}