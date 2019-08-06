package com.maxsitu.polymesh.api.response

import play.api.libs.json.{Format, Json}

case class CreatedRecordDone(num: Int)
object CreatedRecordDone {
  implicit val format: Format[CreatedRecordDone] = Json.format
}
