# mp2 Feedback

## Grade: 2.5

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
|Yes|No|10.15|

## Test Results
### cpen221.mp2.graph.Task2
| Test Status | Count |
| ----------- | ----- |
|failures|6|
|errors|0|
|tests|20|
|skipped|0|
#### Failed Tests
1. `shortestPath(Graph, Vertex, Vertex, int)[2] (java.lang.AssertionError)`
1. `search(Graph, Vertex, int, Set)[1] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Vertex@48, cpen221.mp2.graph.Vertex@44]> but was:<[cpen221.mp2.graph.Vertex@42, cpen221.mp2.graph.Vertex@44]>)`
1. `search(Graph, Vertex, int, Set)[2] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Vertex@44, cpen221.mp2.graph.Vertex@46, cpen221.mp2.graph.Vertex@48]> but was:<[cpen221.mp2.graph.Vertex@42, cpen221.mp2.graph.Vertex@44, cpen221.mp2.graph.Vertex@46, cpen221.mp2.graph.Vertex@48]>)`
1. `search(Graph, Vertex, int, Set)[3] (java.lang.AssertionError: expected:<[cpen221.mp2.graph.Vertex@4f, cpen221.mp2.graph.Vertex@53, cpen221.mp2.graph.Vertex@4b, cpen221.mp2.graph.Vertex@6d]> but was:<[cpen221.mp2.graph.Vertex@51, cpen221.mp2.graph.Vertex@53, cpen221.mp2.graph.Vertex@4b, cpen221.mp2.graph.Vertex@6d, cpen221.mp2.graph.Vertex@4f]>)`
1. `diameter(Graph, int)[2] (java.lang.AssertionError)`
1. `diameter(Graph, int)[3] (java.lang.AssertionError: expected:<184> but was:<241>)`
### cpen221.mp2.graph.Task1
| Test Status | Count |
| ----------- | ----- |
|failures|3|
|errors|0|
|tests|9|
|skipped|0|
#### Failed Tests
1. `testRemoveVertex1() (java.lang.AssertionError)`
1. `testRemoveVertex2() (java.lang.AssertionError)`
1. `testRemoveVertex3() (java.lang.AssertionError)`

## Test Coverage
76
## Other Comments
Good RI, AF, and specs. Missing inline comments in more complex parts of code. Good code quality overall, but includes a lot of unchecked casting (ex. casting from Edge to E instead of declaring as E to begin with). Tests did not compile due to an unused import, and Kamino test ran for too long and needed to be commented out.

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `89` to `89`

**minor: Method `main` has a Cognitive Complexity of 38 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `71` to `139`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `127` to `127`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `239` to `250`

**major: Method `main` has 64 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `71` to `139`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `81` to `81`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `222` to `233`

**minor: Method `equals` has a Cognitive Complexity of 11 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Edge.java`; lines `42` to `53`

**minor: Method `checkRep` has 26 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `46` to `75`

**minor: File `Graph.java` has 346 lines of code (exceeds 250 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `1` to `601`

**minor: `Graph` has 22 methods (exceeds 20 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `32` to `598`

**minor: Method `shortestPath` has a Cognitive Complexity of 20 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `356` to `421`

**minor: Method `pruneRandomEdges` has 41 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `546` to `596`

**minor: Method `minimumSpanningTree` has a Cognitive Complexity of 10 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `445` to `475`

**minor: Method `diameter` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `489` to `504`

**major: Method `shortestPath` has 51 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `356` to `421`

**minor: Method `checkRep` has a Cognitive Complexity of 19 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `46` to `75`

**minor: Method `getEdge` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `315` to `325`

**minor: Method `pruneRandomEdges` has a Cognitive Complexity of 10 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `546` to `596`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `74` to `74`

**minor: Method `edge` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `156` to `164`

**minor: Method `endPhase` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `252` to `280`

**minor: Method `doInBackground` has a Cognitive Complexity of 11 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `326` to `352`

**minor: Method `init` has a Cognitive Complexity of 8 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `181` to `211`

**minor: Method `make` has 6 arguments (exceeds 4 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/Planet.java`; lines `35` to `36`

**minor: Method `update` has 27 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `259` to `287`

**minor: `SpacePanel` has 22 methods (exceeds 20 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `27` to `374`

**minor: Method `keyReleased` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `82` to `113`

**minor: File `SpacePanel.java` has 276 lines of code (exceeds 250 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `1` to `374`

**minor: Method `keyReleased` has 29 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/SpacePanel.java`; lines `82` to `113`


## Checkstyle Results
### `Graph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 11 | 8 | `Unused import - java.util.Stack.` |
| 18 | None | `Line is longer than 100 characters (found 154).` |
| 19 | 4 | `Unknown tag 'vertices'.` |
| 20 | 4 | `Unknown tag 'edges'.` |
| 23 | 4 | `Unknown tag 'vertices'.` |
| 26 | 4 | `Unknown tag 'edges'.` |
| 32 | None | `Type Javadoc comment is missing an @author tag.` |
| 46 | 5 | `Missing a Javadoc comment.` |
| 88 | 12 | `'for' is not followed by whitespace.` |
| 88 | 31 | `'{' is not preceded with whitespace.` |
| 89 | 15 | `'if' is not followed by whitespace.` |
| 89 | 22 | `'==' is not preceded with whitespace.` |
| 89 | 24 | `'==' is not followed by whitespace.` |
| 89 | 31 | `'{' is not preceded with whitespace.` |
| 172 | None | `Line is longer than 100 characters (found 120).` |
| 176 | 27 | `'(' is preceded with whitespace.` |
| 176 | 39 | `'{' is not preceded with whitespace.` |
| 274 | 28 | `'(' is preceded with whitespace.` |
| 290 | 16 | `'edges' hides a field.` |
| 318 | 56 | `'||' should be on a new line.` |
| 335 | 31 | `'<' is preceded with whitespace.` |
| 335 | 33 | `'<' is followed by whitespace.` |
| 335 | 35 | `'>' is preceded with whitespace.` |
| 375 | 41 | `Must have at least one statement.` |
| 423 | 5 | `Missing a Javadoc comment.` |
| 423 | 51 | `'>' is followed by an illegal character.` |
| 547 | None | `Missing a Javadoc comment.` |
| 551 | 13 | `Missing a Javadoc comment.` |
| 551 | 13 | `Redundant 'public' modifier.` |
| 11 | 8 | `Unused import - java.util.Stack.` |
| 18 | None | `Line is longer than 100 characters (found 154).` |
| 19 | 4 | `Unknown tag 'vertices'.` |
| 20 | 4 | `Unknown tag 'edges'.` |
| 23 | 4 | `Unknown tag 'vertices'.` |
| 26 | 4 | `Unknown tag 'edges'.` |
| 32 | None | `Type Javadoc comment is missing an @author tag.` |
| 46 | 5 | `Missing a Javadoc comment.` |
| 88 | 12 | `'for' is not followed by whitespace.` |
| 88 | 31 | `'{' is not preceded with whitespace.` |
| 89 | 15 | `'if' is not followed by whitespace.` |
| 89 | 22 | `'==' is not preceded with whitespace.` |
| 89 | 24 | `'==' is not followed by whitespace.` |
| 89 | 31 | `'{' is not preceded with whitespace.` |
| 172 | None | `Line is longer than 100 characters (found 120).` |
| 176 | 27 | `'(' is preceded with whitespace.` |
| 176 | 39 | `'{' is not preceded with whitespace.` |
| 274 | 28 | `'(' is preceded with whitespace.` |
| 290 | 16 | `'edges' hides a field.` |
| 318 | 56 | `'||' should be on a new line.` |
| 335 | 31 | `'<' is preceded with whitespace.` |
| 335 | 33 | `'<' is followed by whitespace.` |
| 335 | 35 | `'>' is preceded with whitespace.` |
| 375 | 41 | `Must have at least one statement.` |
| 423 | 5 | `Missing a Javadoc comment.` |
| 423 | 51 | `'>' is followed by an illegal character.` |
| 547 | None | `Missing a Javadoc comment.` |
| 551 | 13 | `Missing a Javadoc comment.` |
| 551 | 13 | `Redundant 'public' modifier.` |
### `MillenniumFalcon.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 14 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 19 | None | `Type Javadoc comment is missing an @author tag.` |
| 25 | None | `Unused Javadoc tag.` |
| 39 | None | `Line is longer than 100 characters (found 115).` |
| 40 | None | `Line is longer than 100 characters (found 103).` |
| 41 | None | `Line is longer than 100 characters (found 119).` |
| 51 | None | `Line is longer than 100 characters (found 194).` |
| 51 | 5 | `Missing a Javadoc comment.` |
| 71 | None | `Line is longer than 100 characters (found 154).` |
| 71 | 5 | `Missing a Javadoc comment.` |
| 72 | 29 | `'-100' is a magic number.` |
| 74 | 33 | `'0.05' is a magic number.` |
| 76 | None | `Line is longer than 100 characters (found 153).` |
| 141 | 5 | `Missing a Javadoc comment.` |
| 151 | None | `Line is longer than 100 characters (found 111).` |
| 151 | 5 | `Missing a Javadoc comment.` |
| 166 | None | `Line is longer than 100 characters (found 134).` |
| 166 | 5 | `Missing a Javadoc comment.` |
| 199 | None | `Line is longer than 100 characters (found 114).` |
| 199 | 5 | `Missing a Javadoc comment.` |
| 200 | None | `Line is longer than 100 characters (found 118).` |
| 14 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 19 | None | `Type Javadoc comment is missing an @author tag.` |
| 25 | None | `Unused Javadoc tag.` |
| 39 | None | `Line is longer than 100 characters (found 115).` |
| 40 | None | `Line is longer than 100 characters (found 103).` |
| 41 | None | `Line is longer than 100 characters (found 119).` |
| 51 | None | `Line is longer than 100 characters (found 194).` |
| 51 | 5 | `Missing a Javadoc comment.` |
| 71 | None | `Line is longer than 100 characters (found 154).` |
| 71 | 5 | `Missing a Javadoc comment.` |
| 72 | 29 | `'-100' is a magic number.` |
| 74 | 33 | `'0.05' is a magic number.` |
| 76 | None | `Line is longer than 100 characters (found 153).` |
| 141 | 5 | `Missing a Javadoc comment.` |
| 151 | None | `Line is longer than 100 characters (found 111).` |
| 151 | 5 | `Missing a Javadoc comment.` |
| 166 | None | `Line is longer than 100 characters (found 134).` |
| 166 | 5 | `Missing a Javadoc comment.` |
| 199 | None | `Line is longer than 100 characters (found 114).` |
| 199 | 5 | `Missing a Javadoc comment.` |
| 200 | None | `Line is longer than 100 characters (found 118).` |

