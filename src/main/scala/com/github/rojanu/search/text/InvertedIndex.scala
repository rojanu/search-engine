package com.github.rojanu.search.text

import java.io.File

import scala.util.matching.Regex

object InvertedIndex {

  val WORD: Regex = raw"(\w+)".r

  private def parse(s: String): Iterator[String] = WORD.findAllIn(s).map(_.toString.toLowerCase)

  def add(files: List[File]): Map[String, Set[File]] = {
    mergeMaps(
      files
        .map {
          case d if d.exists && d.isDirectory => add(d.listFiles.toList)
          case f if f.exists && f.isFile && f.getName.endsWith(".txt") => indexWordAgainstFile(f)
          case _ => Map.empty[String, Set[File]]
        }
    )
  }

  private def mergeMaps(maps: Seq[Map[String, Set[File]]]): Map[String, Set[File]] =
    maps.reduceLeft { (r, m) =>
      m.foldLeft(r) {
        case (accIndex, (word, files)) =>
          accIndex.get(word) match {
            case Some(accFiles) => accIndex + (word -> (files ++ accFiles))
            case None => accIndex + (word -> files)
          }
      }
    }

  private def indexWordAgainstFile(f: File): Map[String, Set[File]] = {
    getWordsFromFile(f).map(_ -> f).foldLeft(Map.empty[String, Set[File]]) {
      case (index, (word, file)) =>
        index.get(word) match {
          case Some(files) => index + (word -> (files + file))
          case None => index + (word -> Set(file))
        }
    }
  }

  private def getWordsFromFile(f: File): Iterator[String] = getLinesFromFile(f) flatMap parse

  private def getLinesFromFile(f: File): Iterator[String] = scala.io.Source.fromFile(f).getLines
}
