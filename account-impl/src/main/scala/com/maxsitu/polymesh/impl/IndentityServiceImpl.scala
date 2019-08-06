package com.maxsitu.polymesh.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.BadRequest
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.server.ServerServiceCall
import com.maxsitu.polymesh.account.services.common.validation.ValidationUtil._
import com.maxsitu.polymesh.api.IdentityService
import com.maxsitu.polymesh.api.request.{AuthPermissionCreation, UserCreation, UserLogin}
import com.maxsitu.polymesh.api.response.{CreatedRecordDone, UserLoginDone}
import com.maxsitu.polymesh.account.services.common.authentication.AuthenticationServiceComposition._
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class IdentityServiceImpl(
  db: Database,
  identityRepository: IdentityRepository,
  persistentRegistry: PersistentEntityRegistry
) (implicit ec: ExecutionContext) extends IdentityService {
  override def createAuthPermission(): ServiceCall[AuthPermissionCreation, CreatedRecordDone] = authenticated { (tokenContent, _) =>
    ServerServiceCall { request =>
      validate(request)

      import identityRepository.profile.api._
      val codeNameAction = identityRepository.AuthPermission.filter(_.codename === request.codeName).result.headOption
      db.run(codeNameAction).flatMap { codeNameOpt =>
        codeNameOpt match {
          case Some(codeName) =>
            val ref = persistentRegistry.refFor[IdentityEntity](request.codeName)
            ref.ask(CreateAuthPermissionCommand(request.name, request.codeName))
          case None => throw BadRequest("codeName exists")
        }
      }

    }
  }

  override def createUser(): ServiceCall[UserCreation, CreatedRecordDone] = authenticated { (tokenContent, _) =>
    ServerServiceCall { request =>
      validate(request)


    }
  }

  override def loginUser(): ServiceCall[UserLogin, UserLoginDone] = ???


}
