# CISC191-SUM20-A

<!-- TOC GFM -->

* [Building and Running Project](#building-and-running-project)
  * [Building Using Maven (Recommended. Includes project unit tests)](#building-using-maven-recommended-includes-project-unit-tests)
    * [Maven Installation](#maven-installation)
    * [Maven Usage](#maven-usage)
  * [Building Using Ant (Does not include project unit tests)](#building-using-ant-does-not-include-project-unit-tests)
    * [Ant Installation](#ant-installation)
    * [Ant Usage](#ant-usage)
  * [Building Manually in Terminal (Does not include project unit tests)](#building-manually-in-terminal-does-not-include-project-unit-tests)
    * [Recompiling](#recompiling)
    * [Simple Shortcut](#simple-shortcut)
    * [Cleaning Up](#cleaning-up)
* [Setting Up Project Folder Structure in IntelliJ IDEA](#setting-up-project-folder-structure-in-intellij-idea)

<!-- /TOC -->

## Building and Running Project

### Building Using Maven (Recommended. Includes project unit tests)

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

To recompile project

```bash
mvn compile
```

To run test suite

```bash
mvn test
```

To clean and remove maven build from project

```bash
mvn clean
```

**NOTE:** If unit tests fail for some reason after new changes that prevents
from building the `.jar` file, simply skip unit testing from build by running

```bash
mvn clean install -Dmaven.test.skip
```

### Building Using Ant (Does not include project unit tests)

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

From root project directory. Create `build` directory.

```bash
mkdir -p build
```

Then run the following to compile `.java` into `build` directory as classpath

```bash
javac -Xlint -sourcepath src -d build src/main/**/*.java
find -name "*.java" -not -path "*/test/*" > source.txt
```

Then copy all resource files into `build` directory

```bash
cp src/main/resources/*.png src/main/resources/*.jpg build
```

Then run `Game` class

```bash
java -cp build com.groupA.Game
```

#### Recompiling

```bash
javac -Xlint -sourcepath src -d build src/main/**/*.java
find . -name "*.java" -not -path "*/test/*" > source.txt
java -cp build com.groupA.Game
```

#### Simple Shortcut

Assign an alias to recompile with just one command

```bash
alias javarun='javac -Xlint -sourcepath src -d build src/main/**/*.java; find . -name "*.java" -not -path "*/test/*" > source.txt; java -cp build com.groupA.Game'
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

