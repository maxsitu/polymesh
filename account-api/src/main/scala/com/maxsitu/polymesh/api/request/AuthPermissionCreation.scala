package com.maxsitu.polymesh.api.request

import com.maxsitu.polymesh.account.services.common.validation.ValidationViolationKeys._
import com.wix.accord.dsl._
import play.api.libs.json.{Format, Json}

case class AuthPermissionCreation(
  name: String,
  codeName: String
)

object AuthPermissionCreation {
  implicit val format: Format[AuthPermissionCreation] = Json.format

  implicit val authPermissionCreationValidation = validator[AuthPermissionCreation] { a =>
    a.name as notEmptyKey("name") is notEmpty
    a.codeName as notEmptyKey("codeName") is notEmpty
  }
}