# CISC191-SUM20-A

## Compiling Project Manually in Terminal

From root project directory. Create `bin` directory.

```sh
mkdir -p bin
```

Then run the following to compile `.java` into `bin` directory as classpath

```sh
javac -Xlint -sourcepath src -d bin src/main/**/*.java
find -name "*.java" -not -path "*/test/*" > source.txt
```

Then copy all resource files into `bin` directory

```sh
cp src/main/resources/*.png src/main/resources/*.jpg bin
```

Then run `Game` class

```sh
java -cp bin com.groupA.Game
```

### Recompiling

```sh
javac -Xlint -sourcepath src -d bin src/main/**/*.java
find -name "*.java" -not -path "*/test/*" > source.txt
java -cp bin com.groupA.Game
```

#### Simple Shortcut

Assign an alias to recompile with just one command

```sh
alias javarun='javac -Xlint -sourcepath src -d bin src/main/**/*.java; find -name "*.java" -not -path "*/test/*" > source.txt; java -cp bin com.groupA.Game'
```

Then simply run

```sh
javarun
```

## Setting Up Project Folder Structure in IntelliJ IDEA

  - [Java project folder structure in IntelliJ IDEA](https://stackoverflow.com/questions/41638654/java-project-folder-structure-in-intellij-idea)
