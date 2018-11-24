package com.github.rojanu.search

import java.io.File

import scala.io.StdIn.readLine
import com.github.rojanu.search.text.TextSearchEngine

import scala.annotation.tailrec

object SimpleSearch extends App {
  if (args.length == 0)
    throw new IllegalArgumentException("No directory given to index")
  else {
    evalUserAction(new TextSearchEngine(new File(args(0))))
  }

  @tailrec
  def evalUserAction(searchEngine: TextSearchEngine): Unit ={
    readLine("search > ") match {
      case ":quit" => println("Bye bye...")
      case query =>
        executeSearch(searchEngine, query)
        evalUserAction(searchEngine)
    }
  }

  def executeSearch(searchEngine: TextSearchEngine, query: String): Unit = {
      searchEngine.find(query) match {
        case Right(searchResult) => searchResult match {
          case Nil => println("No matches found")
          case results =>
            results.foreach { result =>
              println(s"${result.file.getAbsolutePath} : ${result.score}%")
            }
        }
        case Left(error) => println(error)
      }
  }
}

