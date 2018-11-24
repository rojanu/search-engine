package com.github.rojanu.search.text

import java.io.File

import com.github.rojanu.search.base.Match
import com.github.rojanu.search.text.InvertedIndex.WORD

class TextSearchEngine(files: List[File]) {

  def this(file: File) = this(List(file))

  val index: Map[String, Set[File]] = InvertedIndex.add(files)

  def find(query: String): Either[String, List[Match]] = {
    val wordsToFind: List[String] = wordsFromQuery(query)
    val wordsFoundInFiles = searchWordsInIndex(wordsToFind)
    wordWeight(wordsToFind) match {
      case Some(weight) => Right(calculateResult (wordsFoundInFiles, weight))
      case None => Left("Search Query cannot be empty")
    }
  }

  private def searchWordsInIndex(wordsToFind: List[String]): List[Set[File]] = {
    wordsToFind.flatMap { key =>
      index.get(key.toString.toLowerCase)
    }
  }

  private def wordsFromQuery(query: String): List[String] = {
    WORD.findAllIn(query).matchData.map(_.group(1)).toList
  }

  private def wordWeight(wordsToFind: List[String]): Option[Double] = {
    if (wordsToFind.nonEmpty){
      Some(100 / wordsToFind.length)
    } else {
      None
    }
  }

  private def calculateResult(foundInFiles: Seq[Set[File]],
                              wordWeight: Double): List[Match] = {
      foundInFiles.flatten
        .groupBy(identity)
        .mapValues(_.size * wordWeight)
        .map {
          case (k, v) =>
            Match(v, k)
        }
        .toList
  }
}
