package com.maxsitu.polymeshstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.maxsitu.polymeshstream.api.PolymeshStreamService
import com.maxsitu.polymesh.api.PolymeshService

import scala.concurrent.Future

/**
  * Implementation of the PolymeshStreamService.
  */
class PolymeshStreamServiceImpl(polymeshService: PolymeshService) extends PolymeshStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(polymeshService.hello(_).invoke()))
  }
}
