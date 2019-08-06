package com.polymesh.auth.entity

import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import com.typesafe.scalalogging.LazyLogging

class AuthEntity extends PersistentEntity with LazyLogging{
  override type Command = AuthCommand
  override type Event = this.type
  override type State = this.type

  override def initialState: AuthEntity.this.type = ???

  override def behavior: Behavior = ???
}
