import slick.jdbc.PostgresProfile.api._
import com.liyaos.forklift.slick.SqlMigration

object M1 {
  PolymeshMigrations.migrations = PolymeshMigrations.migrations :+ SqlMigration(1)(
    List(
      sqlu"""
             create extension if not exists "uuid-ossp";
          """,

      sqlu"""
             create table if not exists "user"
            (
              id          UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
              password    VARCHAR(128) NOT NULL,
              last_login  TIMESTAMP NULL,
              first_name  VARCHAR(30) NOT NULL,
              last_name     VARCHAR(150) NOT NULL,
              is_root_user  BOOLEAN NOT NULL,
              is_admin      BOOLEAN NOT NULL,
              is_active     BOOLEAN NOT NULL,
            	date_joined   TIMESTAMP NOT NULL,
            	email       VARCHAR(254) UNIQUE NOT NULL,
            	cell_phone  VARCHAR(255) NULL
            );
          """,

      sqlu"""
            create table if not exists "auth_group"
            (
            	id    SERIAL PRIMARY KEY,
            	name  VARCHAR(150) NOT NULL,
            	UNIQUE (name)
            );
          """,

      sqlu"""
            create table if not exists "auth_permission"
            (
            	id    SERIAL PRIMARY KEY,
            	name  VARCHAR(255) NOT NULL,
            	codename          VARCHAR(100) NOT NULL,
            	unique (codename)
            );
          """,

      sqlu"""
            create table if not exists "auth_group_permissions"
            (
            	id        SERIAL PRIMARY KEY,
            	group_id  INT NOT NULL,
            	permission_id INT NOT NULL,
            	UNIQUE (group_id, permission_id),
            	FOREIGN KEY (permission_id) REFERENCES "auth_permission" (id),
            	FOREIGN KEY (group_id) REFERENCES "auth_group" (id)
            );
          """,

      sqlu"""
            create table if not exists "user_group"
            (
            	id          SERIAL PRIMARY KEY,
            	user_id     UUID NOT NULL,
            	group_id    INT NOT NULL,
              UNIQUE (user_id, group_id),
            	FOREIGN KEY (group_id) REFERENCES "auth_group" (id),
            	FOREIGN KEY (user_id) REFERENCES "user" (id)
            );
        """
    )
  )
}