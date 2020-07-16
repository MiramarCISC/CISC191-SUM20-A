# CISC191-SUM20-A

## Building and Running Project

---

### Building Using Maven (Recommended. Includes project unit tests)

#### Installation

MacOS

```sh
brew install maven
```

Windows

  - [How to install Maven on Windows](https://www.javatpoint.com/how-to-install-maven)


#### Usage

First, Make sure you are in the project root directory.

With the included `pom.xml` from the repo and Maven already installed, run the
following to compile and install all dependencies

```sh
mvn install
```

Then execute `.jar` file to run project

```sh
java -jar target/WizardGame-0.0.1-SNAPSHOT.jar
```

To recompile project

```sh
mvn compile
```

To run test suite

```sh
mvn test
```

To clean and remove maven build from project

```sh
mvn clean
``

**NOTE:** If unit tests fail for some reason after new changes that prevents
from building the `.jar` file, simply skip unit testing from build by running

```sh
mvn clean install -Dmaven.test.skip
```

---

### Building Using Ant (Does not include project unit tests)

#### Installation

MacOS

```sh
brew install ant
```

Windows

  - [How to Install Apache Ant on Windows](https://mkyong.com/ant/how-to-install-apache-ant-on-windows/)


#### Usage

First, Make sure you are in the project root directory.

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

---

### Building Manually in Terminal (Does not include project unit tests)

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

#### Recompiling

```sh
javac -Xlint -sourcepath src -d build src/main/**/*.java
find . -name "*.java" -not -path "*/test/*" > source.txt
java -cp build com.groupA.Game
```

#### Simple Shortcut

Assign an alias to recompile with just one command

```sh
alias javarun='javac -Xlint -sourcepath src -d build src/main/**/*.java; find . -name "*.java" -not -path "*/test/*" > source.txt; java -cp build com.groupA.Game'
```

Then simply run

```sh
javarun
```

#### Cleaning Up

```sh
rm -r build source.txt
```

---

## Setting Up Project Folder Structure in IntelliJ IDEA

  - [Java project folder structure in IntelliJ IDEA](https://stackoverflow.com/questions/41638654/java-project-folder-structure-in-intellij-idea)

