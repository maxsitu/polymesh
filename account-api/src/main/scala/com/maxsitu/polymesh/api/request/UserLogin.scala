package com.maxsitu.polymesh.api.request

import com.wix.accord.dsl._
import play.api.libs.json.{Format, Json}

case class UserLogin(
  username: String,
  password: String
)

object UserLogin {
  implicit val format: Format[UserLogin] = Json.format

  implicit val userLoginValidation = validator[UserLogin] { u =>
    u.username as "username.notEmpty" is notEmpty
    u.password as "password.notEmpty" is notEmpty
  }
}