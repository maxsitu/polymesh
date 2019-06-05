package com.maxsitu.polymeshstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.maxsitu.polymeshstream.api.PolymeshStreamService
import com.maxsitu.polymesh.api.PolymeshService
import com.softwaremill.macwire._

class PolymeshStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new PolymeshStreamApplication(context) {
      override def serviceLocator: NoServiceLocator.type = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new PolymeshStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[PolymeshStreamService])
}

abstract class PolymeshStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[PolymeshStreamService](wire[PolymeshStreamServiceImpl])

  // Bind the PolymeshService client
  lazy val polymeshService: PolymeshService = serviceClient.implement[PolymeshService]
}
