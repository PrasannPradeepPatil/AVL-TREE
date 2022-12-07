JFLAGS = -g
JC = javac
RM = del
sources = $(wildcard *.java)

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

default: classes

classes: $(sources:.java=.class)

clean:
	$(RM) *.class
	
run:
	java avltree input_file.txt