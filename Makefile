build:
	javac *.java
run:build
	java test ${ARGS}
clean:
	rm *.class
