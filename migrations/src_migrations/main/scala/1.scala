import slick.jdbc.H2Profile.api._
import com.liyaos.forklift.slick.SqlMigration

object M1 {


  PolymeshMigrations
    .migrations = PolymeshMigrations
    .migrations :+ SqlMigration(1)(
    List(
      sqlu"""create table "users" ("id" INTEGER NOT NULL PRIMARY KEY,"first" VARCHAR NOT NULL,"last" VARCHAR NOT NULL)"""
    )
  )
}