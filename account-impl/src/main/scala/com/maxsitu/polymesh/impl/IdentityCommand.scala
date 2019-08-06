package com.maxsitu.polymesh.impl

import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import com.maxsitu.polymesh.api.response.CreatedRecordDone

sealed trait IdentityCommand

case class CreateAuthPermissionCommand(
  name: String,
  codeName: String
) extends PersistentEntity.ReplyType[CreatedRecordDone]
  with IdentityCommand
