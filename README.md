# CISC191-SUM20-A

## Compiling and Running Project

### Using Ant

Ant installation (MacOS)

```sh
brew install ant
```

Ant installation (Windows)

  - [How to Install Apache Ant on Windows](https://mkyong.com/ant/how-to-install-apache-ant-on-windows/)


#### Usage

With the included `build.xml` from the repo and Ant installed, to compile and
distribute `.jar` file, run

```sh
ant
```

Then run `WizardGame.jar` generated with

```sh
ant run
```

Finally, to clean all `build` and `dist` run

```sh
ant clean
```

### Manually in Terminal

From root project directory. Create `build` directory.

```sh
mkdir -p build
```

Then run the following to compile `.java` into `build` directory as classpath

```sh
javac -Xlint -sourcepath src -d build src/main/**/*.java
find -name "*.java" -not -path "*/test/*" > source.txt
```

Then copy all resource files into `build` directory

```sh
cp src/main/resources/*.png src/main/resources/*.jpg build
```

Then run `Game` class

```sh
java -cp build com.groupA.Game
```

### Recompiling

```sh
javac -Xlint -sourcepath src -d build src/main/**/*.java
find -name "*.java" -not -path "*/test/*" > source.txt
java -cp build com.groupA.Game
```

#### Simple Shortcut

Assign an alias to recompile with just one command

```sh
alias javarun='javac -Xlint -sourcepath src -d build src/main/**/*.java; find -name "*.java" -not -path "*/test/*" > source.txt; java -cp build com.groupA.Game'
```

Then simply run

```sh
javarun
```

## Setting Up Project Folder Structure in IntelliJ IDEA

  - [Java project folder structure in IntelliJ IDEA](https://stackoverflow.com/questions/41638654/java-project-folder-structure-in-intellij-idea)
