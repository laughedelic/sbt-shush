package sbtShush

import sbt._, Keys._
import fastparse.all._
import java.nio.file.Path

case object SbtShushPlugin extends sbt.AutoPlugin {

  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin

  case object autoImport {

    lazy val shushFilter = settingKey[String => String]("")
    lazy val customCompilerReporter = TaskKey[Option[xsbti.Reporter]]("compilerReporter", "")
  }
  import autoImport._


  override def projectSettings: Seq[Setting[_]] = Seq(
    shushFilter := { x => x },
    customCompilerReporter in (Compile, compile) := Some(loggerReporter.value),
    customCompilerReporter in (Test,    compile) := Some(loggerReporter.value)
  )


  implicit class MaybeOps[T](val mb: xsbti.Maybe[T]) extends AnyVal {
    def opt: Option[T] = if (mb.isDefined) Some(mb.get) else None
  }

  implicit class FileOps(val file: File) extends AnyVal {

    def absPath: Path = file.toPath.toAbsolutePath.normalize
    def relPath(base: File): Path = base.absPath relativize file.absPath
  }

  val loggerReporter = Def.task {
    new LoggerReporter(maxErrors.value, streams.value.log) {

      override def print(log: (=> String) => Unit, pos: xsbti.Position, msg: String): Unit = {

        val f = pos.sourceFile.opt.map{ _.relPath(baseDirectory.value) }.getOrElse("")
        val l = pos.line.opt.getOrElse("0")
        val c = pos.pointer.opt.getOrElse("0")

        log(s"${f}:${l}:${c}: ${shushFilter.value(msg)}")
        log(pos.lineContent)
        pos.pointerSpace.fold {} { spc => log(spc + "^") }
      }
    }
  }
}
