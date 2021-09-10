######################################
# Author: 	Jie Zhang			 #
# Revised: 	14th April, 2021	 #
# Description:	"MAKEFILE"		 #
######################################

# Assumes JUnit is installed
# Assumes CLASSPATH has been set for Junit

ifeq ($(OS),Windows_NT)
	SEP=;
else
	SEP=:
endif

JFLAGS = -g
#JCLASS = -cp ./src:.:$(CLASSPATH):/Users/billsong/Desktop/junit-4.5.jar
#JCLASS = -cp ./src:.:$(CLASSPATH):/usr/share/java/junit4-4.5.jar # on mills
JCLASS = -cp "src$(SEP).$(SEP)../junit-4.5.jar"
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $*.java

CLASSES = \
	src/BoardT.java \
	src/Tile.java \
	src/textView.java \
	src/TestBoardT.java \
	src/TesTile.java \
	src/AllTests.java \
	src/GameController.java \
	src/Demo.java \

MAIN = AllTests

default: classes

classes: $(CLASSES:.java=.class)

doc:
	doxygen doxConfig
	cd latex && $(MAKE)

test:
	find . -name '*.class' -exec rm -f {} \;
	$(JC) $(JCLASS) $(JFLAGS) src/AllTests.java
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore src.AllTests

demo: src/Demo.java
	$(JC) $(JCLASS) $(JFLAGS) src/Demo.java
	$(JVM) $(JCLASS) Demo

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class
