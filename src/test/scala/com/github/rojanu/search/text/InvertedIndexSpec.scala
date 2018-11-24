package com.github.rojanu.search.text

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class InvertedIndexSpec extends FlatSpec with Matchers with FileFixtures {

  behavior of "InvertedIndex"

  it should "single word in a file is indexed" in {
    val index: Map[String, Set[File]] = InvertedIndex.add(List(singleWordIndexFile))
    index shouldBe Map("word" -> Set(singleWordIndexFile))
  }

  it should "a list of words in a file is indexed" in {
    val index: Map[String, Set[File]] = InvertedIndex.add(List(listIndexFile))
    val expectedIndex = Map(
      "lorem" -> Set(listIndexFile),
      "ipsum" -> Set(listIndexFile),
      "dolor" -> Set(listIndexFile),
      "sit" -> Set(listIndexFile),
      "amet" -> Set(listIndexFile),
      "consectetur" -> Set(listIndexFile),
      "adipiscing" -> Set(listIndexFile),
      "elit" -> Set(listIndexFile)
    )
    index shouldBe expectedIndex
  }

  it should "a paragraph in a file is indexed" in {
    val index: Map[String, Set[File]] = InvertedIndex.add(List(paragraphIndexFile))
    val expectedIndex = Map(
      "lorem" -> Set(paragraphIndexFile),
      "ipsum" -> Set(paragraphIndexFile),
      "dolor" -> Set(paragraphIndexFile),
      "sit" -> Set(paragraphIndexFile),
      "amet" -> Set(paragraphIndexFile),
      "consectetur" -> Set(paragraphIndexFile),
      "adipiscing" -> Set(paragraphIndexFile),
      "elit" -> Set(paragraphIndexFile)
    )
    index shouldBe expectedIndex
  }

  it should "a sentence in a file is indexed" in {
    val index: Map[String, Set[File]] = InvertedIndex.add(List(sentenceIndexFile))
    val expectedIndex = Map(
      "lorem" -> Set(sentenceIndexFile),
      "ipsum" -> Set(sentenceIndexFile),
      "dolor" -> Set(sentenceIndexFile),
      "sit" -> Set(sentenceIndexFile),
      "amet" -> Set(sentenceIndexFile),
      "consectetur" -> Set(sentenceIndexFile),
      "adipiscing" -> Set(sentenceIndexFile),
      "elit" -> Set(sentenceIndexFile)
    )
    index shouldBe expectedIndex
  }

  it should "multi paragraphs in a file is indexed" in {
    val index: Map[String, Set[File]] = InvertedIndex.add(List(multiParagraphIndexFile))
    val expectedIndex = Map(
      "lorem" -> Set(multiParagraphIndexFile),
      "ipsum" -> Set(multiParagraphIndexFile),
      "dolor" -> Set(multiParagraphIndexFile),
      "sit" -> Set(multiParagraphIndexFile),
      "amet" -> Set(multiParagraphIndexFile),
      "consectetur" -> Set(multiParagraphIndexFile),
      "adipiscing" -> Set(multiParagraphIndexFile),
      "elit" -> Set(multiParagraphIndexFile),
      "sed" -> Set(multiParagraphIndexFile),
      "risus" -> Set(multiParagraphIndexFile),
      "scelerisque" -> Set(multiParagraphIndexFile),
      "pulvinar" -> Set(multiParagraphIndexFile),
      "diam" -> Set(multiParagraphIndexFile),
      "nec" -> Set(multiParagraphIndexFile),
      "interdum" -> Set(multiParagraphIndexFile),
      "tellus" -> Set(multiParagraphIndexFile),
      "aenean" -> Set(multiParagraphIndexFile),
      "urna" -> Set(multiParagraphIndexFile),
      "ut" -> Set(multiParagraphIndexFile),
      "enim" -> Set(multiParagraphIndexFile),
      "eleifend" -> Set(multiParagraphIndexFile),
      "laoreet" -> Set(multiParagraphIndexFile),
      "et" -> Set(multiParagraphIndexFile)
    )
    index shouldBe expectedIndex
  }

  it should "non-text file is not indexed" in {
    InvertedIndex.add(List(new File("src/test/resources/index/non_text.dat"))) shouldBe Map.empty
  }

  it should "all txt files in directory are indexed" in {
    InvertedIndex.add(List(new File("src/test/resources/index"))) shouldBe Map(
      "elit" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "pulvinar" -> Set(multiParagraphIndexFile),
      "consectetur" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "sed" -> Set(multiParagraphIndexFile),
      "nec" -> Set(multiParagraphIndexFile),
      "laoreet" -> Set(multiParagraphIndexFile),
      "sit" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "risus" -> Set(multiParagraphIndexFile),
      "ipsum" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "tellus" -> Set(multiParagraphIndexFile),
      "aenean" -> Set(multiParagraphIndexFile),
      "scelerisque" -> Set(multiParagraphIndexFile),
      "lorem" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "amet" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "enim" -> Set(multiParagraphIndexFile),
      "ut" -> Set(multiParagraphIndexFile),
      "et" -> Set(multiParagraphIndexFile),
      "dolor" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile),
      "eleifend" -> Set(multiParagraphIndexFile),
      "word" -> Set(singleWordIndexFile),
      "diam" -> Set(multiParagraphIndexFile),
      "interdum" -> Set(multiParagraphIndexFile),
      "urna" -> Set(multiParagraphIndexFile),
      "adipiscing" -> Set(sentenceIndexFile, listIndexFile, paragraphIndexFile, multiParagraphIndexFile)
    )
  }

  it should "all txt files in sub-directories are indexed" in {
    InvertedIndex.add(List(new File("src/test/resources"))) shouldBe Map(
      "elit" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "est" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "consequat" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "pulvinar" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "tortor" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "fringilla" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "consectetur" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "sed" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "aliquet" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "vulputate" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "dictum" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "in" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "fames" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "accumsan" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "sapien" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "efficitur" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "molestie" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "volutpat" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "dui" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "senectus" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "hac" -> Set(lipsum2SearchFile),
      "auctor" -> Set(lipsum2SearchFile),
      "eu" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ultricies" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "nam" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "aliquam" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "nec" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "justo" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "laoreet" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "sit" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "mattis" -> Set(lipsum2SearchFile),
      "quis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ultrices" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "rhoncus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "vitae" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "risus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "fusce" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "dapibus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ipsum" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "tempus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "felis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "cubilia" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "vel" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ligula" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "mollis" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "ornare" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "tellus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "orci" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "aenean" -> Set(lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "purus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "scelerisque" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "finibus" -> Set(lipsum2SearchFile),
      "malesuada" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "luctus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "curabitur" -> Set(lipsum2SearchFile),
      "potenti" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "cursus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "gravida" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "suspendisse" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "nisl" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "lorem" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "a" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "eget" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "convallis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "metus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "amet" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "nullam" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "dictumst" -> Set(lipsum2SearchFile),
      "enim" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "praesent" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "primis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "commodo" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "vestibulum" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "netus" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "condimentum" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "blandit" -> Set(lipsum2SearchFile),
      "ut" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "neque" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "fermentum" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "viverra" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ante" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "et" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "faucibus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "lobortis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "massa" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "egestas" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "porttitor" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "sodales" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "erat" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "vehicula" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "magna" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "suscipit" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "iaculis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "dolor" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "at" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "nisi" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "sem" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "semper" -> Set(lipsum2SearchFile),
      "id" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "arcu" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ex" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "dignissim" -> Set(lipsum2SearchFile),
      "ac" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "nunc" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "placerat" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "lacus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "euismod" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "pharetra" -> Set(lipsum2SearchFile),
      "leo" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "tristique" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "posuere" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "proin" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "nibh" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "facilisis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "etiam" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "morbi" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "habitasse" -> Set(lipsum2SearchFile),
      "nulla" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "tempor" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "rutrum" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "turpis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "curae" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "sollicitudin" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "venenatis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "ullamcorper" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "eros" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "lectus" -> Set(lipsum2SearchFile),
      "hendrerit" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "habitant" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "mi" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "quam" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "vivamus" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "bibendum" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "pretium" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "imperdiet" -> Set(lipsum2SearchFile),
      "maximus" -> Set(lipsum2SearchFile),
      "odio" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "porta" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "mauris" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "lacinia" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "donec" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "pellentesque" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "platea" -> Set(lipsum2SearchFile),
      "duis" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "quisque" -> Set(lipsum2SearchFile),
      "maecenas" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "eleifend" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "word" -> Set(singleWordSearchFile, singleWordIndexFile),
      "varius" -> Set(lipsum2SearchFile),
      "augue" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "velit" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "congue" -> Set(lipsum2SearchFile),
      "diam" -> Set(lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "tincidunt" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "libero" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "interdum" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "non" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "urna" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile, multiParagraphIndexFile),
      "feugiat" -> Set(lipsum2SearchFile, lipsumSearchFile, listCopyDuplicateFile),
      "adipiscing" -> Set(listCopyDuplicateFile, multiParagraphIndexFile, lipsumSearchFile, sentenceIndexFile, paragraphIndexFile, lipsum2SearchFile, listIndexFile),
      "phasellus" -> Set(lipsumSearchFile, listCopyDuplicateFile),
      "elementum" -> Set(lipsum2SearchFile)
    )
  }
}
