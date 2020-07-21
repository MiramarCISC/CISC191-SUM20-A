# CISC191-SUM20-A

<!-- TOC GFM -->

* [Building and Running Project](#building-and-running-project)
  * [Building Using Maven (Recommended. Includes project unit tests)](#building-using-maven-recommended-includes-project-unit-tests)
    * [Tl;dr](#tldr)
    * [Maven Installation](#maven-installation)
    * [Maven Usage](#maven-usage)
  * [Building Using Ant (Does not include project unit tests)](#building-using-ant-does-not-include-project-unit-tests)
    * [Tl:dr](#tldr-1)
    * [Ant Installation](#ant-installation)
    * [Ant Usage](#ant-usage)
  * [Building Manually in Terminal (Does not include project unit tests)](#building-manually-in-terminal-does-not-include-project-unit-tests)
    * [Tl;dr](#tldr-2)
    * [Building and Running](#building-and-running)
    * [Recompiling](#recompiling)
    * [Simple Shortcut](#simple-shortcut)
    * [Cleaning Up](#cleaning-up)
* [Setting Up Project Folder Structure in IntelliJ IDEA](#setting-up-project-folder-structure-in-intellij-idea)

<!-- /TOC -->

## Building and Running Project

### Building Using Maven (Recommended. Includes project unit tests)

#### Tl;dr

```bash
mvn install

# Running .jar
java -jar target/WizardGame-0.0.1-SNAPSHOT.jar

# Running main class
mvn exec:java -Dexec.mainClass="edu.sdccd.cisc191.wizardGame.Game"
```

#### Maven Installation

MacOS

```bash
brew install maven
```

Windows

* [How to install Maven on Windows](https://www.javatpoint.com/how-to-install-maven)

#### Maven Usage

First, Make sure you are in the project root directory.

With the included `pom.xml` from the repo and Maven already installed, run the
following to compile and install all dependencies

```bash
mvn install
```

Then execute `.jar` file to run project

```bash
java -jar target/WizardGame-0.0.1-SNAPSHOT.jar
```

To run specific class. (replace `<class>` with path to java file starting from
`edu.sdccd.cisc191.wizardGame` directory, using `.` as path separator).

```bash
# Template
mvn exec:java -Dexec.mainClass="edu.sdccd.cisc191.wizardGame.<class>"

# Running Game class
mvn exec:java -Dexec.mainClass="edu.sdccd.cisc191.wizardGame.Game"
```

> `Game` class can be ran by default using only `mvn exec:java` by modifying
> `pom.xml`. Just uncomment this section from the xml file. `<mainClass>` can
> be changed to whatever default class to run.

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <configuration>
    <mainClass>edu.sdccd.cisc191.wizardGame.Game</mainClass>
  </configuration>
</plugin>
```

To recompile project

```bash
mvn compile
```

To package project into a JAR file

```bash
mvn package
```

To run test suite

```bash
mvn test
```

To clean and remove maven build from project

```bash
mvn clean
```

**NOTE:** If unit tests fail after new changes that prevents from building the
`.jar` file, although not recommended, simply skip unit testing from build by
running

```bash
mvn clean install -Dmaven.test.skip
```

or to just compile and package into a JAR file without reinstalling dependencies

```bash
mvn package -Dmaven.test.skip
```

### Building Using Ant (Does not include project unit tests)

#### Tl:dr

```bash
ant
ant run
```

#### Ant Installation

MacOS

```bash
brew install ant
```

Windows

* [How to Install Apache Ant on Windows](https://mkyong.com/ant/how-to-install-apache-ant-on-windows/)

#### Ant Usage

First, Make sure you are in the project root directory.

With the included `build.xml` from the repo and Ant installed, to compile and
distribute `.jar` file, run

```bash
ant
```

Then run `WizardGame.jar` generated with

```bash
ant run
```

Finally, to clean all `build` and `dist` run

```bash
ant clean
```

### Building Manually in Terminal (Does not include project unit tests)

#### Tl;dr

```bash
mkdir -p build
alias javarun='javac -Xlint -sourcepath src -d build src/main/**/*.java; find . -name "*.java" -not -path "*/test/*" > source.txt; java -cp build edu.sdccd.cisc191.wizardGame.Game'
javarun
```

#### Building and Running

From root project directory. Create `build` directory.

```bash
mkdir -p build
```

Then run the following to compile `.java` into `build` directory as classpath

```bash
javac -Xlint -sourcepath src -d build src/main/**/*.java
find . -name "*.java" -not -path "*/test/*" > source.txt
```

Then copy all resource files into `build` directory

```bash
cp src/main/resources/*.png src/main/resources/*.jpg build
```

Then run `Game` class

```bash
java -cp build edu.sdccd.cisc191.wizardGame.Game
```

#### Recompiling

```bash
javac -Xlint -sourcepath src -d build src/main/**/*.java
find . -name "*.java" -not -path "*/test/*" > source.txt
java -cp build edu.sdccd.cisc191.wizardGame.Game
```

#### Simple Shortcut

Assign an alias to recompile with just one command

```bash
alias javarun='javac -Xlint -sourcepath src -d build src/main/**/*.java; find . -name "*.java" -not -path "*/test/*" > source.txt; java -cp build edu.sdccd.cisc191.wizardGame.Game'
```

Then simply run

```bash
javarun
```

#### Cleaning Up

```bash
rm -r build source.txt
```

## Setting Up Project Folder Structure in IntelliJ IDEA

* [Java project folder structure in IntelliJ IDEA](https://stackoverflow.com/questions/41638654/java-project-folder-structure-in-intellij-idea)

