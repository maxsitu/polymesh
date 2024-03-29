package com.maxsitu.polymesh.account.services.common.validation

object ValidationViolationKeys {
  def notEmptyKey(field: String) = s"$field.empty"
  def matchRegexFullyKey(field: String) = s"$field.regexNotFullyMatched"
  def forSizeKey(field: String) = s"$field.size"
}
