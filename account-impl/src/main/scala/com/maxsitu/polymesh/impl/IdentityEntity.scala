package com.maxsitu.polymesh.impl

import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import com.maxsitu.polymesh.account.services.common.response.GeneratedIdDone
import com.maxsitu.polymesh.api.request.UserLogin
import com.maxsitu.polymesh.api.response.CreatedRecordDone

import scala.collection.immutable.Seq

class IdentityEntity extends PersistentEntity {
  override type Command = IdentityCommand
  override type Event = IdentityEvent
  override type State = IdentityState

  override def initialState: IdentityState = IdentityState()

  override def behavior: Behavior = {
    Actions().onCommand[CreateAuthPermissionCommand, CreatedRecordDone]{
      case (CreateAuthPermissionCommand(name, codeName), ctx, state) =>
        ctx.thenPersist(AuthPermissionCreated(name, codeName)) {_ =>
          ctx.reply(CreatedRecordDone(1))
        }
    }
  }
}

object IdentitySerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[GeneratedIdDone],
    JsonSerializer[CreatedRecordDone],
    JsonSerializer[AuthPermissionCreated],
    JsonSerializer[UserLogin],
    JsonSerializer[User],
    JsonSerializer[IdentityState]
  )
}
