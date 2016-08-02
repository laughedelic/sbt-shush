package sbtShush.test

import sbtShush._, parsers._
import fastparse.all._
import scalaparse._, Scala._

class ParsersTest extends org.scalatest.FunSuite {

  test("try to parse a type") {

    val errorMsg = """type mismatch;
      |[error]  found   : ohnosequences.cosas.klists.::[String,ohnosequences.cosas.klists.::[Boolean,ohnosequences.cosas.klists.*[Any]]]
      |[error]     (which expands to)  ohnosequences.cosas.klists.KCons[String,ohnosequences.cosas.klists.KCons[Boolean,ohnosequences.cosas.klists.KNilOf[Any]]]
      |[error]  required: SBI
      |[error]     (which expands to)  ohnosequences.cosas.klists.KCons[String,ohnosequences.cosas.klists.KCons[Boolean,ohnosequences.cosas.klists.KCons[Int,ohnosequences.cosas.klists.KNilOf[Any]]]]
      |[error] val sbi: SBI = "hola" :: true :: *[Any]
      |[error]                           ^
      |[error] one error found
      |""".stripMargin

    val justList = "ohnosequences.cosas.klists.::[String,ohnosequences.cosas.klists.::[Boolean,ohnosequences.cosas.klists.*[Any]]]"

    val c = P( P("ohnosequences.cosas.klists.") ~ ("KCons") ).map{ _ => " :: " }
    val n = P( P("ohnosequences.cosas.klists.") ~ ("KNilOf") ~ "[" ~ TypeId.! ~ "]" ).map{ bound => s"*[${bound}]" }

    val parsed = replaceAll(listParser(c, n)).parse(errorMsg)

    // info(parsed.toString)
    parsed match {
      case Parsed.Success(result, _) => {
        info(result)
        assert(true)
      }
      case Parsed.Failure(_, _, extra) => {
        info(extra.traced.trace)
        assert(false)
      }
    }

  }

}
