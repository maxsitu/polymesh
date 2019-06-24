import com.liyaos.forklift.slick.SlickCodegen

trait Codegen extends PolymeshCodegen {
  override def tableNames = List("users", "user_emails")
}
