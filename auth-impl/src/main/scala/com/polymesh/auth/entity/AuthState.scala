package com.polymesh.auth.entity


import play.api.libs.json.{Format, Json}

object AuthStatus extends Enumeration {
  type Status = Value
  val Created, Removed = Value
  implicit val format: Format[Status] = Json.formatEnum(AuthStatus)
}

case class AuthPermission(
  codeName: AuthPermissionCodeName,
  descr: String,
  status: AuthStatus.Status
)

object AuthPermission {
  implicit val format: Format[AuthPermission] = Json.format
}

case class AuthGroup(
  name: String,
  authPermissions: Set[String] = Set
    .empty[String],
  status: AuthStatus.Status
) {
  def addPermission(permission: AuthPermission): AuthGroup = copy(
    authPermissions = authPermissions + permission
      .codeName
  )
}