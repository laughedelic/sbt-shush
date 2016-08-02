package sbtShush

import fastparse.all._
import scalaparse.Scala._

case object parsers {

  /* Rewrites list type from prefix form:
      foo.bar.Cons[A,foo.bar.Cons[B,foo.bar.Cons[C,foo.bar.Nil]]]
    to infix:
      A :: B :: C :: ∅
    where cons: foo.bar.Cons -> ::, nil: foo.bar.Nil -> ∅
  */
  def infixListType(cons: Parser[String], nil: Parser[String]): Parser[String] = P(
    nil | (
      cons ~ "[" ~/
        TypeId.! ~ "," ~
        infixListType(cons, nil) ~
      "]"
    ).map { case (op, head, tail) =>
      Seq(
        head,
        tail
      ).mkString(op)
    }
  )

  /* Replaces FQN's prefix with the given parser */
  def imported(prefix: Parser[String]): Parser[String] = P(
    prefix ~ "." ~ Id.!
  ).map { case (pref, id) =>
    { if (pref.nonEmpty) pref+"." else "" } + id
  }

  /* Shortens all FQNs from foo.qux.buh to f.q.buh */
  def shortFQNs: Parser[String] =
    (!" " ~ TypeId.!).map { id =>
      val segments = id.split('.').toSeq
      (segments.init.map { seg => seg.take(1) } :+
      segments.last).mkString(".")
    }

  def replaceAll(parser: Parser[String]): Parser[String] = P(
    parser | (AnyChar.! ~ replaceAll(parser)).map { case (a, b) => a + b }
  ).rep.map { _.mkString }


  def applyParserSilently(parser: Parser[String]): String => String = { input =>

    replaceAll(parser).parse(input) match {
      case Parsed.Success(result, _) => result
      case Parsed.Failure(_, _, _) => input
    }
  }
}
