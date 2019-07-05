import slick.jdbc.H2Profile.api._
import com.liyaos.forklift.slick.SqlMigration

object M1 {


  PolymeshMigrations
    .migrations = PolymeshMigrations
    .migrations :+ SqlMigration(1)(
    List(
      sqlu"""
            create table user
            (
              id          SERIAL PRIMARY KEY,
              password    VARCHAR(128) NOT NULL,
              last_login  DATETIME(6) NULL,
              first_name  VARCHAR(30) NOT NULL,
              last_name     VARCHAR(150) NOT NULL,
              is_root_user  BOOLEAN NOT NULL,
              is_admin      BOOLEAN NOT NULL,
              is_active     BOOLEAN NOT NULL,
            	date_joined   TIMESTAMP NOT NULL,
            	email       VARCHAR(254) UNIQUE NOT NULL,
            	cell_phone  VARCHAR(255) NULL
            )
        """,

      sqlu"""
            create table auth_group
            (
            	id    SERIAL PRIMARY KEY,
            	name  VARCHAR(150) NOT NULL,
            	UNIQUE (name)
            )
        """,

      sqlu"""
            create table auth_content_type
            (
            	id        SERIAL PRIMARY KEY,
            	app_label VARCHAR(100) NOT NULL,
            	model     VARCHAR(100) NOT NULL,
            	UNIQUE (app_label, model)
            )
        """,

      sqlu"""
            create table auth_permission
            (
            	id    SERIAL PRIMARY KEY,
            	name  VARCHAR(255) NOT NULL,
            	content_type_id   INT NOT NULL,
            	codename          VARCHAR(100) NOT NULL,
            	unique (content_type_id, codename),
            	foreign key (content_type_id) references auth_content_type (id)
            );
        """,

      sqlu"""
            create table auth_group_permissions
            (
            	id        SERIAL PRIMARY KEY,
            	group_id  INT NOT NULL,
            	permission_id INT NOT NULL,
            	UNIQUE (group_id, permission_id),
            	FOREIGN KEY (permission_id) REFERENCES auth_permission (id),
            	FOREIGN KEY (group_id) REFERENCES auth_group (id)
            )
        """,

      sqlu"""
            create table user_group
            (
            	id          SERIAL PRIMARY KEY,
            	user_id     INT NOT NULL,
            	group_id    INT NOT NULL,
              UNIQUE (user_id, group_id),
            	FOREIGN KEY (group_id) REFERENCES auth_group (id),
            	FOREIGN KEY (user_id) REFERENCES user (id)
            )
        """
    )
  )
}