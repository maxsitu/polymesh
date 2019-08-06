package com.maxsitu.polymesh.impl

import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.jdbc.JdbcPersistenceComponents
import com.lightbend.lagom.scaladsl.persistence.slick.SlickPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.maxsitu.polymesh.account.services.common.exception.handling.CustomExceptionSerializer
import com.maxsitu.polymesh.api.IdentityService
import com.softwaremill.macwire._
import play.api.db.HikariCPComponents
import play.api.libs.ws.ahc.AhcWSComponents

import scala.collection.immutable

class IdentityLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext): LagomApplication =
    new IdentityApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new IdentityApplication(context) with LagomDevModeComponents

  override def describeServices: immutable.Seq[Descriptor] = List(
    readDescriptor[IdentityService]
  )
}

abstract class IdentityApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with JdbcPersistenceComponents
    with SlickPersistenceComponents
    with HikariCPComponents
    with AhcWSComponents {

  // Bind identity service
  override lazy val lagomServer = serverFor[IdentityService](wire[IdentityServiceImpl])

  override lazy val jsonSerializerRegistry = IdentitySerializerRegistry

  lazy val identityRepository = IdentityRepository

  override lazy val defaultExceptionSerializer = new CustomExceptionSerializer(environment)

  persistentEntityRegistry.register(wire[IdentityEntity])

  readSide.register(wire[IdentityEventProcessor])
}