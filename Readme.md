## sbt-shush plugin

<!-- [![](https://travis-ci.org/laughedelic/sbt-shush.svg?branch=master)](https://travis-ci.org/laughedelic/sbt-shush) -->
<!-- [![](https://img.shields.io/codacy/???.svg)](https://www.codacy.com/app/era7/sbt-shush) -->
<!-- [![](http://github-release-version.herokuapp.com/github/laughedelic/sbt-shush/release.svg)](https://github.com/laughedelic/sbt-shush/releases/latest) -->
[![](https://img.shields.io/badge/license-LGPLv3-blue.svg)](https://www.tldrlegal.com/l/lgpl-3.0)
<!-- [![](https://img.shields.io/badge/contact-gitter_chat-dd1054.svg)](https://gitter.im/laughedelic/sbt-shush) -->

Sometimes compilation error messages are too noisy and hardly readable. This sbt plugin allows you to _shush the compiler_ and make those messages a bit nicer.

What this plugin actually does is overriding various parts of the sbt `compilerReporter` which determines how Scalac errors and warnings are displayed.


### Usage

Per project (`project/plugins.sbt`) or globally (`~/.sbt/0.13/plugins/plugins.sbt`):

```scala
addSbtPlugin("laughedelic" % "sbt-shush" % "<version>")
```

The in `build.sbt` (or `~/.sbt/0.13/global.sbt`) you can override following settings:

> TODO
