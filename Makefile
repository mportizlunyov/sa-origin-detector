placeholder:
	echo "THIS IS THE PLACEHOLDER"
	echo "MANUALLY RUN make compile"
	echo "And then whatever tests"

# The first, default rule to run the run target
run: compile execute cleanup

# Target to execute compiled .class jobs
execute:
	# java -classpath "./compiled" *CLASSNAMES*

# Target to make
makecompiledirectory: compiled
	mkdir compiled

# Rule to compile all files
compile: makecompiledirectory
	# javac -d "./compiled" *JAVA Files*

# Clears all items in ./compiled directory
cleanup:
	rm -r ./compiled/*