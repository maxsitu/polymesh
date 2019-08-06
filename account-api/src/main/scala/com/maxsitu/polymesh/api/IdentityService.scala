package com.maxsitu.polymesh.api

import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import com.maxsitu.polymesh.api.request.{AuthPermissionCreation, UserCreation, UserLogin}
import com.maxsitu.polymesh.api.response.{CreatedRecordDone, UserLoginDone}

trait IdentityService extends Service {
  def createUser(): ServiceCall[UserCreation, CreatedRecordDone]
  def loginUser(): ServiceCall[UserLogin, UserLoginDone]
  def createAuthPermission(): ServiceCall[AuthPermissionCreation, CreatedRecordDone]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("identity-service").withCalls(
      restCall(Method.POST, "/api/auth/permission", createAuthPermission),
      restCall(Method.POST, "/api/user/login", loginUser),
      restCall(Method.POST, "/api/user", createUser)
    ).withAutoAcl(true)
    // @formatter:on
  }
}
