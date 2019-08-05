package com.polymesh.auth.entity

import org.scalatest.{AsyncWordSpec, Matchers}
import play.api.libs.json.Json
import play.api.libs.json.JsString

class AuthStatusSpec extends AsyncWordSpec with Matchers {
  "The AuthState format" should {
    "format state correctly" in {
      val createdState = AuthStatus
        .Created
      val createdStateStr = Json
        .toJson(createdState)
      createdStateStr shouldEqual JsString("Created")

      val removedState = AuthStatus
        .Removed
      val removedStateStr = Json
        .toJson(removedState)
      removedStateStr shouldEqual JsString("Removed")
    }

    "parse state string correctly" in {
      val createdStateJsStr = JsString("Created")
      val createdState = createdStateJsStr
        .as[AuthStatus.Value]
      createdState shouldBe AuthStatus
        .Created

      val removedStateJsStr = JsString("Removed")
      val removedState = removedStateJsStr
        .as[AuthStatus.Value]
      removedState shouldBe AuthStatus
        .Removed
    }
  }
}
