import com.liyaos.forklift.slick.SlickCodegen

trait Codegen extends PolymeshCodegen {
  override def tableNames = List("auth_group_permissions", "auth_permission", "auth_group", "user_group", "user")
}
