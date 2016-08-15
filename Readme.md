## sbt-shush plugin

[![](https://travis-ci.org/laughedelic/sbt-shush.svg)](https://travis-ci.org/laughedelic/sbt-shush)
<!-- [![](https://img.shields.io/codacy/???.svg)](https://www.codacy.com/app/era7/sbt-shush) -->
<!-- [![](http://github-release-version.herokuapp.com/github/laughedelic/sbt-shush/release.svg)](https://github.com/laughedelic/sbt-shush/releases/latest) -->
[![](https://img.shields.io/badge/license-LGPLv3-blue.svg)](https://www.tldrlegal.com/l/lgpl-3.0)
[![](https://img.shields.io/badge/contact-gitter_chat-dd1054.svg)](https://gitter.im/laughedelic/sbt-shush)

Sometimes compilation error messages are too noisy and hardly readable. This sbt plugin allows you to _shush the compiler_ and make those messages a bit nicer.

What this plugin actually does is overriding various parts of the sbt `compilerReporter` which determines how Scalac errors and warnings are displayed.

### Example

One of the most frequent cases is "found/required" kind of error message. It can be hard to compare two types when same fully qualified names are repeated many times and operators are written in prefix form.

#### Before

```
/Users/laughedelic/dev/ohnosequences/cosas/src/test/scala/cosas/KListsTests.scala:370: type mismatch;
 found   : this.Y
    (which expands to)  ohnosequences.cosas.klists.KCons[String,ohnosequences.cosas.klists.KCons[Int,ohnosequences.cosas.klists.KCons[String,oh
nosequences.cosas.klists.KCons[Boolean,ohnosequences.cosas.klists.KNilOf[Any]]]]]
 required: ohnosequences.cosas.klists.::[String,ohnosequences.cosas.klists.::[String,ohnosequences.cosas.klists.::[Boolean,ohnosequences.cosas.
klists.*[Any]]]]
    (which expands to)  ohnosequences.cosas.klists.KCons[String,ohnosequences.cosas.klists.KCons[String,ohnosequences.cosas.klists.KCons[Boolea
n,ohnosequences.cosas.klists.KNilOf[Any]]]]
```

##### After

```
src/test/scala/cosas/KListsTests.scala:370:53: type mismatch;
    found: this.Y
         = String :: Int :: String :: Boolean :: *[Any]

 required: String :: String :: Boolean :: *[Any]
```



### Usage

Per project (`project/plugins.sbt`) or globally (`~/.sbt/0.13/plugins/plugins.sbt`):

```scala
addSbtPlugin("laughedelic" % "sbt-shush" % "<version>")
```

The in `build.sbt` (or `~/.sbt/0.13/global.sbt`) you can override following settings:

> TODO
