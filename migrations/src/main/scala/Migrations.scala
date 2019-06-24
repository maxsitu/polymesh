import com.liyaos.forklift.slick.{SlickMigrationCommandLineTool, SlickMigrationCommands}

object PolymeshMigrations extends App
  with SlickMigrationCommandLineTool
  with SlickMigrationCommands
  with PolymeshMigrationManager
  with Codegen {

  execCommands(args.toList)
}