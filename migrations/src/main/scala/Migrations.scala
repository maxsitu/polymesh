import com.liyaos.forklift.slick.{SlickMigrationCommandLineTool, SlickMigrationCommands, SlickMigrationManager}

object PolymeshMigrations extends App
  with SlickMigrationCommandLineTool
  with SlickMigrationCommands
  with SlickMigrationManager
  with Codegen {

  MigrationSummary
  execCommands(args.toList)
}