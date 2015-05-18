JFLAGS	= -g -d

CLASSES	= $(shell find src -name *.java)
OBJ	= obj

build:
	@mkdir -p $(OBJ)
	@javac $(JFLAGS) $(OBJ) $(CLASSES)

run: build
	@java -classpath $(OBJ) it.uniroma3.sdr.Main

clean:
	@rm -r $(OBJ)

default: build
