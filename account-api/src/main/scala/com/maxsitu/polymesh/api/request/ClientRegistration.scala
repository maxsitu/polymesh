import com.maxsitu.polymesh.account.services.common.regex.Matchers
import com.maxsitu.polymesh.account.services.common.validation.ValidationViolationKeys._
import com.maxsitu.polymesh.api.request.WithUserCreationFields
import com.wix.accord.dsl._
import play.api.libs.json.{Format, Json}

case class ClientRegistration(
  company: String,
  firstName: String,
  lastName: String,
  email: String,
  username: String,
  password: String
) extends WithUserCreationFields

object ClientRegistration {
  implicit val format: Format[ClientRegistration] = Json.format

  implicit val clientRegistrationValidator = validator[ClientRegistration] { c =>
    c.company as notEmptyKey("company") is notEmpty
    c.firstName as notEmptyKey("firstName") is notEmpty
    c.lastName as notEmptyKey("lastName") is notEmpty
    c.email as matchRegexFullyKey("email") should matchRegexFully(Matchers.Email)
    c.username as notEmptyKey("username") is notEmpty
    c.username as forSizeKey("username") has size > 5
    c.password as notEmptyKey("password") is notEmpty
    c.password as forSizeKey("password") has size > 7
  }
}