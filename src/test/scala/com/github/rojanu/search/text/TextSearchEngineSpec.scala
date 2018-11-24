package com.github.rojanu.search.text

import java.io.File

import com.github.rojanu.search.base.Match
import org.scalatest.{FlatSpec, Matchers}

class TextSearchEngineSpec extends FlatSpec with Matchers with FileFixtures {

  behavior of "TextSearchEngine"

  it should "find a word in a single word file when searching single with 100% score" in {
    val textSearchEngine = new TextSearchEngine(List(singleWordSearchFile))
    textSearchEngine.find("word") shouldBe List(Match(100, singleWordSearchFile))
  }

  it should "find a word in a 5 paragraph file when searching single file with 100% score" in {
    val textSearchEngine = new TextSearchEngine(List(lipsumSearchFile))
    textSearchEngine.find("purus") shouldBe List(Match(100, lipsumSearchFile))
  }

  it should "partially find multiple words in a 5 paragraph file when searching single file with 25% score" in {
    val textSearchEngine = new TextSearchEngine(List(lipsumSearchFile))
    textSearchEngine.find("purus is latin word") shouldBe List(Match(25, lipsumSearchFile))
  }

  it should "find multiple words in a 5 paragraph file when searching single file with 100% score" in {
    val textSearchEngine = new TextSearchEngine(List(lipsumSearchFile))
    textSearchEngine.find("purus Aliquam enim porta") shouldBe List(Match(100, lipsumSearchFile))
  }

  it should "find a word in a file when searching multiple files with 100% score" in {
    val textSearchEngine =
      new TextSearchEngine(List(singleWordSearchFile, lipsumSearchFile))
    textSearchEngine.find("word") shouldBe List(Match(100, singleWordSearchFile))
  }

  it should "find multiple words in a 5 paragraph file when searching multiple files with 100% score" in {
    val textSearchEngine =
      new TextSearchEngine(List(singleWordSearchFile, lipsumSearchFile))
    textSearchEngine.find("purus Aliquam enim porta") shouldBe List(Match(100, lipsumSearchFile))
  }

  it should "find multiple words in multiple files when searching multiple files with varying scores" in {
    val textSearchEngine =
      new TextSearchEngine(List(singleWordSearchFile, lipsumSearchFile, lipsum2SearchFile))
    textSearchEngine.find("purus Aliquam enim porta") shouldBe List(Match(100, lipsumSearchFile), Match(75, lipsum2SearchFile))
  }

  it should "find multiple words in multiple files when searching multiple files and non txt file with varying scores" in {
    val textSearchEngine = new TextSearchEngine(List(singleWordSearchFile, lipsumSearchFile, lipsum2SearchFile, nonTxtSearchFile))
    textSearchEngine.find("purus Aliquam enim porta") shouldBe List(Match(100, lipsumSearchFile), Match(75, lipsum2SearchFile))
  }

  it should "find multiple words in multiple files when searching a directory with varying scores" in {
    val textSearchEngine = new TextSearchEngine(resourcesSearchDir)
    textSearchEngine.find("purus Aliquam enim porta") shouldBe List(Match(100, lipsumSearchFile), Match(75, lipsum2SearchFile))
  }

  it should "find multiple words when searching a directory with sub-directories with varying scores" in {
    val textSearchEngine = new TextSearchEngine(resourcesDir)
    textSearchEngine.find("purus Aliquam enim porta") shouldBe List(Match(100.0, lipsumSearchFile),
                                                                    Match(25.0, multiParagraphIndexFile),
                                                                    Match(100.0, listCopyDuplicateFile),
                                                                    Match(75.0, lipsum2SearchFile))
  }

  it should "fail to find a word that doesn't exist in directory" in {
    val textSearchEngine = new TextSearchEngine(resourcesSearchDir)
    textSearchEngine.find("nothing") shouldBe List.empty
  }
}
