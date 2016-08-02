package sbtShush

import sbt._, Keys._
import fastparse.all._

case object SbtShushPlugin extends sbt.AutoPlugin {

  override def trigger = allRequirements
  override def requires = empty

  case object autoImport {

    lazy val shushFilter = settingKey[String => String]("")
  }
  import autoImport._

  lazy val customCompilerReporter = TaskKey[Option[xsbti.Reporter]]("compilerReporter", "")

  override lazy val projectSettings = Seq[Setting[_]](
    customCompilerReporter in (Compile, compile) := Some(loggerReporter.value),
    customCompilerReporter in (Test,    compile) := Some(loggerReporter.value)
  )

  val loggerReporter = Def.task {
    new LoggerReporter(
      maxErrors.value,
      streams.value.log
    ) {

      override def log(pos: xsbti.Position, msg: String, severity: xsbti.Severity): Unit =
        super.log(pos, shushFilter.value(msg), severity)
    }
  }
}
