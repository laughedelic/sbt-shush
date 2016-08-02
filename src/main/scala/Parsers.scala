package sbtShush

import fastparse.all._
import scalaparse.Scala._

case object parsers {

  def listParser(cons: Parser[String], nil: Parser[String]): Parser[String] = P(
    nil | (
      cons ~ "[" ~/
        TypeId.! ~ "," ~
        listParser(cons, nil) ~
      "]"
    ).map { case (op, head, tail) =>
      Seq(
        head,
        tail
      ).mkString(op)
    }
  )

  def replaceAll(parser: Parser[String]): Parser[String] = P(
    parser | (AnyChar.! ~ replaceAll(parser)).map { case (a, b) => a + b }
  ).rep.map { _.mkString }


  def applyParserSilently(parser: Parser[String]): String => String = { input =>

    parser.parse(input) match {
      case Parsed.Success(result, _) => result
      case Parsed.Failure(_, _, _) => input
    }
  }
}
