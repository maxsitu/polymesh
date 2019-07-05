import com.liyaos.forklift.slick.SlickCodegen

trait Codegen extends PolymeshCodegen {
  override def tableNames = List("user", "auth_group", "auth_content_type", "auth_permission", "auth_group_permissions", "user_group")
}
