# Text Search Engine
This application will build an in memory representation of the content of the txt file in given directory, including 
sub-directories.

To search 
- `sbt "runMain com.github.rojanu.search.SimpleSearch <path_to_directory/containing/txt-files>"`
- enter text to search
- `:quit` to exit the search console

```bash
sbt "runMain com.github.rojanu.search.SimpleSearch /Users/austek/Workspace/search-engine/src/test/resources"
search > ipsum
/Users/austek/Workspace/search-engine/src/test/resources/index/sentence.txt : 100.0%
/Users/austek/Workspace/search-engine/src/test/resources/index/multi_paragraphs.txt : 100.0%
/Users/austek/Workspace/search-engine/src/test/resources/search/lipsum.txt : 100.0%
/Users/austek/Workspace/search-engine/src/test/resources/search/lipsum_2.txt : 100.0%
/Users/austek/Workspace/search-engine/src/test/resources/duplicate/lipsum_copy.txt : 100.0%
/Users/austek/Workspace/search-engine/src/test/resources/index/list.txt : 100.0%
/Users/austek/Workspace/search-engine/src/test/resources/index/paragraph.txt : 100.0%
search > none
No matches found
search > :quit
[success] Total time: 263 s, completed Oct 28, 2018 8:31:53 PM
```