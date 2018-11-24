package com.github.rojanu.search.text

import java.io.File

trait FileFixtures {
  val resourcesDir = new File("src/test/resources")
  val resourcesSearchDir = new File("src/test/resources/search")
  val nonTxtSearchFile = new File("src/test/resources/search/lipsum.dat")
  val singleWordIndexFile = new File("src/test/resources/index/single_word.txt")
  val listIndexFile = new File("src/test/resources/index/list.txt")
  val listCopyDuplicateFile = new File("src/test/resources/duplicate/lipsum_copy.txt")
  val sentenceIndexFile = new File("src/test/resources/index/sentence.txt")
  val paragraphIndexFile = new File("src/test/resources/index/paragraph.txt")
  val multiParagraphIndexFile = new File("src/test/resources/index/multi_paragraphs.txt")
  val singleWordSearchFile = new File("src/test/resources/search/single_word.txt")
  val lipsumSearchFile = new File("src/test/resources/search/lipsum.txt")
  val lipsum2SearchFile = new File("src/test/resources/search/lipsum_2.txt")
}
