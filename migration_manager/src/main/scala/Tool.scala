import com.liyaos.forklift.slick.{SlickRescueCommandLineTool, SlickRescueCommands}

object Tool extends App
  with SlickRescueCommandLineTool
  with SlickRescueCommands
  with PolymeshCodegen {
  execCommands(
    args
      .toList
  )
}
