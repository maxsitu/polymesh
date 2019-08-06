package com.maxsitu.polymesh.impl

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object IdentityRepository extends {
  val profile = slick.jdbc.PostgresProfile
} with IdentityRepository

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait IdentityRepository {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = AuthGroup.schema ++ AuthGroupPermissions.schema ++ AuthPermission.schema ++ User.schema ++ UserGroup.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table AuthGroup
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(150,true) */
  case class AuthGroupRow(id: Int, name: String)
  /** GetResult implicit for fetching AuthGroupRow objects using plain SQL queries */
  implicit def GetResultAuthGroupRow(implicit e0: GR[Int], e1: GR[String]): GR[AuthGroupRow] = GR{
    prs => import prs._
    AuthGroupRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table auth_group. Objects of this class serve as prototypes for rows in queries. */
  class AuthGroup(_tableTag: Tag) extends profile.api.Table[AuthGroupRow](_tableTag, "auth_group") {
    def * = (id, name) <> (AuthGroupRow.tupled, AuthGroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> AuthGroupRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(150,true) */
    val name: Rep[String] = column[String]("name", O.Length(150,varying=true))
  }
  /** Collection-like TableQuery object for table AuthGroup */
  lazy val AuthGroup = new TableQuery(tag => new AuthGroup(tag))

  /** Entity class storing rows of table AuthGroupPermissions
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param groupId Database column group_id SqlType(int4)
   *  @param permissionId Database column permission_id SqlType(int4) */
  case class AuthGroupPermissionsRow(id: Int, groupId: Int, permissionId: Int)
  /** GetResult implicit for fetching AuthGroupPermissionsRow objects using plain SQL queries */
  implicit def GetResultAuthGroupPermissionsRow(implicit e0: GR[Int]): GR[AuthGroupPermissionsRow] = GR{
    prs => import prs._
    AuthGroupPermissionsRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table auth_group_permissions. Objects of this class serve as prototypes for rows in queries. */
  class AuthGroupPermissions(_tableTag: Tag) extends profile.api.Table[AuthGroupPermissionsRow](_tableTag, "auth_group_permissions") {
    def * = (id, groupId, permissionId) <> (AuthGroupPermissionsRow.tupled, AuthGroupPermissionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(groupId), Rep.Some(permissionId)).shaped.<>({r=>import r._; _1.map(_=> AuthGroupPermissionsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column group_id SqlType(int4) */
    val groupId: Rep[Int] = column[Int]("group_id")
    /** Database column permission_id SqlType(int4) */
    val permissionId: Rep[Int] = column[Int]("permission_id")

    /** Foreign key referencing AuthGroup (database name auth_group_permissions_group_id_fkey) */
    lazy val authGroupFk = foreignKey("auth_group_permissions_group_id_fkey", groupId, AuthGroup)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing AuthPermission (database name auth_group_permissions_permission_id_fkey) */
    lazy val authPermissionFk = foreignKey("auth_group_permissions_permission_id_fkey", permissionId, AuthPermission)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table AuthGroupPermissions */
  lazy val AuthGroupPermissions = new TableQuery(tag => new AuthGroupPermissions(tag))

  /** Entity class storing rows of table AuthPermission
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(255,true)
   *  @param codename Database column codename SqlType(varchar), Length(100,true) */
  case class AuthPermissionRow(id: Int, name: String, codename: String)
  /** GetResult implicit for fetching AuthPermissionRow objects using plain SQL queries */
  implicit def GetResultAuthPermissionRow(implicit e0: GR[Int], e1: GR[String]): GR[AuthPermissionRow] = GR{
    prs => import prs._
    AuthPermissionRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table auth_permission. Objects of this class serve as prototypes for rows in queries. */
  class AuthPermission(_tableTag: Tag) extends profile.api.Table[AuthPermissionRow](_tableTag, "auth_permission") {
    def * = (id, name, codename) <> (AuthPermissionRow.tupled, AuthPermissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(codename)).shaped.<>({r=>import r._; _1.map(_=> AuthPermissionRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column codename SqlType(varchar), Length(100,true) */
    val codename: Rep[String] = column[String]("codename", O.Length(100,varying=true))
  }
  /** Collection-like TableQuery object for table AuthPermission */
  lazy val AuthPermission = new TableQuery(tag => new AuthPermission(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(uuid), PrimaryKey
   *  @param password Database column password SqlType(varchar), Length(128,true)
   *  @param lastLogin Database column last_login SqlType(timestamp), Default(None)
   *  @param firstName Database column first_name SqlType(varchar), Length(30,true)
   *  @param lastName Database column last_name SqlType(varchar), Length(150,true)
   *  @param isRootUser Database column is_root_user SqlType(bool)
   *  @param isAdmin Database column is_admin SqlType(bool)
   *  @param isActive Database column is_active SqlType(bool)
   *  @param dateJoined Database column date_joined SqlType(timestamp)
   *  @param email Database column email SqlType(varchar), Length(254,true)
   *  @param cellPhone Database column cell_phone SqlType(varchar), Length(255,true), Default(None) */
  case class UserRow(id: java.util.UUID, password: String, lastLogin: Option[java.sql.Timestamp] = None, firstName: String, lastName: String, isRootUser: Boolean, isAdmin: Boolean, isActive: Boolean, dateJoined: java.sql.Timestamp, email: String, cellPhone: Option[String] = None)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[java.util.UUID], e1: GR[String], e2: GR[Option[java.sql.Timestamp]], e3: GR[Boolean], e4: GR[java.sql.Timestamp], e5: GR[Option[String]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[java.util.UUID], <<[String], <<?[java.sql.Timestamp], <<[String], <<[String], <<[Boolean], <<[Boolean], <<[Boolean], <<[java.sql.Timestamp], <<[String], <<?[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, "user") {
    def * = (id, password, lastLogin, firstName, lastName, isRootUser, isAdmin, isActive, dateJoined, email, cellPhone) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(password), lastLogin, Rep.Some(firstName), Rep.Some(lastName), Rep.Some(isRootUser), Rep.Some(isAdmin), Rep.Some(isActive), Rep.Some(dateJoined), Rep.Some(email), cellPhone).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[java.util.UUID] = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column password SqlType(varchar), Length(128,true) */
    val password: Rep[String] = column[String]("password", O.Length(128,varying=true))
    /** Database column last_login SqlType(timestamp), Default(None) */
    val lastLogin: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_login", O.Default(None))
    /** Database column first_name SqlType(varchar), Length(30,true) */
    val firstName: Rep[String] = column[String]("first_name", O.Length(30,varying=true))
    /** Database column last_name SqlType(varchar), Length(150,true) */
    val lastName: Rep[String] = column[String]("last_name", O.Length(150,varying=true))
    /** Database column is_root_user SqlType(bool) */
    val isRootUser: Rep[Boolean] = column[Boolean]("is_root_user")
    /** Database column is_admin SqlType(bool) */
    val isAdmin: Rep[Boolean] = column[Boolean]("is_admin")
    /** Database column is_active SqlType(bool) */
    val isActive: Rep[Boolean] = column[Boolean]("is_active")
    /** Database column date_joined SqlType(timestamp) */
    val dateJoined: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("date_joined")
    /** Database column email SqlType(varchar), Length(254,true) */
    val email: Rep[String] = column[String]("email", O.Length(254,varying=true))
    /** Database column cell_phone SqlType(varchar), Length(255,true), Default(None) */
    val cellPhone: Rep[Option[String]] = column[Option[String]]("cell_phone", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table UserGroup
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(uuid)
   *  @param groupId Database column group_id SqlType(int4) */
  case class UserGroupRow(id: Int, userId: java.util.UUID, groupId: Int)
  /** GetResult implicit for fetching UserGroupRow objects using plain SQL queries */
  implicit def GetResultUserGroupRow(implicit e0: GR[Int], e1: GR[java.util.UUID]): GR[UserGroupRow] = GR{
    prs => import prs._
    UserGroupRow.tupled((<<[Int], <<[java.util.UUID], <<[Int]))
  }
  /** Table description of table user_group. Objects of this class serve as prototypes for rows in queries. */
  class UserGroup(_tableTag: Tag) extends profile.api.Table[UserGroupRow](_tableTag, "user_group") {
    def * = (id, userId, groupId) <> (UserGroupRow.tupled, UserGroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(groupId)).shaped.<>({r=>import r._; _1.map(_=> UserGroupRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(uuid) */
    val userId: Rep[java.util.UUID] = column[java.util.UUID]("user_id")
    /** Database column group_id SqlType(int4) */
    val groupId: Rep[Int] = column[Int]("group_id")

    /** Foreign key referencing AuthGroup (database name user_group_group_id_fkey) */
    lazy val authGroupFk = foreignKey("user_group_group_id_fkey", groupId, AuthGroup)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name user_group_user_id_fkey) */
    lazy val userFk = foreignKey("user_group_user_id_fkey", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table UserGroup */
  lazy val UserGroup = new TableQuery(tag => new UserGroup(tag))
}
object Version{
  def version = 1
}
