## References

Some notes/links for myself:

- Scalac sources:
  + [typechecker/ContextErrors.scala](https://github.com/scala/scala/blob/2.11.x/src/compiler/scala/tools/nsc/typechecker/ContextErrors.scala)
  + [found/required message](https://github.com/scala/scala/blob/2.11.x/src/compiler/scala/tools/nsc/typechecker/TypeDiagnostics.scala#L279)
- Sbt sources:
  + [LoggerReporter.scala](https://github.com/sbt/sbt/blob/0.13/compile/src/main/scala/sbt/LoggerReporter.scala#L91)
- Some request to implement syntax highlighting in Scalac error messages: [sbt/sbt#2066](https://github.com/sbt/sbt/issues/2066)
- Another request to filter messages before reporting them: @felher on [gitter](https://gitter.im/sbt/sbt?at=579f4fa7f1da4f376e1f0c36)
